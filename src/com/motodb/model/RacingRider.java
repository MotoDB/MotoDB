package com.motodb.model;

import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RacingRider {

	private final IntegerProperty championshipYear;
	private Date weekendStartingDate;
	private final StringProperty className;
    private final StringProperty sessionCode;
    private final StringProperty fastestTime;
    private final IntegerProperty position;
    private final BooleanProperty finished;
    private final IntegerProperty personalCode;
    private final StringProperty manufacturerName;
    private final StringProperty bikeModel;
    private final IntegerProperty points;
    
    
    public RacingRider() {
        this(0, null, null, null, null, 0, null, 0, null, null, 0);
    }

    public RacingRider(int championshipYear, Date weekendStartingDate, String className,
			String sessionCode, String fastestTime, int position, Boolean finished, int personalCode,
			String manufacturerName, String bikeModel, int points) {
		this.championshipYear = new SimpleIntegerProperty(championshipYear);
		this.weekendStartingDate = weekendStartingDate;
		this.className = new SimpleStringProperty(className);
		this.sessionCode = new SimpleStringProperty(sessionCode);
		this.fastestTime = new SimpleStringProperty(fastestTime);
		this.position = new SimpleIntegerProperty(position);
		this.finished = new SimpleBooleanProperty(finished);
		this.personalCode = new SimpleIntegerProperty(personalCode);
		this.manufacturerName = new SimpleStringProperty(manufacturerName);
		this.bikeModel = new SimpleStringProperty(bikeModel);
		this.points = new SimpleIntegerProperty(points);
	}


    // Championship Year
    public IntegerProperty championshipYearProperty() {
        return championshipYear;
    }

    public int getChampionshipYear() {
        return championshipYear.get();
    }

    public void setChampionshipYear(int championshipYear) {
        this.championshipYear.set(championshipYear);
    }
    
    // Weekend Starting Date
    public void setWeekendStartingDate(Date weekendStartingDate) {
        this.weekendStartingDate = weekendStartingDate;
    }

    public Date getWeekendStartingDate() {
        return this.weekendStartingDate;
    }

    // Class Name
    public StringProperty classNameProperty() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className.set(className);
    }
    
    public String getClassName() {
        return className.get();
    }
   
    // Session Code
    public StringProperty sessionCodeProperty() {
        return sessionCode;
    }
    
    public void setSessionCode(String sessionCode) {
        this.sessionCode.set(sessionCode);
    }
    
    public String getSessionCode() {
        return sessionCode.get();
    }
    
    // Fastest Time
    public StringProperty fastestTimeProperty() {
        return fastestTime;
    }
    
    public void setFastestTime(String fastestTime) {
        this.fastestTime.set(fastestTime);
    }
    
    public String getFastestTime() {
        return fastestTime.get();
    }

    // Position
    public IntegerProperty positionProperty() {
        return position;
    }

    public int getPosition() {
        return position.get();
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    // Finished Boolean
    public BooleanProperty finishedProperty() {
        return finished;
    }

    public boolean hasFinished() {
        return finished.get();
    }

    public void setFinished(boolean finished) {
        this.finished.set(finished);
    }
    
    // Personal Code
    public IntegerProperty personalCodeProperty() {
        return personalCode;
    }

    public Integer getPersonalCode() {
        return personalCode.get();
    }

    public void setPersonalCode(int personalCode) {
        this.personalCode.set(personalCode);
    }
    
    // Manufacturer Name
    public StringProperty manufacturerNameProperty() {
        return manufacturerName;
    }
    public String getManufacturerName() {
        return manufacturerName.get();
    }

    public void setManufacturerName(String name) {
        this.manufacturerName.set(name);
    }
    
    // Bike Model
    public StringProperty bikeModelProperty() {
        return bikeModel;
    }
    public String getBikeModel() {
        return bikeModel.get();
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel.set(bikeModel);
    }
    
    // Points
    public IntegerProperty pointsProperty() {
        return points;
    }

    public Integer getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
    
	
}
