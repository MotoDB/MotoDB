package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ManufacturerContract {

	private final StringProperty manufacturerName;
	private final IntegerProperty championshipYear;
	private final StringProperty teamName;
	
	public ManufacturerContract(){
		
		this(null, 0, null);
	}
	
	public ManufacturerContract(String manufacturerName, Integer championshipYear, String teamName){
		
		this.manufacturerName = new SimpleStringProperty(manufacturerName);
		this.championshipYear = new SimpleIntegerProperty(championshipYear);
		this.teamName = new SimpleStringProperty(teamName);
	}
	
	// brandName
	public StringProperty manufacturerNameProperty() {
		return manufacturerName;
	}
	public String getManufacturerName() {
		return manufacturerName.get();
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName.set(manufacturerName);
	}
	
	// championshipYear
    public IntegerProperty championshipYearProperty() {
        return championshipYear;
    }

    public Integer getChampionshipYear() {
        return championshipYear.get();
    }

    public void setChampionshipYear(int championshipYear) {
        this.championshipYear.set(championshipYear);
    }
    
    //teamName
    public StringProperty teamNameProperty() {
        return teamName;
    }
    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }
    
}
