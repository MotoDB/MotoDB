package com.motodb.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sponsor {
	
	private final StringProperty name;
    private final StringProperty photo;
    
    public Sponsor() {
        this(null, null);
    }
    
    public Sponsor(String name, String photo){
    	 this.name = new SimpleStringProperty(name);
         this.photo = new SimpleStringProperty(photo);
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
    
    public StringProperty photoProperty() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo.set(photo);
    }
    
    public String getPhoto() {
        return photo.get();
    }
}
