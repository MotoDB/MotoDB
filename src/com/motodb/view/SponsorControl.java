package com.motodb.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.model.Sponsor;
import com.motodb.view.util.SponsorGridPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SponsorControl extends ScreenControl{

    @FXML
    private TextField searchField;
    @FXML 
    private Button addTyre, addBrand, addSponsor;

    private SponsorManager sponsorManager = new SponsorManagerImpl();
    private List<SponsorGridPane> list = new ArrayList<>();
    
    @FXML 
    private HBox mainPane;
    
    public SponsorControl() {
        super();
    }
    
    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {
    	
    	list.clear();
    	
    	GridPane grid = new GridPane();

 	    for(Sponsor sponsor : sponsorManager.getSponsors()){
 	    	SponsorGridPane sponsorPane = new SponsorGridPane(sponsor);
 	    	list.add(sponsorPane);
 	    }

     	int i=0;
     	ListIterator<SponsorGridPane> it = list.listIterator();
     	SponsorGridPane temp =it.next();
 	    while(it.hasNext()){
 	    	int j=0;
 	    	while(j<=3){
 		        grid.add(temp.getPane(), j, i);
 		        temp=it.next();
 		        j++;
 	    	}
 	    	i++;
 	   }
 	    
         mainPane.getChildren().add(0, grid);
 
    }
    
}