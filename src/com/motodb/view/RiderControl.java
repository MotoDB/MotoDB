package com.motodb.view;

import java.util.ArrayList;
import java.util.List;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.controller.TeamManager;
import com.motodb.controller.TeamManagerImpl;
import com.motodb.model.Championship;
import com.motodb.model.Rider;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.RiderGridPane;
import com.motodb.view.util.PersistentButtonToggleGroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RiderControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();
    
    // Controller
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final MemberManager riderManager = new MemberManagerImpl();

    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup classesButtons = new PersistentButtonToggleGroup();
    
    @FXML
    private HBox mainPane;
    @FXML
    private Button addRacingRider;
    @FXML
    private HBox years,classes,teams;
    @FXML
    private TextField searchField;
    
    private List<RiderGridPane> list = new ArrayList<>();


	GridPane grid = new GridPane();

    public RiderControl() {
        super();

        for (Championship c : championshipManager.getChampionships()) {
        	ToggleButton button = new ToggleButton(Integer.toString(c.getYear()));
            button.setToggleGroup(yearsButtons);
            button.setUserData(c.getYear());
        }

        if (!yearsButtons.getToggles().isEmpty()) {
            yearsButtons.getToggles().get(0).setSelected(true);
            
            // Creating a button for each class of that year, and adding such button to the group
            for (String s : championshipManager.getClassesNames((Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))) {
                ToggleButton button = new ToggleButton(s);
                button.setToggleGroup(classesButtons);
                button.setUserData(s);
            }
            
            if (!classesButtons.getToggles().isEmpty()) {
            	classesButtons.getToggles().get(0).setSelected(true);
            }
        }
    }

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {

        // Adding the buttons created in the constructor to the hBox, before the button
        for (Toggle button : yearsButtons.getToggles()) {
            years.getChildren().add(yearsButtons.getToggles().indexOf(button), (ToggleButton)button);
        }
        for (Toggle button : classesButtons.getToggles()) {
            button.setToggleGroup(classesButtons);
            classes.getChildren().add(classesButtons.getToggles().indexOf(button), (ToggleButton)button);
        }
       
        // Method which handles the selection of a year
        this.filter();

        if (!classesButtons.getToggles().isEmpty()) {
        	classesButtons.getToggles().get(0).setSelected(true);
        	
        }
             
    }

    /**
     * Called when the user selects a toggle-button to filter classes so
     * there are only buttons for each class of that year
     */
    private void filter() {
    	
        yearsButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

            	if(!riderManager.getRidersFromClassAndYear(classesButtons.getSelectedToggle().getUserData().toString(), (Integer.parseInt(newValue.getUserData().toString()))).isEmpty()){
            		mainPane.getChildren().clear();
            	}
            	
                classesButtons.getToggles().clear();
                classes.getChildren().clear();

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (String s : championshipManager.getClassesNames((Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))) {
                    ToggleButton button = new ToggleButton(s);
                    button.setToggleGroup(classesButtons);
                    button.setUserData(s);
                }
                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (Toggle button : classesButtons.getToggles()) {
                    button.setToggleGroup(classesButtons);
                    classes.getChildren().add(classesButtons.getToggles().indexOf(button), (ToggleButton)button);
                }

                
                classesButtons.getToggles().get(0).setSelected(true);
                    
                
            }

        });
        
        classesButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            	list.clear();
	            
            	if(!riderManager.getRidersFromClassAndYear(newValue.getUserData().toString(), (Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()))).isEmpty()){
	        		
            		for(Rider rider : riderManager.getRidersFromClassAndYear(newValue.getUserData().toString(), (Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))){
		     	    	RiderGridPane riderPane = new RiderGridPane(rider);
		     	    	list.add(riderPane);
	            		
		     	    }
		
		         	int i=0;
		         	int riders=0;
		         	
		     	    while(i<((list.size())/4)){
		     	    	int j=0;
		     	    	while(j<=3){
		     		        grid.add(list.get(riders).getPane(), j, i);
		     		        j++;
		     		        riders++;
		     	    	}
		     	    	i++;
		     	   }
		            mainPane.getChildren().add(grid);    
		            	
	            	
	            }else{
	            	mainPane.getChildren().clear();
	            }
            	
            }
        });
     
    }
    	 
       
}
