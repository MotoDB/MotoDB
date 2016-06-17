package com.motodb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tyre {
	
	private final StringProperty make;
    private final StringProperty size;
    private final StringProperty compound;
    
    public Tyre() {
        this(null, null, null);
    }
    
    public Tyre(String make, String size, String compound){
    	 this.make = new SimpleStringProperty(make);
         this.size = new SimpleStringProperty(size);
         this.compound = new SimpleStringProperty(compound);
    }

    public StringProperty makeProperty() {
        return make;
    }
    public String getMake() {
        return make.get();
    }

    public void setMake(String name) {
        this.make.set(name);
    }
    
    public StringProperty sizeProperty() {
        return size;
    }
    
    public void setSize(String size) {
        this.size.set(size);
    }
    
    public String getSize() {
        return size.get();
    }
    
    public StringProperty compoundProperty() {
        return compound;
    }
    
    public void setCompound(String compound) {
        this.compound.set(compound);
    }
    
    public String getCompound() {
        return compound.get();
    }

	@Override
	public String toString() {
		return make.get() + compound.get();
	}

}
