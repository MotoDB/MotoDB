package com.motodb.view;

import java.time.LocalDate;
import java.util.Arrays;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.ClaxManager;
import com.motodb.controller.ClaxManagerImpl;
import com.motodb.controller.SessionManager;
import com.motodb.controller.SessionManagerImpl;
import com.motodb.controller.WeekendManager;
import com.motodb.controller.WeekendManagerImpl;
import com.motodb.model.Clax;
import com.motodb.model.Session;
import com.motodb.model.Weekend;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class AddSessionControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final SessionManager sessionManager = new SessionManagerImpl();
    private final ClaxManager classManager = new ClaxManagerImpl();
    private final WeekendManager weekendManager = new WeekendManagerImpl();
    private final ChampionshipManager championshipManager = new ChampionshipManagerImpl();

    @FXML
    private TableView<Session> sessionsTable;
    @FXML
    private TableColumn<Session, String> yearColumn, codeColumn, typeColumn, classColumn, startDateColumn,
            airTempColumn, groundTempColumn, humColumn, conditionsColumn, lapsColumn, durationColumn;
    @FXML
    private TextField durationField, lapsField, humidityField, groundTemperatureField, airTemperatureField, codeField,
            conditionsField, searchField;
    @FXML
    private ComboBox<String> typeBox, yearBox, codeBox;
    @FXML
    private ComboBox<Weekend> weekendBox;
    @FXML
    private ComboBox<Clax> classBox;
    @FXML
    private DatePicker startDate;

    @FXML
    private Button delete;
    @FXML
    private VBox vBoxFields;

    public enum SessionType {

        WUP("Warm Up", FXCollections.observableArrayList(Arrays.asList("WP"))), FP("Free Practice",
                FXCollections.observableArrayList(Arrays.asList("FP1", "FP2", "FP3", "FP4"))), Q("Qualification",
                        FXCollections.observableArrayList(Arrays.asList("Q1", "Q2"))), Gara("Race",
                                FXCollections.observableArrayList(Arrays.asList("RACE")));

        private String type;
        private ObservableList<String> codes;

        private SessionType(String type, ObservableList<String> codes) {
            this.type = type;
            this.codes = codes;
        }

        public String getType() {
            return type;
        }

        public ObservableList<String> getCodes() {
            return codes;
        }
    }

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {

        classBox.setItems(classManager.getClasses());

        championshipManager.getChampionships().forEach(l -> yearBox.getItems().add(Integer.toString(l.getYear())));

        ObservableList<String> o = FXCollections.observableArrayList();
        for (SessionType type : SessionType.values()) {
            o.add(type.getType());
        }
        typeBox.setItems(o);
        classBox.setDisable(true);
        codeBox.setDisable(true);
        weekendBox.setDisable(true);
        startDate.setDisable(true);

        // Initialize the table
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        startDateColumn.setCellValueFactory(
                cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStartDate().toString()));
        airTempColumn.setCellValueFactory(cellData -> cellData.getValue().airTempProperty().asString());
        groundTempColumn.setCellValueFactory(cellData -> cellData.getValue().groundTempProperty().asString());
        humColumn.setCellValueFactory(cellData -> cellData.getValue().humidityProperty().asString());
        conditionsColumn.setCellValueFactory(cellData -> cellData.getValue().conditionsProperty());
        lapsColumn.setCellValueFactory(cellData -> cellData.getValue().lapsProperty().asString());
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationMaxProperty());

        // Add observable list data to the table
        sessionsTable.setItems(sessionManager.getSessions());

        // Make the table columns editable by double clicking
        this.edit();
        // Use a 'searchField' to search for books in the tableView
        this.search();
        // Listen for selection changes and enable delete button
        this.update();

    }

    /**
     * Called when the user press the 'add' button; this method adds a new depot
     * to the controller ObservableList of depots
     */
    @FXML
    private void add() {
        try {

            sessionManager.addSession(classBox.getSelectionModel().getSelectedItem().getName(),
                    Integer.parseInt(yearBox.getSelectionModel().getSelectedItem()),
                    weekendBox.getValue().getStartDate(), conditionsField.getText(),
                    Integer.parseInt(airTemperatureField.getText()), Integer.parseInt(groundTemperatureField.getText()),
                    Integer.parseInt(humidityField.getText()), java.sql.Date.valueOf(startDate.getValue()),
                    codeBox.getValue(), durationField.getText(), typeBox.getValue(),
                    Integer.parseInt(lapsField.getText()));

            sessionsTable.setItems(sessionManager.getSessions()); // Update
                                                                  // table view
            this.clear();
        } catch (Exception e) {
            e.printStackTrace();
            alert.showWarning(e);
        }
    }

    /**
     * Called when the user edit a depot name directly from the tableColumn;
     * This method edits the selected field in the observableList of depots and
     * makes fields editable directly from the table
     */
    private void edit() {

    }

    /**
     * Called on delete button press, opens a confirmation dialog asking if you
     * really want to delete the element; this method is called to delete the
     * selected element from the observableList
     */
    @FXML
    private void delete() {

    }

    /**
     * Called when the user enter something in the search field; It search name
     * of the depot
     */
    private void search() {/*
                            * // 1. Wrap the ObservableList in a FilteredList
                            * (initially display all data). FilteredList<Clax>
                            * filteredData = new
                            * FilteredList<>(manager.getClasses(), p -> true);
                            * 
                            * // 2. Set the filter Predicate whenever the filter
                            * changes. searchField.textProperty().addListener((
                            * observable, oldValue, newValue) -> {
                            * filteredData.setPredicate(e -> { // If filter text
                            * is empty, display all persons. if (newValue ==
                            * null || newValue.isEmpty()) { return true; }
                            * 
                            * // Compare first name and last name of every
                            * person with filter text. String lowerCaseFilter =
                            * newValue.toLowerCase();
                            * 
                            * if (e.getName().toLowerCase().contains(
                            * lowerCaseFilter)) { return true; // Filter matches
                            * first name. } else if
                            * (e.getRules().toLowerCase().contains(
                            * lowerCaseFilter)) { return true; // Filter matches
                            * last name. } return false; // Does not match. });
                            * });
                            * 
                            * // 3. Wrap the FilteredList in a SortedList.
                            * SortedList<Clax> sortedData = new
                            * SortedList<>(filteredData);
                            * 
                            * // 4. Bind the SortedList comparator to the
                            * TableView comparator.
                            * sortedData.comparatorProperty().bind(classesTable.
                            * comparatorProperty());
                            * 
                            * // 5. Add sorted (and filtered) data to the table.
                            * classesTable.setItems(sortedData);
                            */
    }

    /**
     * It listen for selection changes to disable/enable the delete button when
     * the user selects something in the table
     */
    private void update() {

        yearBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!weekendManager.getWeekendsFromYear(Integer.parseInt(newValue)).isEmpty()) {
                    classBox.setDisable(false);
                    classBox.setItems(classManager.getClassesFromYear(Integer.parseInt(newValue)));
                    weekendBox.setDisable(false);
                    weekendBox.setItems(weekendManager.getWeekendsFromYear(Integer.parseInt(newValue)));
                    this.updatePossibleDates();
                } else {
                    weekendBox.setDisable(true);
                    startDate.setDisable(true);
                }
            }
        });

        typeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                codeBox.setDisable(false);

                for (SessionType type : SessionType.values()) {
                    if (typeBox.getSelectionModel().getSelectedItem().equals(type.getType())) {
                        codeBox.setItems(type.getCodes());
                    }
                }
            }
        });

    }

    private void updatePossibleDates() {
        weekendBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                startDate.setDisable(false);
                startDate.setValue(newValue.getStartDate().toLocalDate());
                final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(newValue.getStartDate().toLocalDate())
                                        || item.isAfter(newValue.getFinishDate().toLocalDate())) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
                startDate.setDayCellFactory(dayCellFactory);
            }
        });
    }
}
