package com.motodb.controller;

import com.motodb.model.Bike;

import javafx.collections.ObservableList;

public interface BikeManager {

    ObservableList<Bike> getBikes();

    void addBike(String manufacturerName, String model, String photoUrl, int weight, int championshipYear,
            String teamName);

    public ObservableList<Bike> getBikesFromManufacturer(String manufacturer, int year);
    
    public ObservableList<Bike> getBikesFromTeamAndYear(String team, int year);

    Bike getBikeByTeamAndYearAndRider(int year, int rider);
    
	Bike getBikeByYear(int year);
	
}