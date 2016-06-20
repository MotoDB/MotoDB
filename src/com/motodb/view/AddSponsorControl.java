package com.motodb.view;

import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.model.Sponsor;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddSponsorControl extends ScreenControl {

    // Alert panel to manage exceptions
    private final AlertTypes alert = new AlertTypesImpl();

    // Controller
    private final SponsorManager manager = new SponsorManagerImpl();

    @FXML
    private TableView<Sponsor> sponsorsTable;
    @FXML
    private TableColumn<Sponsor, String> nameColumn, logoColumn;
    @FXML
    private TextField nameField, logoUrlField, searchField;
    @FXML
    private Button delete;

    /**
     * Called after the fxml file has been loaded; this method initializes the
     * fxml control class.
     */
    public void initialize() {
        // Initialize the table
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        logoColumn.setCellValueFactory(cellData -> cellData.getValue().logoProperty());
        // Add observable list data to the table
        sponsorsTable.setItems(manager.getSponsors());

        // Make the table columns editable by double clicking
        this.edit();
        // Use a 'searchField' to search for books in the tableView
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
            manager.addSponsor(nameField.getText(), logoUrlField.getText());
            this.clear();

            sponsorsTable.setItems(manager.getSponsors());
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
    

    /**
     * It listen for selection changes to disable/enable the delete button when
     * the user selects something in the table
     */
    private void update() {

    }
}
