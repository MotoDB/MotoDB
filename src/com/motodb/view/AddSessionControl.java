package com.motodb.view;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ClaxManager;
import com.motodb.controller.ClaxManagerImpl;
import com.motodb.controller.SessionManager;
import com.motodb.controller.SessionManagerImpl;
import com.motodb.controller.WeekendManager;
import com.motodb.controller.WeekendManagerImpl;
import com.motodb.model.Clax;
import com.motodb.model.Session;
import com.motodb.model.Weekend;
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

public class AddSessionControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final SessionManager sessionManager = new SessionManagerImpl();
    private final ClaxManager classManager = new ClaxManagerImpl();
    private final WeekendManager weekendManager = new WeekendManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();

	@FXML
	private TableView<Session> sessionsTable;
	@FXML
	private TableColumn<Session, String> yearColumn, codeColumn, typeColumn, classColumn, startDateColumn, 
		endDateColumn, airTempColumn, groundTempColumn, humColumn, conditionsColumn, lapsColumn, durationColumn;
	@FXML
	private TextField durationField, lapsField, humidityField, groundTemperatureField, airTemperatureField, codeField, 
			searchField;
	@FXML
	private ComboBox<SessionType> typeBox;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private ComboBox<Weekend> weekendBox;
	@FXML
	private ComboBox<Clax> classBox;
	@FXML
	private ComboBox<ConditionType> conditionsBox;
	@FXML
	private DatePicker startDate, finishDate;
	
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;

	public enum SessionType{
		ProvaLibera,
		Qualifica,
		Gara;
	}
	
	public enum ConditionType{
		Piovoso,
		Asciutto,
		Mite;
	}
	
    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	
    	classBox.setItems(classManager.getClasses());
    	typeBox.setItems(FXCollections.observableArrayList(Arrays.asList(SessionType.values())));
    	conditionsBox.setItems(FXCollections.observableArrayList(Arrays.asList(ConditionType.values())));
    	championshipManager.getChampionships().forEach(l->yearBox.getItems().add(Integer.toString(l.getYear())));
    	weekendBox.setItems(weekendManager.getWeekends());
    	
    	// Initialize the table
    	yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
    	codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
    	typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    	classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
    	startDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStartDate().toString()));
    	endDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFinishDate().toString()));
    	airTempColumn.setCellValueFactory(cellData -> cellData.getValue().airTempProperty().asString());
    	groundTempColumn.setCellValueFactory(cellData -> cellData.getValue().groundTempProperty().asString());
    	humColumn.setCellValueFactory(cellData -> cellData.getValue().humidityProperty().asString());
    	conditionsColumn.setCellValueFactory(cellData -> cellData.getValue().conditionsProperty());
    	lapsColumn.setCellValueFactory(cellData -> cellData.getValue().lapsProperty().asString());
    	durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationMaxProperty());
    	          
        // Add observable list data to the table
        sessionsTable.setItems(sessionManager.getSessions());
        
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
        	java.util.Date weekDate= new SimpleDateFormat("yyyy-MM-dd").parse(weekendBox.getSelectionModel().getSelectedItem().getStartDate().toString());
        	java.util.Date startingDate= new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getValue().toString());
        	java.util.Date endingDate= new SimpleDateFormat("yyyy-MM-dd").parse(finishDate.getValue().toString());
    		
        	sessionManager.addSession(classBox.getSelectionModel().getSelectedItem().getName(), Integer.parseInt(yearBox.getSelectionModel().getSelectedItem()), new java.sql.Date(weekDate.getTime()) , conditionsBox.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(airTemperatureField.getText()), 
    				Integer.parseInt(groundTemperatureField.getText()), Integer.parseInt(humidityField.getText()), new java.sql.Date(startingDate.getTime()), new java.sql.Date(endingDate.getTime()), codeField.getText(), durationField.getText(), typeBox.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(lapsField.getText()));
        
    		sessionsTable.setItems(sessionManager.getSessions()); // Update table view
        	this.clear();
        } catch (Exception e) {
        	e.printStackTrace();
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
