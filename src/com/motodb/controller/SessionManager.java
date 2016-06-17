package com.motodb.controller;

import java.sql.Date;

import com.motodb.model.Session;

import javafx.collections.ObservableList;

public interface SessionManager {
	/**
	 * 
	 * @param className
	 * @param year
	 * @param weekendDate
	 * @param conditions
	 * @param airTemp
	 * @param groundTemp
	 * @param humidity
	 * @param startDate
	 * @param finishDate
	 * @param code
	 * @param durationMax
	 * @param type
	 * @param laps
	 */
    public void addSession(String className, int year, Date weekendDate, String conditions, int airTemp, 
    		int groundTemp, int humidity, Date startDate, Date finishDate, String code, String durationMax, String type, int laps);
	/**
	 * 
	 * @return
	 */
    public ObservableList<Session> getSessions();

    public ObservableList<Session> getSessionsFromWeekend(Date weekend);
}