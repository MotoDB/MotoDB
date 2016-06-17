package com.motodb.controller;

import com.motodb.model.ManufacturerContract;

import javafx.collections.ObservableList;

public interface ManufacturerContractManager {
	/**
	 * 
	 * @return
	 */
	public ObservableList<ManufacturerContract> getManufacturerContracts();
	/**
	 * 
	 * @param year
	 * @param memberType
	 * @param member
	 * @param team
	 */
	public void addManufacturerContract(String manufacurer, Integer year, String team);
	
}
