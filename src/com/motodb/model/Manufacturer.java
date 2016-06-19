package com.motodb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Manufacturer {

    private final StringProperty manufacturerName;
    private final StringProperty manufacturerLogo;

    public Manufacturer() {
        this(null, null);
    }

    public Manufacturer(String manufacturerName, String manufacturerLogo) {
        this.manufacturerName = new SimpleStringProperty(manufacturerName);
        this.manufacturerLogo = new SimpleStringProperty(manufacturerLogo);
    }

    public StringProperty manufacturerNameProperty() {
        return manufacturerName;
    }

    public String getManufacturerName() {
        return manufacturerName.get();
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName.set(manufacturerName);
    }

    public StringProperty logoProperty() {
        return manufacturerLogo;
    }

    public void setLogo(String manufacturerLogo) {
        this.manufacturerLogo.set(manufacturerLogo);
    }

    public String getLogo() {
        return manufacturerLogo.get();
    }

    @Override
    public String toString() {
        return manufacturerName.get();
    }

}
