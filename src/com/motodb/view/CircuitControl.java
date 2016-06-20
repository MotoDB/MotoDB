package com.motodb.view;

import java.util.ArrayList;
import java.util.List;

import com.motodb.controller.CircuitManager;
import com.motodb.controller.CircuitManagerImpl;
import com.motodb.model.Circuit;
import com.motodb.view.util.CircuitGridPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CircuitControl extends ScreenControl {

    @FXML
    private Button addTyre, addBrand, addSponsor;

    private CircuitManager circuitManager = new CircuitManagerImpl();
    private List<CircuitGridPane> list = new ArrayList<>();

    @FXML
    private HBox mainPane;

    public CircuitControl() {
        super();
    }

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {

        list.clear();

        GridPane grid = new GridPane();

        for (Circuit circuit : circuitManager.getCircuits()) {
            CircuitGridPane sponsorPane = new CircuitGridPane(circuit);
            list.add(sponsorPane);
        }

        int i = 0;
        int riders = 0;

        while (i < ((list.size()) / 4)) {
            int j = 0;
            while (j <= 3) {
                grid.add(list.get(riders).getPane(), j, i);
                j++;
                riders++;
            }
            i++;
        }
        mainPane.getChildren().add(grid);

    }

}