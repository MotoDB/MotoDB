package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team {

    private final StringProperty name;
    private final IntegerProperty year;
    private final StringProperty location;
    private final StringProperty logo;

    public Team() {
        this(null, 0, null, null);
    }

    public Team(String name, Integer year, String location, String logo) {
        this.name = new SimpleStringProperty(name);
        this.year = new SimpleIntegerProperty(year);
        this.location = new SimpleStringProperty(location);
        this.logo = new SimpleStringProperty(logo);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty logoProperty() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo.set(logo);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getLogo() {
        return logo.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }

    public Integer getYear() {
        return year.get();
    }

    @Override
    public String toString() {
        return "Team " + name.get() + " " + year.get();
    }

}
