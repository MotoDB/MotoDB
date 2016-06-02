/**
 * 'addChampionship.fxml' Control Class
 */

package com.motodb.view;

import org.controlsfx.control.CheckComboBox;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ClassesManager;
import com.motodb.controller.ClassesManagerImpl;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddChampionship extends ScreenControl {
	
	
	// Aler panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

	/*@FXML
	private TableView<Championship> depotsTable;
	
	@FXML
	private TableColumn<Championship, String> nameColumn;*/
    
    ChampionshipManager manager = new ChampionshipManagerImpl();
    ClassesManager classesManager = new ClassesManagerImpl();
	
	@FXML
	private TextField yearField, editionField, searchField;;
	@FXML
	private Button delete;
    @FXML
    private CheckComboBox<String> combo;
    
    @FXML
    private VBox vBoxFields;
    
	public AddChampionship(){
		super();
		
		
	}
	    
    /**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
    public void initialize() {
    	
    	combo=new CheckComboBox<String>(classesManager.getClassesNames());
    	vBoxFields.getChildren().add(vBoxFields.getChildren().size()-1, combo);
    	
    	// Initialize the table
        //nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        
        // Add observable list data to the table
        //depotsTable.setItems(depotsController.getDepots());
        
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
        	manager.insertChampionship(Integer.parseInt(yearField.getText()),Integer.parseInt(editionField.getText()),combo.getCheckModel().getCheckedItems());
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
		/*
        // nameColumn
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(t -> {
	        try{
	        	//depotsController.editName(t.getTableView().getItems().get(t.getTablePosition().getRow()), t.getNewValue()); 
	        }catch(Exception e){
	        	alert.showWarning(e);
	        }
        });*/
	}
	
	/**
     * Called on delete button press, opens a confirmation dialog asking if you 
     * really want to delete the element; this method is called 
     * to delete the selected element from the observableList
     */
    @FXML
    private void delete() {/*
        Optional<ButtonType> result = alert.showConfirmation(depotsTable.getSelectionModel().getSelectedItem().getName());

        if (result.get() == ButtonType.OK) {
            int selectedIndex = depotsTable.getSelectionModel().getSelectedIndex();
            depotsController.removeDepot(depotsTable.getItems().get(selectedIndex));
        }*/
    }
    
    /**
     * Called when the user enter something in the search field;
     * It search name of the depot
     */
    private void search(){
    	searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        	/*if (!newValue.isEmpty()){
		        depotsTable.setItems(FXCollections.observableArrayList(depotsController.searchDepot(newValue)));
        	}else depotsTable.setItems(depotsController.getDepots());*/
        });
    }
    
    /**
	 * It listen for selection changes to disable/enable the delete button 
	 * when the user selects something in the table
	 */
	private void update(){/*
        delete.setDisable(true);
        depotsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        	delete.setDisable(false);
        } );*/
	}
}
