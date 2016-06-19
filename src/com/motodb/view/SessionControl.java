package com.motodb.view;

import java.sql.Date;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ClaxManager;
import com.motodb.controller.ClaxManagerImpl;
import com.motodb.controller.RacingRiderManager;
import com.motodb.controller.RacingRiderManagerImpl;
import com.motodb.controller.SessionManager;
import com.motodb.controller.SessionManagerImpl;
import com.motodb.controller.WeekendManager;
import com.motodb.controller.WeekendManagerImpl;
import com.motodb.model.Championship;
import com.motodb.model.Clax;
import com.motodb.model.RacingRider;
import com.motodb.model.Session;
import com.motodb.model.Weekend;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;
import com.motodb.view.util.PersistentButtonToggleGroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class SessionControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final ChampionshipManager manager = new ChampionshipManagerImpl();
    private final RacingRiderManager racingRiderManager = new RacingRiderManagerImpl();
    private final WeekendManager weekendManager = new WeekendManagerImpl();
    private final ClaxManager classManager = new ClaxManagerImpl();
    private final SessionManager sessionManager = new SessionManagerImpl();

    // ToggleGroup to have just one toggleButton selected at a time
    private final ToggleGroup yearsButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup weeksButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup classesButtons = new PersistentButtonToggleGroup();
    private final ToggleGroup sessionsButtons = new PersistentButtonToggleGroup();

    @FXML
    private TableView<RacingRider> sessionTable;
    @FXML
    private TableColumn<RacingRider, String> riderColumn, sessionColumn, positionColumn, pointsColumn, classColumn;

    @FXML
    private HBox years, weekend, clax, session;
    @FXML
    private TextField searchField;

    public SessionControl() {
        super();

        for (Championship c : manager.getChampionships()) {
            ToggleButton button = new ToggleButton(Integer.toString(c.getYear()));
            button.setToggleGroup(yearsButtons);
            button.setUserData(c.getYear());
        }

        if (!yearsButtons.getToggles().isEmpty()) {
            yearsButtons.getToggles().get(0).setSelected(true);

            // Creating a button for each class of that year, and adding such
            // button to the group
            for (Weekend s : weekendManager.getWeekendsFromYear(
                    (Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))) {
                ToggleButton button = new ToggleButton(s.getStartDate().toString());
                button.setToggleGroup(weeksButtons);
                button.setUserData(s.getStartDate().toString());
            }
            
            for (Clax s : classManager.getClassesFromYear(Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()))) {

                ToggleButton button = new ToggleButton(s.getName());
                button.setToggleGroup(classesButtons);
                button.setUserData(s.getName());
            }

            if (!weeksButtons.getToggles().isEmpty()) {
                weeksButtons.getToggles().get(0).setSelected(true);
                
                if (!classesButtons.getToggles().isEmpty()) {
                	classesButtons.getToggles().get(0).setSelected(true);}

                // Creating a button for each class of that year, and adding
                // such button to the group
                for (Session s : sessionManager.getSessionByWeekendAndClass(classesButtons.getSelectedToggle().getUserData().toString(),
                        Date.valueOf(weeksButtons.getSelectedToggle().getUserData().toString()))) {

                    ToggleButton button = new ToggleButton(s.getCode());
                    button.setToggleGroup(sessionsButtons);
                    button.setUserData(s.getCode());
                }

            }
        }

    }

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {

        // Initialize the table
        riderColumn.setCellValueFactory(cellData -> cellData.getValue().personalCodeProperty().asString());
        classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        sessionColumn.setCellValueFactory(cellData -> cellData.getValue().sessionCodeProperty());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty().asString());
        pointsColumn.setCellValueFactory(cellData -> cellData.getValue().pointsProperty().asString());

        // sessionTable.setItems(racingRiderManager.getRidersFromYearWeekSess(2016,
        // , String session));

        // Adding the buttons created in the constructor to the hBox, before the
        // button
        for (Toggle button : yearsButtons.getToggles()) {
            years.getChildren().add(yearsButtons.getToggles().indexOf(button), (ToggleButton) button);
        }
        for (Toggle button : classesButtons.getToggles()) {
            button.setToggleGroup(classesButtons);
            clax.getChildren().add(classesButtons.getToggles().indexOf(button), (ToggleButton) button);
        }
        for (Toggle button : weeksButtons.getToggles()) {
            button.setToggleGroup(weeksButtons);
            weekend.getChildren().add(weeksButtons.getToggles().indexOf(button), (ToggleButton) button);
        }
        for (Toggle button : sessionsButtons.getToggles()) {
            button.setToggleGroup(sessionsButtons);
            session.getChildren().add(sessionsButtons.getToggles().indexOf(button), (ToggleButton) button);
        }

        // Method which handles the selection of a year
        this.filter();

        if (!weeksButtons.getToggles().isEmpty()) {
            weeksButtons.getToggles().get(0).setSelected(true);
            if (!sessionsButtons.getToggles().isEmpty()) {
                sessionsButtons.getToggles().get(0).setSelected(true);
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

                weeksButtons.getToggles().clear();
                weekend.getChildren().clear();
                
                classesButtons.getToggles().clear();
                clax.getChildren().clear();
                
                for (Clax c : classManager.getClassesFromYear(Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()))) {
                    ToggleButton button = new ToggleButton(c.getName());
                    button.setToggleGroup(classesButtons);
                    button.setUserData(c.getName());
                }

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (Toggle button : classesButtons.getToggles()) {
                    button.setToggleGroup(classesButtons);
                    clax.getChildren().add(classesButtons.getToggles().indexOf(button), (ToggleButton) button);
                }

                if (!classesButtons.getToggles().isEmpty()) {
                	classesButtons.getToggles().get(0).setSelected(true);
                }
                
                // Creating a button for each class of that year, and adding
                // such button to the group
                for (Weekend s : weekendManager.getWeekendsFromYear(
                        (Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString())))) {
                    ToggleButton button = new ToggleButton(s.getStartDate().toString());
                    button.setToggleGroup(weeksButtons);
                    button.setUserData(s.getStartDate().toString());
                }

                // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                for (Toggle button : weeksButtons.getToggles()) {
                    button.setToggleGroup(weeksButtons);
                    weekend.getChildren().add(weeksButtons.getToggles().indexOf(button), (ToggleButton) button);
                }

                if (!weeksButtons.getToggles().isEmpty()) {
                    weeksButtons.getToggles().get(0).setSelected(true);
                }
            }

        });

        weeksButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                sessionsButtons.getToggles().clear();
                session.getChildren().clear();

                if (!weeksButtons.getToggles().isEmpty()) {

                    if (!classesButtons.getToggles().isEmpty()) {
                    	classesButtons.getToggles().get(0).setSelected(true);}

                    // Creating a button for each class of that weekend, and
                    // adding such button to the group
                    for (Session s : sessionManager.getSessionByWeekendAndClass(classesButtons.getSelectedToggle().getUserData().toString(),
                            Date.valueOf(weeksButtons.getSelectedToggle().getUserData().toString()))) {
                        ToggleButton button = new ToggleButton(s.getCode());
                        button.setToggleGroup(sessionsButtons);
                        button.setUserData(s.getCode());
                    }

                    // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                    for (Toggle button : sessionsButtons.getToggles()) {
                        button.setToggleGroup(sessionsButtons);
                        session.getChildren().add(sessionsButtons.getToggles().indexOf(button), (ToggleButton) button);
                    }

                    if (!sessionsButtons.getToggles().isEmpty()) {
                        sessionsButtons.getToggles().get(0).setSelected(true);
                    }
                }

            }
        });
        
        classesButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                sessionsButtons.getToggles().clear();
                session.getChildren().clear();

                if (!weeksButtons.getToggles().isEmpty() && !classesButtons.getToggles().isEmpty()) {

                    // Creating a button for each class of that weekend, and
                    // adding such button to the group
                    for (Session s : sessionManager.getSessionByWeekendAndClass(classesButtons.getSelectedToggle().getUserData().toString(),
                            Date.valueOf(weeksButtons.getSelectedToggle().getUserData().toString()))) {
                        ToggleButton button = new ToggleButton(s.getCode());
                        button.setToggleGroup(sessionsButtons);
                        button.setUserData(s.getCode());
                    }

                    // THIS CODE IS COPIED FROM TOP, IT NEEDS TO BE REFACTORED
                    for (Toggle button : sessionsButtons.getToggles()) {
                        button.setToggleGroup(sessionsButtons);
                        session.getChildren().add(sessionsButtons.getToggles().indexOf(button), (ToggleButton) button);
                    }

                    if (!sessionsButtons.getToggles().isEmpty()) {
                        sessionsButtons.getToggles().get(0).setSelected(true);
                    }
                }

            }
        });

        sessionsButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    if (!racingRiderManager.getRidersFromYearWeekSess(
                            Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()),
                            Date.valueOf(weeksButtons.getSelectedToggle().getUserData().toString()),
                            sessionsButtons.getSelectedToggle().getUserData().toString()).isEmpty()) {
                        sessionTable.setItems(racingRiderManager.getRidersFromYearWeekSess(
                                Integer.parseInt(yearsButtons.getSelectedToggle().getUserData().toString()),
                                Date.valueOf(weeksButtons.getSelectedToggle().getUserData().toString()),
                                sessionsButtons.getSelectedToggle().getUserData().toString()));
                    } else {
                        sessionTable.setItems(null);
                    }
                } else {
                    sessionTable.setItems(null);
                }
            }
        });
        
        

    }
}
