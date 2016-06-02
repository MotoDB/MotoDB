package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Championship {

    private final IntegerProperty year;
    private final IntegerProperty edition;
    
    public Championship() {
        this(0, 0);
    }
    
    public Championship(int year, int edition){
    	 this.year = new SimpleIntegerProperty(year);
         this.edition = new SimpleIntegerProperty(edition);
    }

    public IntegerProperty yearProperty() {
        return year;
    }
    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }
    
    public IntegerProperty editionProperty() {
        return edition;
    }
    
    public void setEdition(int edition) {
        this.edition.set(edition);
    }
    
    public int getEdition() {
        return edition.get();
    }
  
}