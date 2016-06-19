/**
 * 'statistics.fxml' Control Class
 */

package com.motodb.view;

import java.text.DateFormatSymbols;
import java.util.*;

import javafx.collections.*;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class StatisticsControl extends ScreenControl {

    // ObservableList of movements
    // private final StatisticsController statisticsController =
    // StatisticsController.getInstanceOf();

    // ObservableList of months
    private final ObservableList<String> monthNames = FXCollections
            .observableArrayList(Arrays.asList(DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths()));
    private static final int MONTHS = 12;

    // ObservableList of years
    // private final ObservableList<String> yearNames =
    // MovementsController.getInstanceOf().getYearsWithMovements();

    @FXML
    private ToggleButton earningStats;
    @FXML
    private ToggleButton movStats;

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private CategoryAxis xMovAxis;
    @FXML
    private CategoryAxis xPriceAxis;

    @FXML
    private ChoiceBox<String> yearBox;

    @FXML
    private HBox hBox;

    public StatisticsControl() {
        super();
    }

    /**
     * Called after the fxml file has been loaded. Method to initializes the
     * control class.
     */
    @FXML
    private void initialize() {
        movStats.setSelected(true);
        this.uploadMovStats();
        // yearBox.setItems(yearNames);
        this.setMovementsData();
    }

    /**
     * Sets the statistics graph according to the number of movements in a
     * specific month.
     */
    public void setMovementsData() {

        // Assign the month names as categories for the horizontal axis.
        xMovAxis.setCategories(monthNames);
        xPriceAxis.setCategories(monthNames);

        barChart.setLegendVisible(false);
        lineChart.setLegendVisible(false);

        // Count the number of movements in a specific month.
        yearBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            barChart.getData().clear();
            lineChart.getData().clear();

            Series<String, Integer> movSeries = new Series<>();
            Series<String, Double> priceSeries = new Series<>();

            // Create a XYChart. Data object for each month. Add it to the
            // series.
            for (int i = 0; i < MONTHS; i++) {
                // movSeries.getData().add(new Data<>(monthNames.get(i),
                // statisticsController.getMovStats(newValue)[i]));
                // priceSeries.getData().add(new Data<>(monthNames.get(i),
                // statisticsController.getPriceStats(newValue)[i]));
            }

            barChart.getData().add(movSeries);
            lineChart.getData().add(priceSeries);
        });
    }

    /**
     * Method to upload the earnings statistics graph Called when the
     * earningStats button is pressed
     */
    public void uploadEarningsStats() {
        hBox.getChildren().clear();
        hBox.getChildren().add(lineChart);
    }

    /**
     * Method to upload the movements statistics graph Called when the movStats
     * button is pressed
     */
    public void uploadMovStats() {
        hBox.getChildren().clear();
        hBox.getChildren().add(barChart);
    }
}
