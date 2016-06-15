package com.motodb.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {

    private final IntegerProperty personalCode;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty photo;
    private final StringProperty birthplace;
    private final StringProperty state;
    private final StringProperty role;
    private Date dateOfBirth;

    public Member() {
        this(0, null, null, null, null, null, null, null);
    }

    public Member(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, Date dateOfBirth) {
        this.personalCode = new SimpleIntegerProperty(personalCode);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.photo = new SimpleStringProperty(photo);
        this.birthplace = new SimpleStringProperty(birthplace);
        this.state = new SimpleStringProperty(state);
        this.role = new SimpleStringProperty(role);
        this.dateOfBirth = dateOfBirth;
    }
    
    // Personal Code
    public IntegerProperty personalCodeProperty() {
        return personalCode;
    }

    public Integer getPersonalCode() {
        return personalCode.get();
    }

    public void setPersonalCode(int firstName) {
        this.personalCode.set(firstName);
    }

    // First Name
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    // First Name
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    // Photo URL
    public StringProperty photoProperty() {
        return photo;
    }

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }

    // Birthplace
    public StringProperty birthplaceProperty() {
        return birthplace;
    }

    public String getBirthplace() {
        return birthplace.get();
    }

    public void setBirthplace(String birthplace) {
        this.birthplace.set(birthplace);
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

    // Role
    public StringProperty roleProperty() {
        return role;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    // Date
    public void setDate(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDate() {
        return this.dateOfBirth;
    }

	@Override
	public String toString() {
		return firstName.get() + " " + lastName.get();
	}

}
