package com.motodb.controller;

import java.sql.Date;

import com.motodb.model.RacingRider;
import com.motodb.model.Team;

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
	public void addRacingRider(int year, Date weekendDate, String className, String sessionCode, String fastestTime,
			Integer position, boolean finished, int personalCode, String manufacturer, String bikeModel, int points, String nameTyre, String nameModelTyre, String typeTyre);
	/**
	 * 
	 * @return
	 */
	public ObservableList<RacingRider> getRacingRiders();
	
	public Team getTeamByRiderAndYear(int rider, int year);
	
	public ObservableList<RacingRider> getRidersFromYearWeekSess(int year, Date weekend, String session);
	
	String getRiderNameByCode(int code);
}
