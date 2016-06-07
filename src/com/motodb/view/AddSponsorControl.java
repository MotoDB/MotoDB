package com.motodb.view;

import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.model.Sponsor;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddSponsorControl extends ScreenControl {
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final SponsorManager manager = new SponsorManagerImpl();
    
	@FXML
	private TableView<Sponsor> sponsorsTable;
	@FXML
	private TableColumn<Sponsor, String> nameColumn, logoColumn;
	@FXML
	private TextField nameField, logoUrlField, searchField;
	@FXML
	private Button delete;
    
	public AddSponsorControl(){
		super();
	}
	    
    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	// Initialize the table
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        logoColumn.setCellValueFactory(cellData -> cellData.getValue().logoProperty());
        // Add observable list data to the table
        sponsorsTable.setItems(manager.getSponsors());
        
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
        	manager.addSponsor(nameField.getText(), logoUrlField.getText());
        	this.clear();
        	
            sponsorsTable.setItems(manager.getSponsors());
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
    private void search(){
    	
    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sponsor> filteredData = new FilteredList<>(manager.getSponsors(), p -> true);

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
                } else if (e.getLogo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Sponsor> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(sponsorsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        sponsorsTable.setItems(sortedData);
    }
    
    /**
	 * It listen for selection changes to disable/enable the delete button 
	 * when the user selects something in the table
	 */
	private void update(){
		
	}
}
