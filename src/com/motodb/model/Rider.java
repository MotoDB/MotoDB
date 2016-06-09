package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rider extends Member{

    private final IntegerProperty height;
    private final IntegerProperty weight;
    private final IntegerProperty number;
    private final StringProperty acronym;

    public Rider() {
        this(0, 0, 0, null);
    }

    public Rider(int weight, int height, int number, String acronym) {
        super();
        this.weight = new SimpleIntegerProperty(weight);
        this.height = new SimpleIntegerProperty(height);
        this.number = new SimpleIntegerProperty(number);
        this.acronym = new SimpleStringProperty(acronym);
    }

    // Weight
    public IntegerProperty weightProperty() {
        return weight;
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    // Height
    public IntegerProperty heightProperty() {
        return height;
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    // Number
    public IntegerProperty numberProperty() {
        return number;
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    // Acronym
    public StringProperty acronymProperty() {
        return acronym;
    }

    public String getAcronym() {
        return acronym.get();
    }

    public void setAcronym(String acronym) {
        this.acronym.set(acronym);
    }


}
