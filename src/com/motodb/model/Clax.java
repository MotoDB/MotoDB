package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clax {

	private final StringProperty name;
    private final StringProperty rules;
    private final IntegerProperty index;
    
    public Clax() {
        this(null, null, 0);
    }
    
    public Clax(String name, String rules, Integer index){
    	 this.name = new SimpleStringProperty(name);
         this.rules = new SimpleStringProperty(rules);
         this.index = new SimpleIntegerProperty(index);
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
    
    public IntegerProperty indexProperty() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index.set(index);
    }
    
    public Integer getIndex() {
        return index.get();
    }

	@Override
	public String toString() {
		return name.get();
	}
    
}
