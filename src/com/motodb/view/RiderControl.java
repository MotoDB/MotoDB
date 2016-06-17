package com.motodb.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
import com.motodb.view.util.MyGridPane;
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
import javafx.scene.layout.VBox;

public class RiderControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();
    
    // Controller
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final TeamManager manager = new TeamManagerImpl();
    private final MemberManager riderManager = new MemberManagerImpl();

    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup classesButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup teamsButtons = new PersistentButtonToggleGroup();
    @FXML
    private VBox mainPane;
    @FXML
    private Button addRacingRider;
    @FXML
    private HBox years,classes,teams;
    @FXML
    private TextField searchField;
    
    private List<MyGridPane> list = new ArrayList<>();

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
            	
            	// Creating a button for each team of that year, and adding such button to the group
            	for (String s : manager.getTeamsByYearAndClass((Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())), classesButtons.getSelectedToggle().getUserData().toString())) {
                    ToggleButton button = new ToggleButton(s);
                    button.setToggleGroup(teamsButtons);
                    button.setUserData(s);
                }
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
        for (Toggle button : teamsButtons.getToggles()) {
            button.setToggleGroup(teamsButtons);
            teams.getChildren().add(teamsButtons.getToggles().indexOf(button), (ToggleButton)button);
        }

        // Method which handles the selection of a year
        this.filter();

        if (!classesButtons.getToggles().isEmpty()) {
        	classesButtons.getToggles().get(0).setSelected(true);
        	if (!teamsButtons.getToggles().isEmpty()) {
            	teamsButtons.getToggles().get(0).setSelected(true);
            }
        }
      /*
        GridPane grid = new GridPane();
        
	    for(Rider rider : riderManager.getRiders()){
	    	MyGridPane riderPane = new MyGridPane(rider);
	    	list.add(riderPane);
	    }

    	int i=0;
    	ListIterator<MyGridPane> it = list.listIterator();
    	MyGridPane temp =it.next();
	    while(it.hasNext()){
	    	int j=0;
	    	while(j<=3){
		        grid.add(temp.getPane(), j, i);
		        temp=it.next();
		        j++;
	    	}
	    	i++;
	   }
	    
        mainPane.getChildren().add(4, grid);*/
    }

    /**
     * Called when the user selects a toggle-button to filter classes so
     * there are only buttons for each class of that year
     */
    private void filter() {
    	
        yearsButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            	
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
            }
        });
        
        classesButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            	
                teamsButtons.getToggles().clear();
                teams.getChildren().clear();

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (String s : manager.getTeamsByYearAndClass((Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())), classesButtons.getSelectedToggle().getUserData().toString())) {
                    ToggleButton button = new ToggleButton(s);
                    button.setToggleGroup(teamsButtons);
                    button.setUserData(s);
                }
                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (Toggle button : teamsButtons.getToggles()) {
                    button.setToggleGroup(teamsButtons);
                    teams.getChildren().add(teamsButtons.getToggles().indexOf(button), (ToggleButton)button);
                }
                
                if (!classesButtons.getToggles().isEmpty()) {
                	if (!teamsButtons.getToggles().isEmpty()) {
                    	teamsButtons.getToggles().get(0).setSelected(true);
                    }
                }
            }
        });
        
        classesButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            	list.clear();
            	
            	GridPane grid = new GridPane();

         	    for(Rider rider : riderManager.getRiders()){
         	    	MyGridPane riderPane = new MyGridPane(rider);
         	    	list.add(riderPane);
         	    }

             	int i=0;
             	ListIterator<MyGridPane> it = list.listIterator();
             	MyGridPane temp =it.next();
         	    while(it.hasNext()){
         	    	int j=0;
         	    	while(j<=3){
         		        grid.add(temp.getPane(), j, i);
         		        temp=it.next();
         		        j++;
         	    	}
         	    	i++;
         	   }
         	    
                 mainPane.getChildren().add(4, grid);
               
            }
        });
     
    }
}
