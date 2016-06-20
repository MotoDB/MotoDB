package com.motodb.view.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.motodb.model.Circuit;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CircuitGridPane {

    GridPane singleSponsor = new GridPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();

    Label name = new Label();
    ImageView img;

    public CircuitGridPane(Circuit circuit) {

        singleSponsor.setPadding(new Insets(5));
        singleSponsor.setHgap(10);
        singleSponsor.setVgap(12);
        singleSponsor.setMinSize((width - 100) / 4, (width - 100) / 4);
        singleSponsor.setMinHeight(300);
        this.name.setText(circuit.getName());
        this.getPane().setCache(true);
        if (!circuit.getPhoto().isEmpty()) {
            img = new ImageView(new Image(circuit.getPhoto(), true));
            singleSponsor.add(img, 5, 0);
        }
        singleSponsor.add(this.name, 5, 1);

    }

    public GridPane getPane() {
        return singleSponsor;
    }

}
