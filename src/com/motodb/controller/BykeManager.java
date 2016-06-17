package com.motodb.controller;

import com.motodb.model.Byke;

import javafx.collections.ObservableList;

public interface BykeManager {
    
    ObservableList<Byke> getBykes();

    void addByke(String manufacturerName, String model, String photoUrl, int weight, int championshipYear, String teamName);

    public ObservableList<Byke> getBikesFromManufacturer(String manufacturer);
}