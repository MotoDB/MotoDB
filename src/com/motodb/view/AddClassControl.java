package com.motodb.view;

import com.motodb.controller.ClassesManager;
import com.motodb.controller.ClassesManagerImpl;
import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddClassControl extends ScreenControl {
	
	
	// Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

	/*@FXML
	private TableView<Championship> depotsTable;
	
	@FXML
	private TableColumn<Championship, String> nameColumn;*/
    
    ClassesManager manager = new ClassesManagerImpl();
	
	@FXML
	private TextField nameField, rulesUrlField;
	@FXML
	private Button delete;
    
	public AddClassControl(){
		super();
	}
	    
    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	
    }
    
    /**
     * Called when the user press the 'add' button; this method adds
     * a new depot to the controller ObservableList of depots
     */
	@FXML
    private void add() {
        try {
        	manager.addClass(nameField.getText(), rulesUrlField.getText());
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
    private void search(){

    }
    
    /**
	 * It listen for selection changes to disable/enable the delete button 
	 * when the user selects something in the table
	 */
	private void update(){

	}
}
