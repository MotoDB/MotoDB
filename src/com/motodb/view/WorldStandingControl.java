/**
 * 'worldStanding.fxml' Control Class
 */

package com.motodb.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.DBManager;
import com.motodb.model.Championship;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.PersistentButtonToggleGroup;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class WorldStandingControl extends ScreenControl {
	
	// Reference to the controller
	//private final DepotsController depotsController = DepotsController.getInstanceOf();
	
	// Aler panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();
    
    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtonsGroup = new PersistentButtonToggleGroup();
    private final ToggleGroup classesButtonsGroup = new PersistentButtonToggleGroup();
    private final List<ToggleButton> yearsButtonsList = new ArrayList<>();
    private final List<ToggleButton> classesButtonsList = new ArrayList<>();
    
    private final ChampionshipManager manager = new ChampionshipManager();
    private final List<Championship> championships = manager.showChampionship();
    private final List<Integer> yearsList = new ArrayList<>();
    private final List<Integer> classesList = new ArrayList<>();

	/*@FXML
	private TableView<Entry<StandardBookImpl,Integer>> worldStandingTable;
	
	@FXML
	private TableColumn<Entry<StandardBookImpl,Integer>, String> positionColumn, riderColumn, bikeColumn,
	nationColumn, pointsColumn;*/
	
	@FXML
	private HBox years;
	
	@FXML
	private HBox classes;
	
	@FXML
	private TextField searchField;
    
	public WorldStandingControl(){
		super();
		
		championships.forEach(e -> {
			yearsList.add(e.getYear());
	    });
	                
	                
	   
		// Creating a button for each CLASS and adding such button to the group and to the list
		/*for(String c : classesList){
			ToggleButton button = new ToggleButton(c);
			button.setToggleGroup(classesButtonsGroup);
			classesButtonsList.add(button);
			button.setUserData(c);
		}*/
		for(Integer y : yearsList){
			ToggleButton button = new ToggleButton(y.toString());
			button.setToggleGroup(yearsButtonsGroup);
			yearsButtonsList.add(button);
			button.setUserData(y);
		}		
		
	}
	
	/**
     * Called after the fxml file has been loaded; this method initializes 
     * the fxml control class. 
     */
	public void initialize() {
		
		// Adding the buttons created in the constructor to the hBox, after the title
		int hBoxPos=0; // hBoxPos is 0 because I need to add buttons before the elements already there
		for(ToggleButton button : yearsButtonsList){	
			years.getChildren().add(hBoxPos, button);
			hBoxPos++; // the other buttons will be added after the one I just added
		}
		hBoxPos=0;
		for(ToggleButton button : classesButtonsList){
			button.setToggleGroup(classesButtonsGroup);
			classes.getChildren().add(hBoxPos, button);
			hBoxPos++; // the other buttons will be added after the one I just added
		}
		
		// Initializing the table
        /*positionColumn.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        riderColumn.setCellValueFactory(cellData -> cellData.getValue().getKey().isbnProperty());
        bikeColumn.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        nationColumn.setCellValueFactory(cellData -> cellData.getValue().getKey().yearProperty().asString());
        pointsColumn.setCellValueFactory(cellData -> cellData.getValue().getKey().pagesProperty().asString());*/
		
        // Method which handle the selection of a depot
        this.filter();
        
        // Use a 'searchField' to search for books in the tableView
        //this.search();
        
        // Selecting the first depot of the list for the first time the user opens the screen
        if(!yearsButtonsList.isEmpty()) yearsButtonsList.get(0).setSelected(true);
        
        if(!classesButtonsList.isEmpty()) classesButtonsList.get(0).setSelected(true);
        
        // Putting data into the table
        //worldStandingTable.setItems(data);
	}
	
	/**
	 * Called when the user selects a toggle-button 
	 * Method to filter CLASSES so in the table there are only the books from the selected depot
	 */
	private void filter(){
		
		yearsButtonsGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if(newValue==null){
					// When there are no buttons toggled, the table is empty
					//data.clear();
				} else{
					// The table is cleaned
					//data.clear();
					// Data is added to the table, relatively to the toggle button selected
					//data.addAll(depotsController.filterDepot((String)yearsButtonsGroup.getSelectedToggle().getUserData()).findFirst().get().getBooks().entrySet());
				}
			}
		});
	}
	
	//@TODO Search Method
}
