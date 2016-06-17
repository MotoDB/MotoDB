package com.motodb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Brand {

	private final StringProperty brandName;
	private final StringProperty brandLogo;
 
	public Brand(){
		this(null,null);
	}
 
	public Brand(String brandName, String brandLogo){
		this.brandName = new SimpleStringProperty(brandName);
		this.brandLogo = new SimpleStringProperty(brandLogo);
	}
 
	public StringProperty brandNameProperty() {
		return brandName;
	}
	public String getBrandName() {
		return brandName.get();
	}

	public void setBrandName(String brandName) {
		this.brandName.set(brandName);
	}

	public StringProperty logoProperty() {
		return brandLogo;
	}

	public void setLogo(String brandLogo) {
		this.brandLogo.set(brandLogo);
	}

	public String getLogo() {
		return brandLogo.get();
	}
}
