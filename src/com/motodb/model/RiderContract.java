package com.motodb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RiderContract extends Contract {

	final StringProperty className;
	
	public RiderContract() {
		this(0, null, 0, null, null);
	}
	
	
	public RiderContract(int int1, String string, int int2, String string2, String className) {
		super();
		this.className = new SimpleStringProperty(className);
	}

	public StringProperty classNameProperty() {
        return className;
    }
    public String getclassName() {
        return className.get();
    }

    public void setclassName(String name) {
        this.className.set(name);
    }
}
