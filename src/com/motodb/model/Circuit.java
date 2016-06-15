package com.motodb.model;

import java.sql.Date;
import java.util.Optional;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Circuit{

    private final StringProperty name;
    private final StringProperty state;
    private final StringProperty location;
    private final IntegerProperty capacity;
    private final IntegerProperty rightHanders;
    private final IntegerProperty leftHanders;
    private final IntegerProperty lenght;
    private final IntegerProperty straight;
    private Date date;
    private final StringProperty photo;
    private final Optional<IntegerProperty> record;
    private final Optional<StringProperty> recordRider;

    public Circuit() {
        this(null, null, null, 0, 0, 0, 0, 0, null, null, 0);
    }

    public Circuit(String name, String state, String location, int capacity, int rightHanders, int leftHanders, int lenght, int straight, Date date, String photo, int record) {

        this.name = new SimpleStringProperty(name);
        this.state = new SimpleStringProperty(state);
        this.location = new SimpleStringProperty(location);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.rightHanders = new SimpleIntegerProperty(rightHanders);
        this.leftHanders = new SimpleIntegerProperty(leftHanders);
        this.lenght = new SimpleIntegerProperty(lenght);
        this.straight = new SimpleIntegerProperty(straight);
        this.date = date;
        this.photo = new SimpleStringProperty(photo);
        this.record = new SimpleIntegerProperty(record);
    }

    // Name
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String acronym) {
        this.name.set(acronym);
    } 

    // State
    public StringProperty stateProperty() {
        return state;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }
    
    // Location
    public StringProperty locationProperty() {
        return location;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
    
    // Capacity
    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int weight) {
        this.capacity.set(weight);
    }

    // RightHanders
    public IntegerProperty rightHandersProperty() {
        return rightHanders;
    }

    public int getRightHanders() {
        return rightHanders.get();
    }

    public void setRightHanders(int height) {
        this.rightHanders.set(height);
    }

    // LeftHanders
    public IntegerProperty leftHandersProperty() {
        return leftHanders;
    }

    public int getLeftHanders() {
        return leftHanders.get();
    }

    public void setLeftHanders(int number) {
        this.leftHanders.set(number);
    }
    
    // Lenght
    public IntegerProperty lenghtProperty() {
        return lenght;
    }

    public int getLenght() {
        return lenght.get();
    }

    public void setLenght(int lenght) {
        this.lenght.set(lenght);
    }
    
    // Straight
    public IntegerProperty straightProperty() {
        return straight;
    }

    public int getStraight() {
        return straight.get();
    }

    public void setStraight(int straight) {
        this.straight.set(straight);
    }
    
    // Date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }
    
    // Photo
    public StringProperty photoProperty() {
        return photo;
    }

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }
    
    // Record
    public IntegerProperty recordProperty() {
        return record;
    }

    public int getRecord() {
        return record.get();
    }

    public void setRecord(int record) {
        this.record.set(record);
    }

}
