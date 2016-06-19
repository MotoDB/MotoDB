package com.motodb.view.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.motodb.model.Team;
import com.motodb.view.ScreenControl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TeamGridPane extends ScreenControl {

    HBox externHBox = new HBox();
    VBox internalVBox = new VBox();
    HBox labelsHBox = new HBox();
    VBox singleLabels = new VBox();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();

    Label name = new Label();
    Label year = new Label();
    Label location = new Label();
    ImageView img;

    public TeamGridPane(Team team) {
        super();
        externHBox.setPadding(new Insets(20));
        externHBox.setMaxWidth(700);

        externHBox.setAlignment(Pos.CENTER);

        this.name.setText(team.getName());
        this.year.setText("Year: " + team.getYear());
        this.location.setText("Number: " + team.getLocation());
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

        labelsHBox.setAlignment(Pos.CENTER_LEFT);
        singleLabels.setAlignment(Pos.CENTER_LEFT);
        internalVBox.getChildren().add(labelsHBox);
        labelsHBox.getChildren().add(singleLabels);

        singleLabels.getChildren().add(name);
        singleLabels.getChildren().add(year);
        singleLabels.getChildren().add(location);

        externHBox.getChildren().add(internalVBox);
    }

    public HBox getPane() {
        return externHBox;
    }

}
