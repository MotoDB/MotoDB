package com.motodb.controller;

import com.motodb.model.Brand;

import javafx.collections.ObservableList;

public interface BrandManager {

    void addBrand(String name, String urlLogo);

    ObservableList<Brand> getBrands();

}