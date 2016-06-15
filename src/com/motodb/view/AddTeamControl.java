package com.motodb.view;

import org.controlsfx.control.CheckComboBox;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.controller.TeamManager;
import com.motodb.controller.TeamManagerImpl;
import com.motodb.model.Team;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddTeamControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final TeamManager manager = new TeamManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final SponsorManager sponsorManager = new SponsorManagerImpl();
    
	@FXML
	private TableView<Team> teamsTable;
	@FXML
	private TableColumn<Team, String> nameColumn, yearColumn, locationColumn, logoColumn;
	@FXML
	private TextField nameField, locationField, logoField;
	@FXML
	private CheckComboBox<String> classesField, sponsorsField;
	@FXML
	private ComboBox<String> yearField = new ComboBox<String>();
	@FXML
	private Button delete;
	@FXML
	private VBox vBoxFields;
	    
    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	
    	championshipManager.getChampionships().forEach(l->yearField.getItems().add(Integer.toString(l.getYear())));
    	
    	classesField=new CheckComboBox<String>();
    	vBoxFields.getChildren().add(vBoxFields.getChildren().size()-3, classesField);
    	classesField.setDisable(true);
    	classesField.setPrefWidth(300.0);
    	classesField.setMaxWidth(300.0);
    	
    	sponsorsField=new CheckComboBox<String>();
    	vBoxFields.getChildren().add(vBoxFields.getChildren().size()-2, sponsorsField);
    	sponsorsField.getItems().addAll(sponsorManager.getSponsorsNames());
    	sponsorsField.setPrefWidth(300.0);
    	sponsorsField.setMaxWidth(300.0);
 
    	// Initialize the table
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        logoColumn.setCellValueFactory(cellData -> cellData.getValue().logoProperty());
        
        // Add observable list data to the table
        teamsTable.setItems(manager.getTeams());
        
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
        	manager.addTeam(Integer.parseInt(yearField.getSelectionModel().getSelectedItem()), nameField.getText(), locationField.getText(), logoField.getText(), classesField.getCheckModel().getCheckedItems(),sponsorsField.getCheckModel().getCheckedItems());
        	teamsTable.setItems(manager.getTeams()); // Update table view
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
			
		});
	}
}
