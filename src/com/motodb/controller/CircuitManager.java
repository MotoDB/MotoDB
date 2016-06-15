package com.motodb.controller;

import java.sql.Date;

import com.motodb.model.Circuit;

import javafx.collections.ObservableList;

public interface CircuitManager {

    void addCircuit(String name, String state, String location, int capacity, int rightHanders, int leftHanders, int lenght, int straight, Date date, String photo, int record);

    ObservableList<Circuit> getCircuits();
    
    ObservableList<String> getCircuitsNames();

}