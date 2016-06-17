package com.motodb.controller;

import com.motodb.model.Manufacturer;

import javafx.collections.ObservableList;

public interface ManufacturerManager {

    void addManufacturer(String name, String urlLogo);

    ObservableList<Manufacturer> getManufacturers();

}