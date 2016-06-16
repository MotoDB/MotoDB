package com.motodb.controller;

import com.motodb.model.Contract;
import com.motodb.view.AddContractControl.MemberType;

import javafx.collections.ObservableList;

public interface ContractManager {
	/**
	 * 
	 * @return
	 */
	public ObservableList<Contract> getContracts();
	/**
	 * 
	 * @param year
	 * @param memberType
	 * @param member
	 * @param team
	 */
	public void addContract(Integer year, MemberType memberType, Integer member, String team);
	/**
	 * 
	 * @return
	 */
	public ObservableList<Contract> getRiderContracts();
	/**
	 * 
	 * @return
	 */
	public ObservableList<Contract> getEngineerContracts();
	/**
	 * 
	 * @return
	 */
	public ObservableList<Contract> getMechanicContracts();
}
