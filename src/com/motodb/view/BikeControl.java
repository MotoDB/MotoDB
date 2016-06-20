package com.motodb.view;

import java.util.ArrayList;
import java.util.List;

import com.motodb.controller.BikeManager;
import com.motodb.controller.BikeManagerImpl;
import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.controller.TeamManager;
import com.motodb.controller.TeamManagerImpl;
import com.motodb.model.Bike;
import com.motodb.model.Championship;
import com.motodb.model.Rider;
import com.motodb.model.Team;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.RiderGridPane;
import com.motodb.view.util.BikeGridPane;
import com.motodb.view.util.PersistentButtonToggleGroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class BikeControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();
    private final BikeManager bikeManager = new BikeManagerImpl();
    private final TeamManager teamManager = new TeamManagerImpl();

    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup teamsButtons = new PersistentButtonToggleGroup();

    @FXML
    private HBox mainPane;
    @FXML
    private HBox years, teams, bikes;

    private List<BikeGridPane> list = new ArrayList<>();

    GridPane grid = new GridPane();

    public BikeControl() {
        // super();

        for (Championship c : championshipManager.getChampionships()) {
            ToggleButton button = new ToggleButton(Integer.toString(c.getYear()));
            button.setToggleGroup(yearsButtons);
            button.setUserData(c.getYear());
        }

        if (!yearsButtons.getToggles().isEmpty()) {
            yearsButtons.getToggles().get(0).setSelected(true);

            // Creating a button for each class of that year, and adding such
            // button to the group
            for (Team t : teamManager.getTeamsFromYear(Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()))) {
                ToggleButton button = new ToggleButton(t.getName());
                button.setToggleGroup(teamsButtons);
                button.setUserData(t.getName());
            }

        }
    }

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {

        // Adding the buttons created in the constructor to the hBox, before the
        // button
        for (Toggle button : yearsButtons.getToggles()) {
            years.getChildren().add(yearsButtons.getToggles().indexOf(button), (ToggleButton) button);
        }
        for (Toggle button : teamsButtons.getToggles()) {
            button.setToggleGroup(teamsButtons);
            teams.getChildren().add(teamsButtons.getToggles().indexOf(button), (ToggleButton) button);
        }

        // Method which handles the selection of a year
        this.filter();
        if (!yearsButtons.getToggles().isEmpty()) {
            yearsButtons.getToggles().get(0).setSelected(true);
            if (!teamsButtons.getToggles().isEmpty()) {
                teamsButtons.getToggles().get(0).setSelected(true);
            }
        }

    }

    /**
     * Called when the user selects a toggle-button to filter classes so there
     * are only buttons for each class of that year
     */
    private void filter() {
        yearsButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                list.clear();
                mainPane.getChildren().clear();

                teamsButtons.getToggles().clear();
                teams.getChildren().clear();

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (String s : championshipManager.getClassesNames(
                        (Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))) {
                    ToggleButton button = new ToggleButton(s);
                    button.setToggleGroup(teamsButtons);
                    button.setUserData(s);
                }

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (Toggle button : teamsButtons.getToggles()) {
                    button.setToggleGroup(teamsButtons);
                    teams.getChildren().add(teamsButtons.getToggles().indexOf(button), (ToggleButton) button);
                }
                teamsButtons.getToggles().get(1).setSelected(true);

                if (!teamsButtons.getToggles().isEmpty()) {
                    teamsButtons.getToggles().get(0).setSelected(true);
                }
            }
        });

        teamsButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                mainPane.getChildren().clear();

                if (newValue != null) {
                    if (!bikeManager.getBikes().isEmpty()) {

                        list.clear();
                        for (Bike bike : bikeManager.getBikes()) {
                            BikeGridPane riderPane = new BikeGridPane(bike);
                            list.add(riderPane);
                        }

                        grid = new GridPane();

                        int i = 0, riders = 0;

                        while (i < (int) Math.ceil(list.size() / (4.0)) || riders < list.size()) {
                            int j = 0;
                            while (j < 4 && riders < list.size()) {
                                grid.add(list.get(riders).getPane(), j, i);
                                riders++;
                                j++;
                            }
                            i++;
                        }
                        mainPane.getChildren().add(grid);

                    } else {
                        mainPane.getChildren().clear();
                    }
                }

            }
        });

    }
}
