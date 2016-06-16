package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Circuit{

    private final StringProperty name;
    private final StringProperty state;
    private final StringProperty location;
    private final IntegerProperty rightHanders;
    private final IntegerProperty leftHanders;
    private final IntegerProperty lenght;
    private final IntegerProperty straight;
    private final StringProperty photo;
    private IntegerProperty record;
    private IntegerProperty recordRider;
    private IntegerProperty recordYear;

    public Circuit() {
        this(null, null, null, 0, 0, 0, 0, null, 0, 0, 0);
    }
 
    public Circuit(String name, String state, String location, int rightHanders, int leftHanders, int lenght, int straight, String photo, Integer record, Integer recordRider, Integer recordYear) {

        this.name = new SimpleStringProperty(name);
        this.state = new SimpleStringProperty(state);
        this.location = new SimpleStringProperty(location);
        this.rightHanders = new SimpleIntegerProperty(rightHanders);
        this.leftHanders = new SimpleIntegerProperty(leftHanders);
        this.lenght = new SimpleIntegerProperty(lenght);
        this.straight = new SimpleIntegerProperty(straight);
        this.photo = new SimpleStringProperty(photo);
        this.record = new SimpleIntegerProperty(record);
        this.recordRider = new SimpleIntegerProperty(recordRider);
        this.recordYear = new SimpleIntegerProperty(recordYear);
    }

    // Name
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
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

    // Record Year
    public IntegerProperty recordRiderProperty() {
        return recordRider;
    }

    public int getRecordRider() {
        return recordRider.get();
    }

    public void setRecordRider(int recordRider) {
        this.recordRider.set(recordRider);
    }
    
    // Record Year
    public IntegerProperty recordYearProperty() {
        return recordYear;
    }

    public int getRecordYear() {
        return recordYear.get();
    }

    public void setRecordYear(int recordYear) {
        this.recordYear.set(recordYear);
    }

	@Override
	public String toString() {
		return name.get();
	}
    
    
}
