package com.motodb.controller;

import java.sql.Date;

import com.motodb.model.Rider;

import javafx.collections.ObservableList;

public interface RacingRiderManager {
	/**
	 * 
	 * @param year
	 * @param weekendDate
	 * @param className
	 * @param sessionCode
	 * @param fastestTime
	 * @param position
	 * @param averageSpeed
	 * @param finished
	 * @param personalCode
	 * @param manufacturer
	 * @param bikeModel
	 * @param points
	 */
	public void addRacingRider(int year, Date weekendDate, String className, String sessionCode,
	            String fastestTime, Integer position, Integer averageSpeed, boolean finished, int personalCode, String manufacturer, String bikeModel,
	            int points);
	/**
	 * 
	 * @return
	 */
	public ObservableList<RacingRider> getRacingRiders();
}
