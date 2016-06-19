package com.motodb.view;

import java.util.Arrays;

import com.motodb.controller.TyreManager;
import com.motodb.controller.TyreManagerImpl;
import com.motodb.model.Tyre;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddTyreControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final TyreManager manager = new TyreManagerImpl();

    @FXML
    private TableView<Tyre> tyresTable;
    @FXML
    private TableColumn<Tyre, String> makeColumn, modelColumn, sizeColumn, compoundColumn;
    @FXML
    private ComboBox<String> makeField;
    @FXML
    private TextField modelField, sizeField, compoundField;
    @FXML
    private Button delete;

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {
        // Initialize the table
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        sizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        compoundColumn.setCellValueFactory(cellData -> cellData.getValue().compoundProperty());
        // Add observable list data to the table
        tyresTable.setItems(manager.getTyres());

        makeField.getItems()
                .addAll(FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Michelin", "Dunlop")));

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
            manager.addTyre(makeField.getSelectionModel().getSelectedItem(), modelField.getText(), sizeField.getText(),
                    compoundField.getText());
            tyresTable.setItems(manager.getTyres());
            this.clear();
        } catch (Exception e) {
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

    }
}
