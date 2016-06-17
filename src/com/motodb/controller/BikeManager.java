package com.motodb.controller;

import com.motodb.model.Bike;

import javafx.collections.ObservableList;

public interface BikeManager {
    
    ObservableList<Bike> getBikes();

    void addBike(String manufacturerName, String model, String photoUrl, int weight, int championshipYear, String teamName);

}