package com.motodb.controller;

import java.sql.Date;

import com.motodb.model.Weekend;

import javafx.collections.ObservableList;

public interface WeekendManager {
	/**
	 * 
	 * @return 
	 */
	public ObservableList<Weekend> getWeekends();
	/**
	 * 
	 * @param year
	 * @param circuit
	 * @param startDate
	 * @param endDate
	 */
	public void addWeekend(Integer year, String circuit, Date startDate, Date endDate);
}
