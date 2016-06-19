package com.motodb.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sponsor {

    private final StringProperty name;
    private final StringProperty logo;

    public Sponsor() {
        this(null, null);
    }

    public Sponsor(String name, String logo) {
        this.name = new SimpleStringProperty(name);
        this.logo = new SimpleStringProperty(logo);
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

    public StringProperty logoProperty() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo.set(logo);
    }

    public String getLogo() {
        return logo.get();
    }
}
