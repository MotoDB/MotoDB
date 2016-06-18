package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ranking {

    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty nation;
    private final IntegerProperty number;
    private final StringProperty team;
    private final StringProperty manufacturer;
    private final IntegerProperty points;
    
    public Ranking() {
        this(null, null, null, 0, null, null, 0);
    }

    public Ranking(String name, String surname, String team, int number, String manufacturer, String nation, int points) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.team = new SimpleStringProperty(team);
        this.number = new SimpleIntegerProperty(number);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.nation = new SimpleStringProperty(nation);
        this.points = new SimpleIntegerProperty(points);
    }

    public String getName() {
        return this.name.get();
    }

    public String getSurname() {
        return this.surname.get();
    }

    public String getTeam() {
        return this.team.get();
    }

    public String getManufacturer() {
        return this.manufacturer.get();
    }
    
    public String getNation() {
        return this.nation.get();
    }
    
    public int getPoints() {
        return this.points.get();
    }
    
    public int getNumber() {
    	return this.number.get();
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty surnameProperty() {
        return this.surname;
    }

    public StringProperty teamProperty() {
        return this.team;
    }

    public StringProperty manufacturerProperty() {
        return this.manufacturer;
    }
    
    public StringProperty nationProperty() {
        return this.nation;
    }
    
    public IntegerProperty pointsProperty() {
        return this.points;
    }
    
    public IntegerProperty numberProperty() {
        return this.number;
    }
    
    public void setPoints(int points) {
        this.points.set(points);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
    
    public void setTeam(String team) {
        this.team.set(team);
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }
    
    public void setNation(String nation) {
        this.nation.set(nation);
    }
    
    public void setNumber(int number) {
    	this.number.set(number);
    }
  
}