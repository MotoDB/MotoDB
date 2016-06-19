package com.motodb.view.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.motodb.model.Rider;
import com.motodb.model.Sponsor;
import com.motodb.model.Team;
import com.motodb.view.ScreenControl;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TeamGridPane extends ScreenControl {

    VBox internalVBox = new VBox();
    HBox labelsHBox = new HBox();
    VBox singleLabels = new VBox();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();

    Label name = new Label();
    Label year = new Label();
    Label location = new Label();
    ImageView img;

    public TeamGridPane(Team team, ObservableList<Sponsor> sponsors, ObservableList<Rider> riders) {
        super();
        internalVBox.setPadding(new Insets(40));
        internalVBox.setMaxWidth(700);

        internalVBox.setAlignment(Pos.CENTER);

        this.name.setText(team.getName());
        name.setPadding(new Insets(15,0,10,0));
        name.setStyle("-fx-font-size: 22; -fx-font-weight: bold");
        
        this.year.setText("Year: " + team.getYear());
        year.setPadding(new Insets(3,0,3,0));
        year.setStyle("-fx-font-size: 13");
        
        this.location.setText("Location: " + team.getLocation());
        location.setPadding(new Insets(3,0,15,0));
        location.setStyle("-fx-font-size: 13");
        
        HBox logos = new HBox();
        logos.setAlignment(Pos.CENTER_LEFT);

    	logos.setCache(true);
    	
        this.getPane().setCache(true);

        if (!team.getLogo().isEmpty()) {
            img = new ImageView(new Image(team.getLogo(), true));
            img.setFitHeight(180);
            img.setFitWidth(180);

            img.setPreserveRatio(true);
            img.prefWidth(100);
            img.maxHeight(100);
            img.maxWidth(100);
            internalVBox.getChildren().add(img);
        }

        labelsHBox.setAlignment(Pos.CENTER);
        singleLabels.setAlignment(Pos.CENTER);
        internalVBox.getChildren().add(labelsHBox);
        labelsHBox.getChildren().add(singleLabels);

        singleLabels.getChildren().add(name);
        singleLabels.getChildren().add(year);
        singleLabels.getChildren().add(location);
        
        for(Rider m : riders){
        	
        	  Label rider = new Label(m.getRole()+": " +m.getFirstName()+ " "+ m.getLastName());
        	  rider.setPadding(new Insets(3,0,3,0));
              location.setStyle("-fx-font-size: 14");
              singleLabels.getChildren().add(rider);
        }

        for(Sponsor s : sponsors){
        	if(!s.getLogo().isEmpty()){
	            ImageView sponsorImg;
	            sponsorImg = new ImageView(new Image(s.getLogo(), true));
	            sponsorImg.setFitHeight(50);
	            sponsorImg.setFitWidth(50);
	
	            sponsorImg.setPreserveRatio(true);
	            sponsorImg.prefWidth(50);
	            sponsorImg.maxHeight(50);
	            sponsorImg.maxWidth(50);
	            singleLabels.getChildren().add(sponsorImg);
        	}
        }
       
       // singleLabels.getChildren().add(logos);
    }

    public VBox getPane() {
        return internalVBox;
    }

}
