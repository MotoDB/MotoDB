package com.motodb.view;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ManufacturerContractManager;
import com.motodb.controller.ManufacturerContractManagerImpl;
import com.motodb.controller.ManufacturerManager;
import com.motodb.controller.ManufacturerManagerImpl;
import com.motodb.controller.TeamManager;
import com.motodb.controller.TeamManagerImpl;
import com.motodb.model.Manufacturer;
import com.motodb.model.ManufacturerContract;
import com.motodb.model.Team;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddManufacturerContractControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final TeamManager teamManager = new TeamManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final ManufacturerContractManager manufacturerContractManager = new ManufacturerContractManagerImpl();
    private final ManufacturerManager manufacturerManager = new ManufacturerManagerImpl();

	@FXML
	private TableView<ManufacturerContract> manufacturerContractTable;
	@FXML
	private TableColumn<ManufacturerContract, String> yearColumn, teamColumn, manufacturerColumn;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private ComboBox<Manufacturer> manufacturerBox;
	@FXML
	private ComboBox<Team> teamBox;
	@FXML
	private TextField searchField;
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;

    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	
    	manufacturerBox.setItems(FXCollections.observableArrayList(manufacturerManager.getManufacturers()));
    	teamBox.setDisable(true);
    	this.update();
    	
    	// Initialize the table
    	yearColumn.setCellValueFactory(cellData -> cellData.getValue().championshipYearProperty().asString());
       	teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamNameProperty());
       	manufacturerColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerNameProperty());
    	
        championshipManager.getChampionships().forEach(l->yearBox.getItems().add(Integer.toString(l.getYear())));
        
        // Add observable list data to the table
        manufacturerContractTable.setItems(manufacturerContractManager.getManufacturerContracts());
        
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
        	
        	manufacturerContractManager.addManufacturerContract(manufacturerBox.getSelectionModel().getSelectedItem().getManufacturerName(), Integer.parseInt(yearBox.getSelectionModel().getSelectedItem()),  
    				teamBox.getSelectionModel().getSelectedItem().getName());

        	manufacturerContractTable.setItems(manufacturerContractManager.getManufacturerContracts()); // Update table view
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
		
		yearBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			if(newValue!=null){
				teamBox.setDisable(false);
				teamBox.setItems(FXCollections.observableArrayList(teamManager.getTeamsFromYear(Integer.parseInt(newValue))));
			}else{
				teamBox.setDisable(true);
			}
		}));
	}
}
