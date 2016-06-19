package com.motodb.controller;

import com.motodb.model.Tyre;

import javafx.collections.ObservableList;

public interface TyreManager {

    ObservableList<Tyre> getTyres();

    void addTyre(String tyre, String model, String size, String compound);

}