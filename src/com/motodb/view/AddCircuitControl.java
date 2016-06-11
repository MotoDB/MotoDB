package com.motodb.view;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.model.Rider;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddCircuitControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final CircuitManager circuitManager = new CircuitManagerImpl();

	@FXML
	private TableView<Circuit> circuitTable;
	@FXML
	private TableColumn<Circuit, String> nameColumn, locationColumn, stateColumn, capacityColumn, rightHandersColumn, leftHandersColumn, 
		lenghtColumn, straightColumn, dateColumn, recordColumn;
	@FXML
	private TextField nameField, locationField, stateField, straightField, rightHandersField, leftHandersField, 
		lenghtField, capacityField, recordField, photoField, searchField;
	@FXML
	private DatePicker datePicker = new DatePicker();
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;

    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	    	
    	// Initialize the table
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	locationColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	stateColumn.setCellValueFactory(cellData -> cellData.getValue().personalCodeProperty().asString());
    	capacityColumn.setCellValueFactory(cellData -> cellData.getValue().birthplaceProperty());
    	rightHandersColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
    	leftHandersColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
    	lenghtColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDate().toString()));
    	straightColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asString());
    	dateColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asString());
    	recordColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asString());
        
        // Add observable list data to the table
        circuitTable.setItems(circiutManager.getCirciuts());
        
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
   /* 
    int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
    String role, java.sql.Date dateOfBirth, int number, int weigth, int heigth, String acronym*/
	@FXML
    private void add() {
        try {
        	java.util.Date date= new SimpleDateFormat("yyyy-MM-dd").parse(datePicker.getValue().toString());
        	circuitManager.addCircuit(Integer.parseInt(nameField.getText()), stateField.getText(), locationField.getText(), 
        			Integer.parseInt(capacityField.getText()), Integer.parseInt(rightHandersField.getText()), Integer.parseInt(leftHandersField.getText()), 
        				Integer.parseInt(lenghtField.getText()), Integer.parseInt(straightField.getText()), 
        					new java.sql.Date(date.getTime()), photoField.getText(), Integer.parseInt(recordField.getText()));
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
