/**
 * 'worldStanding.fxml' Control Class
 */

package com.motodb.view;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import com.motodb.controller.ChampionshipManager;
import com.motodb.model.Championship;
import com.motodb.model.Classes;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.PersistentButtonToggleGroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class WorldStandingControl extends ScreenControl {
	
	// Aler panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();
    
    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtonsGroup = new PersistentButtonToggleGroup();
    private final ToggleGroup classesButtonsGroup = new PersistentButtonToggleGroup();
    private final List<ToggleButton> yearsButtonsList = new ArrayList<>();
    private final ObservableList<ToggleButton> classesButtonsList = FXCollections.observableArrayList();
    
    private final ChampionshipManager manager = new ChampionshipManager();
    private List<Championship> championships  = manager.showChampionship();
    private final List<Integer> yearsList = new ArrayList<>();
    private final ObservableList<String> classesList = FXCollections.observableArrayList();

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
		
		for(Championship c: manager.showChampionship()){
			yearsList.add(c.getYear());
		}
		
		for(Integer y : yearsList){
			ToggleButton button = new ToggleButton(y.toString());
			button.setToggleGroup(yearsButtonsGroup);
			yearsButtonsList.add(button);
			button.setUserData(y);
		}	
		
		if(!yearsButtonsList.isEmpty()) yearsButtonsList.get(0).setSelected(true);
		
		for(Classes c: manager.showClasses(Integer.parseInt(yearsButtonsGroup.getSelectedToggle().getUserData().toString()))){
			classesList.add(c.getName());
		}
		
		// Creating a button for each CLASS and adding such button to the group and to the list
		for(String c : classesList){
			ToggleButton button = new ToggleButton(c);
			button.setToggleGroup(classesButtonsGroup);
			classesButtonsList.add(button);
			button.setUserData(c);
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
					//data.clear();
				} else{
					// The table is cleaned
					classesList.clear();
					classesButtonsList.clear();
					classes.getChildren().clear();
					
					for(Classes c: manager.showClasses(Integer.parseInt(yearsButtonsGroup.getSelectedToggle().getUserData().toString()))){
						classesList.add(c.getName());
					}
					
					// Creating a button for each CLASS and adding such button to the group and to the list
					for(String c : classesList){
						ToggleButton button = new ToggleButton(c);
						button.setToggleGroup(classesButtonsGroup);
						classesButtonsList.add(button);
						button.setUserData(c);
					}
					
					int hBoxPos=0;
					for(ToggleButton button : classesButtonsList){
						button.setToggleGroup(classesButtonsGroup);
						classes.getChildren().add(hBoxPos, button);
						hBoxPos++; // the other buttons will be added after the one I just added
					}
					
				}
			}
		});
	}
	
	//@TODO Search Method
}
