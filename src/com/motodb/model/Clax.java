package com.motodb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clax {

	private final StringProperty name;
    private final StringProperty rules;
    
    public Clax() {
        this(null, null);
    }
    
    public Clax(String name, String rules){
    	 this.name = new SimpleStringProperty(name);
         this.rules = new SimpleStringProperty(rules);
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
    
    public StringProperty rulesProperty() {
        return rules;
    }
    
    public void setRules(String rules) {
        this.rules.set(rules);
    }
    
    public String getRules() {
        return rules.get();
    }
    
}
