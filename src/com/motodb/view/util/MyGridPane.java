package com.motodb.view.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.motodb.model.Rider;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MyGridPane {
	
	GridPane singleRider = new GridPane();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    
    Label name = new Label();
    Label birthPlace = new Label();
    Label date = new Label();
    Label info = new Label();
    Label number = new Label();
    Label role = new Label();
    ImageView img;
    
	public MyGridPane(Rider rider){
		
		singleRider.setPadding(new Insets(5));
	    singleRider.setHgap(10);
	    singleRider.setVgap(12);
	    singleRider.setMinSize((width-100)/4, (width-100)/4);
	    singleRider.setMinHeight(300);
	    this.name.setText("Name: " + rider.getFirstName()+ " " + rider.getLastName());
	    this.info.setText("Weight: " + rider.getWeight()+ "  Height: " + rider.getHeight());
	    this.number.setText("Number: " + rider.getNumber());
	    this.role.setText(rider.getRole());
	    this.getPane().setCache(true);
	    if(!rider.getPhoto().isEmpty()){
		    img = new ImageView(new Image(rider.getPhoto(), true));
		    singleRider.add(img, 5, 0);
	    }
	    singleRider.add(this.name, 5, 1);
	    singleRider.add(this.info, 5, 2);
	    singleRider.add(this.number, 5, 3);
	    singleRider.add(this.role, 5, 4);
	    
	}
    
    public GridPane getPane(){
    	return singleRider;
    }
    
}
