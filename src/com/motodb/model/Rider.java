package com.motodb.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rider {
	
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty id;
    private final StringProperty photo;
	private final StringProperty birthplace;
    private final StringProperty state;
    private final StringProperty role;
    private final IntegerProperty height;
    private final IntegerProperty weight;
    private final IntegerProperty number;
    private final StringProperty acronym;
    private Date dateOfBirth;
    
    public Rider() {
        this(null, null, null, null, null, null, null, 0, 0, 0, null, null);
    }
   
    public Rider(String firstName, String lastName, String id, String photo, String birthplace, String state, String role,
    		int weight, int height, int number, String acronym, Date dateOfBirth){
    	 this.firstName = new SimpleStringProperty(firstName);
    	 this.lastName = new SimpleStringProperty(lastName);
    	 this.id = new SimpleStringProperty(id);
    	 this.photo = new SimpleStringProperty(photo);
    	 this.birthplace = new SimpleStringProperty(birthplace);
    	 this.state = new SimpleStringProperty(state);
    	 this.role = new SimpleStringProperty(role);
    	 this.weight = new SimpleIntegerProperty(weight);
    	 this.height = new SimpleIntegerProperty(height);
    	 this.number = new SimpleIntegerProperty(number);
    	 this.acronym = new SimpleStringProperty(acronym);
    	 this.dateOfBirth = dateOfBirth;
    }

    //First Name
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    //First Name
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    //Personal Code
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    
    public StringProperty idProperty() {
        return id;
    }
    
    //Photo URL
    public StringProperty photoProperty() {
        return photo;
    }
    
    public String getPhoto() {
    	return photo.get();
    }
    
    public void setPhoto(String photo) {
        this.photo.set(photo);
    }
    
    //Birthplace
    public StringProperty birthplaceProperty() {
        return birthplace;
    }
    
    public String getBirthplace() {
        return birthplace.get();
    }
    
    public void setBirthplace(String birthplace) {
        this.birthplace.set(birthplace);
    }
    
    //State
    public StringProperty stateProperty() {
        return state;
    }
    
    public String getState() {
        return state.get();
    }
    
    public void setState(String state) {
        this.state.set(state);
    }
    
    //Role
    public StringProperty roleProperty() {
        return role;
    }
    
    public String getRole() {
        return role.get();
    }
    
    public void setRole(String role) {
        this.role.set(role);
    }
    
    //Weight
    public IntegerProperty weightProperty() {
        return weight;
    }
    
    public int getWeight() {
        return weight.get();
    }
    
    public void setWeight(int weight) {
        this.weight.set(weight);
    }
    
    //Height
    public IntegerProperty heightProperty() {
        return height;
    }
    
    public int getHeight() {
        return height.get();
    }
    
    public void setHeight(int height) {
        this.height.set(height);
    }
    
    //Number
    public IntegerProperty numberProperty() {
        return number;
    }
    
    public int getNumber() {
        return number.get();
    }
    
    public void setNumber(int number) {
        this.number.set(number);
    }
    
    //Acronym
    public StringProperty acronymProperty() {
        return acronym;
    }
    
    public String getAcronym() {
        return acronym.get();
    }
    
    public void setAcronym(String acronym) {
        this.acronym.set(acronym);
    }
    
    //Date
    public void setDate(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Date getDate() {
        return this.dateOfBirth;
    }

}
