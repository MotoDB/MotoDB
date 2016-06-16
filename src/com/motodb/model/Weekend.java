package com.motodb.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Weekend {
	
	private final IntegerProperty championshipYear;
	private final StringProperty circuitName;
	private Date startDate;
	private Date finishDate;
	
	public Weekend() {
        this(0, null, null, null);
    }
    
    public Weekend(int championshipYear, String circuitName, Date startDate, Date finishDate){
    	 this.championshipYear = new SimpleIntegerProperty(championshipYear);
         this.circuitName = new SimpleStringProperty(circuitName);
         this.startDate = startDate;
         this.finishDate = finishDate;
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
    
    // Circuit Name
    public StringProperty circuitName() {
        return circuitName;
    }

    public String getCircuitName() {
        return circuitName.get();
    }

    public void setCircuitName(String circuit) {
        this.circuitName.set(circuit);
    }

    // Start Date
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    // Finish Date
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

}
