package com.motodb.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SponsorControl extends ScreenControl{

    @FXML
    private TextField searchField;
    @FXML 
    private Button addTyre, addBrand, addSponsor;
    
    public SponsorControl() {
        super();
    }
    
    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {
    	
    }
    
}