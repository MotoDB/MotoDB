package com.motodb.view;

import java.util.Arrays;

import com.motodb.controller.BikeManager;
import com.motodb.controller.BikeManagerImpl;
import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ClaxManager;
import com.motodb.controller.ClaxManagerImpl;
import com.motodb.controller.ContractManager;
import com.motodb.controller.ContractManagerImpl;
import com.motodb.controller.ManufacturerManager;
import com.motodb.controller.ManufacturerManagerImpl;
import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.controller.RacingRiderManager;
import com.motodb.controller.RacingRiderManagerImpl;
import com.motodb.controller.SessionManager;
import com.motodb.controller.SessionManagerImpl;
import com.motodb.controller.WeekendManager;
import com.motodb.controller.WeekendManagerImpl;
import com.motodb.model.Bike;
import com.motodb.model.Clax;
import com.motodb.model.Manufacturer;
import com.motodb.model.RacingRider;
import com.motodb.model.Rider;
import com.motodb.model.Session;
import com.motodb.model.Weekend;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddRacingRiderControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final SessionManager sessionManager = new SessionManagerImpl();
    private final ClaxManager classManager = new ClaxManagerImpl();
    private final ContractManager contractManager = new ContractManagerImpl();
    private final RacingRiderManager racingRiderManager = new RacingRiderManagerImpl();
    private final MemberManager riderManager = new MemberManagerImpl();
    private final WeekendManager weekendManager = new WeekendManagerImpl();
    private final ManufacturerManager manufacturerManager = new ManufacturerManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final BikeManager bykeManager = new BikeManagerImpl();

	@FXML
	private TableView<RacingRider> racingRidersTable;
	@FXML
	private TableColumn<RacingRider, String> yearColumn, weekendColumn, riderColumn, classColumn, sessionColumn, 
	timeColumn, finishedColumn, positionColumn, pointsColumn, manufacturerColumn, modelColumn;
	@FXML
	private TextField timeField, speedField, positionField, searchField;
	
	@FXML
	private ComboBox<Session> sessionCodeBox;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private ComboBox<Rider> riderBox;
	@FXML
	private ComboBox<Weekend> weekendBox;
	@FXML
	private ComboBox<Clax> classBox;
	@FXML
	private ComboBox<Boolean> finishedBox;
	@FXML
	private ComboBox<Manufacturer> manufacturerBox;
	@FXML
	private ComboBox<Bike> bikeModelBox;
	
	private final ObservableList<Integer> points = FXCollections.observableArrayList(Arrays.asList(25,20,16,13,11,10,9,8,7,6,5,4,3,2,1));
	
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;

    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	manufacturerBox.setItems(manufacturerManager.getManufacturers());
    	bikeModelBox.setDisable(true);
    	classBox.setDisable(true);
    	riderBox.setDisable(true);
    	weekendBox.setDisable(true);
    	sessionCodeBox.setDisable(true);
    	finishedBox.setItems(FXCollections.observableArrayList(Arrays.asList(true,false)));
    	championshipManager.getChampionships().forEach(l->yearBox.getItems().add(Integer.toString(l.getYear())));

    	this.update();
    	
    	// Initialize the table
    	yearColumn.setCellValueFactory(cellData -> cellData.getValue().championshipYearProperty().asString());
    	weekendColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeekendStartingDate().toString()));
    	riderColumn.setCellValueFactory(cellData -> cellData.getValue().personalCodeProperty().asString());
    	classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
    	modelColumn.setCellValueFactory(cellData -> cellData.getValue().bikeModelProperty());
    	sessionColumn.setCellValueFactory(cellData -> cellData.getValue().sessionCodeProperty());
    	timeColumn.setCellValueFactory(cellData -> cellData.getValue().fastestTimeProperty());
    	finishedColumn.setCellValueFactory(cellData -> cellData.getValue().finishedProperty().asString());
    	positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty().asString());
    	pointsColumn.setCellValueFactory(cellData -> cellData.getValue().pointsProperty().asString());
    	speedColumn.setCellValueFactory(cellData -> cellData.getValue().averageSpeedProperty().asString());
    	manufacturerColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerNameProperty());
    	          
        // Add observable list data to the table
    	racingRidersTable.setItems(racingRiderManager.getRacingRiders());
        
        // Make the table columns editable by double clicking
        this.edit();
        // Use a 'searchField' to search for books in the tableView
        this.search();
    }
    
    /**
     * Called when the user press the 'add' button; this method adds
     * a new depot to the controller ObservableList of depots
     */
	@FXML
    private void add() {
        try {
        	if(finishedBox.getValue().equals("false") || Integer.parseInt(positionField.getText())>15){
        		racingRiderManager.addRacingRider(Integer.parseInt(yearBox.getValue()), weekendBox.getValue().getStartDate(), contractManager.getClassFromRiderYear(Integer.parseInt(yearBox.getValue()), riderBox.getValue().getPersonalCode()), sessionCodeBox.getValue().getCode(),
                        timeField.getText(), Integer.parseInt(positionField.getText()), Integer.parseInt(speedField.getText()), finishedBox.getValue(), riderBox.getValue().getPersonalCode(), manufacturerBox.getValue().getManufacturerName(), bikeModelBox.getValue().getModel(),
                        0);
        	}else{
	        	racingRiderManager.addRacingRider(Integer.parseInt(yearBox.getValue()), weekendBox.getValue().getStartDate(), contractManager.getClassFromRiderYear(Integer.parseInt(yearBox.getValue()), riderBox.getValue().getPersonalCode()), sessionCodeBox.getValue().getCode(),
	                    timeField.getText(), Integer.parseInt(positionField.getText()), Integer.parseInt(speedField.getText()), finishedBox.getValue(), riderBox.getValue().getPersonalCode(), manufacturerBox.getValue().getManufacturerName(), bikeModelBox.getValue().getModel(),
	                    points.get(Integer.parseInt(positionField.getText())-1));
        	}
        
        	racingRidersTable.setItems(racingRiderManager.getRacingRiders()); // Update table view
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
		
		yearBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue!=null){
				if(!weekendManager.getWeekendsFromYear(Integer.parseInt(newValue)).isEmpty()){
					weekendBox.setDisable(false);
					weekendBox.setItems(weekendManager.getWeekendsFromYear(Integer.parseInt(newValue)));
					
				}else{
					weekendBox.setDisable(true);
				}
				
				if(!riderManager.getRidersFromYear(Integer.parseInt(newValue)).isEmpty()){
					riderBox.setDisable(false);
					riderBox.setItems(riderManager.getRidersFromYear(Integer.parseInt(newValue)));
					
				}else{
					riderBox.setDisable(true);
				}
				
				if(!classManager.getClassesFromYear(Integer.parseInt(newValue)).isEmpty()){
					classBox.setDisable(false);
					classBox.setItems(classManager.getClassesFromYear(Integer.parseInt(newValue)));
					
				}else{
					riderBox.setDisable(true);
				}
			}
		});
		
		
		weekendBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue!=null){
				if(!sessionManager.getSessionsFromWeekend(weekendBox.getValue().getStartDate()).isEmpty()){
					sessionCodeBox.setDisable(false);
					sessionCodeBox.setItems(sessionManager.getSessionsFromWeekend(weekendBox.getValue().getStartDate()));
					
				}else{
					sessionCodeBox.setDisable(true);
				}
			}
		});
		
		manufacturerBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue!=null){
				if(!bykeManager.getBikesFromManufacturer(manufacturerBox.getValue().getManufacturerName()).isEmpty()){
					bikeModelBox.setDisable(false);
					bikeModelBox.setItems(bykeManager.getBikesFromManufacturer(manufacturerBox.getValue().getManufacturerName()));
					
				}else{
					bikeModelBox.setDisable(true);
				}
			}
		});
		
	}
	
}
