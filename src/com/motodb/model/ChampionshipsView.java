package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChampionshipsView {

    private final IntegerProperty year;
    private final IntegerProperty edition;
    private final StringProperty classes;
    private final StringProperty sponsors;
    
    public ChampionshipsView() {
        this(0, 0, null, null);
    }

    public ChampionshipsView(int year, int edition, String classes, String sponsors) {
        this.year = new SimpleIntegerProperty(year);
        this.edition = new SimpleIntegerProperty(edition);
        this.classes = new SimpleStringProperty(classes);
        this.sponsors = new SimpleStringProperty(sponsors);
    }

    public Integer getYear() {
        return year.get();
    }

    public Integer getEdition() {
        return edition.get();
    }

    public String getClasses() {
        return classes.get();
    }

    public String getSponsors() {
        return sponsors.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public IntegerProperty editionProperty() {
        return edition;
    }

    public StringProperty classesProperty() {
        return classes;
    }

    public StringProperty sponsorsProperty() {
        return sponsors;
    }
    
    public void setYear(int year) {
        this.year.set(year);
    }

    public void setEdition(int edition) {
        this.edition.set(edition);
    }

    public void setClasses(String classes) {
        this.classes.set(classes);
    }

    public void setSponsors(String sponsors) {
        this.sponsors.set(sponsors);
    }
  
}