package com.motodb.controller;

import java.util.Optional;

import com.motodb.model.Circuit;

import javafx.collections.ObservableList;

public interface CircuitManager {

    void addCircuit(String name, String state, String location, int rightHanders, int leftHanders, 
    		int lenght, int straight, String photo, Optional<String> record, Optional<Integer> recordRider, Optional<Integer> recordYear);

    ObservableList<Circuit> getCircuits();
    
    ObservableList<String> getCircuitsNames();

}