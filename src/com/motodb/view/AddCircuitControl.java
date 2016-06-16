package com.motodb.view;

import java.util.Optional;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.CircuitManager;
import com.motodb.controller.CircuitManagerImpl;
import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.model.Circuit;
import com.motodb.model.Rider;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.AutoCompleteComboBoxListener;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddCircuitControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final CircuitManager circuitManager = new CircuitManagerImpl();
    private final MemberManager riderManager = new MemberManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();

	@FXML
	private TableView<Circuit> circuitTable;
	@FXML
	private TableColumn<Circuit, String> nameColumn, locationColumn, stateColumn, rightHandersColumn, leftHandersColumn, 
		lenghtColumn, straightColumn, recordColumn, recordRiderColumn, recordYearColumn;
	@FXML
	private TextField nameField, locationField, stateField, straightField, rightHandersField, leftHandersField, 
		lenghtField, capacityField, recordField, photoField, searchField;
	@FXML
	private ComboBox<String> recordYearBox;
	@FXML
	private ComboBox<Rider> recordRiderBox;
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;
	
	@SuppressWarnings("unused")
	private AutoCompleteComboBoxListener<String> autoCompleteFactory;
	@SuppressWarnings("unused")
	private AutoCompleteComboBoxListener<Rider> autoCompleteRiderFactory;

    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	    	
    	// Initialize the table
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    	stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
    	rightHandersColumn.setCellValueFactory(cellData -> cellData.getValue().rightHandersProperty().asString());
    	leftHandersColumn.setCellValueFactory(cellData -> cellData.getValue().leftHandersProperty().asString());
    	lenghtColumn.setCellValueFactory(cellData -> cellData.getValue().lenghtProperty().asString());
    	straightColumn.setCellValueFactory(cellData -> cellData.getValue().straightProperty().asString());
    	recordColumn.setCellValueFactory(cellData -> cellData.getValue().recordProperty().asString());
    	recordRiderColumn.setCellValueFactory(cellData -> cellData.getValue().recordRiderProperty().asString());
    	recordYearColumn.setCellValueFactory(cellData -> cellData.getValue().recordYearProperty().asString());
        
    	recordRiderBox.setItems(FXCollections.observableArrayList(riderManager.getRiders()));
        autoCompleteRiderFactory = new AutoCompleteComboBoxListener<Rider>(recordRiderBox);
        
        championshipManager.getChampionships().forEach(l->recordYearBox.getItems().add(Integer.toString(l.getYear())));
        autoCompleteFactory = new AutoCompleteComboBoxListener<String>(recordYearBox);
        
        // Add observable list data to the table
        circuitTable.setItems(circuitManager.getCircuits());
        
        // Make the table columns editable by double clicking
        this.edit();
        // Use a 'searchField' to search for books in the tableView
        this.search();
        // Listen for selection changes and enable delete button
        this.update();
    }
    
    /**
     * Called when the user press the 'add' button; this method adds
     * a new depot to the controller ObservableList of depots
     */
	@FXML
    private void add() {
        try {
        	if(recordField.getText()!=null && recordRiderBox.getSelectionModel().getSelectedItem()!=null && recordYearBox.getSelectionModel().getSelectedItem()!=null){
        		circuitManager.addCircuit(nameField.getText(), stateField.getText(), locationField.getText(), Integer.parseInt(rightHandersField.getText()), 
        		Integer.parseInt(leftHandersField.getText()), Integer.parseInt(lenghtField.getText()), Integer.parseInt(straightField.getText()), 
        		photoField.getText(), Optional.ofNullable(Integer.parseInt(recordField.getText())), Optional.ofNullable(recordRiderBox.getSelectionModel().getSelectedItem().getPersonalCode()), Optional.ofNullable(Integer.parseInt(recordYearBox.getSelectionModel().getSelectedItem())) );
        	}else if(recordField.getText().isEmpty() && recordRiderBox.getSelectionModel().isEmpty() && recordYearBox.getSelectionModel().isEmpty()){
        		circuitManager.addCircuit(nameField.getText(), stateField.getText(), locationField.getText(), Integer.parseInt(rightHandersField.getText()), 
            	Integer.parseInt(leftHandersField.getText()), Integer.parseInt(lenghtField.getText()), Integer.parseInt(straightField.getText()), 
            	photoField.getText(), Optional.empty(), Optional.empty(), Optional.empty());
        	}else throw new IllegalArgumentException();
        	
        	circuitTable.setItems(circuitManager.getCircuits()); // Update table view
        	this.clear();
        } catch (Exception e) {
            alert.showWarning(e);
        }
    }
	
	/**
     * Called when the user edit a depot name directly from the tableColumn;
     * This method edits the selected field in the observableList of depots and 
     * makes fields editable directly from the table
     */
	private void edit() {

	}
	
	/**
     * Called on delete button press, opens a confirmation dialog asking if you 
     * really want to delete the element; this method is called 
     * to delete the selected element from the observableList
     */
    @FXML
    private void delete() {

    }
    
    /**
     * Called when the user enter something in the search field;
     * It search name of the depot
     */
    private void search(){/*
    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Clax> filteredData = new FilteredList<>(manager.getClasses(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(e -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (e.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (e.getRules().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Clax> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(classesTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        classesTable.setItems(sortedData);*/
    }
    
    /**
	 * It listen for selection changes to disable/enable the delete button 
	 * when the user selects something in the table
	 */
	private void update(){
		/*
		yearField.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
			if(newValue!=null){
				classesField.getItems().clear();
				classesField.setDisable(false);
				classesField.getItems().addAll(championshipManager.getClassesNames(Integer.parseInt(yearField.getSelectionModel().getSelectedItem())));
			} else {
				classesField.setDisable(true);
			}
		});

		yearField.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
			
		});*/
	}
}
