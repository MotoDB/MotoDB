package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bike {
	
	private final StringProperty manufacturerName;
	private final StringProperty model;
    private final StringProperty photoUrl;
    private final IntegerProperty weight;
    private final IntegerProperty championshipYear;
    private final StringProperty teamName;
    
    public Bike() {
        this(null, null, null, 0, 0, null);
    }
    
    public Bike(String manufacturerName, String model, String photoUrl, int weight, int championshipYear, String teamName){
    	 this.manufacturerName = new SimpleStringProperty(manufacturerName);
         this.model = new SimpleStringProperty(model);
         this.photoUrl = new SimpleStringProperty(photoUrl);
         this.weight = new SimpleIntegerProperty(weight);
         this.championshipYear = new SimpleIntegerProperty(championshipYear);
         this.teamName = new SimpleStringProperty(teamName);
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
    
    public StringProperty modelProperty() {
        return model;
    }
    
    public void setModel(String model) {
        this.model.set(model);
    }
    
    public String getModel() {
        return model.get();
    }
    
    public StringProperty photoUrlProperty() {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl.set(photoUrl);
    }
    
    public String getPhotoUrl() {
        return photoUrl.get();
    }
    
    public IntegerProperty weightProperty() {
        return weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight.set(weight);
    }
    
    public Integer getWeight() {
        return weight.get();
    }
    
    public IntegerProperty championshipYearProperty() {
        return championshipYear;
    }
    
    public void setChampionshipYear(Integer championshipYear) {
        this.championshipYear.set(championshipYear);
    }
    
    public Integer getChampionshipYear() {
        return championshipYear.get();
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }
    
    public String getTeamName() {
        return teamName.get();
    }

	@Override
	public String toString() {
		return model.get();
	}
    
    
}
