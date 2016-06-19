package com.motodb.model;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Session {

    private final StringProperty className;
    private final IntegerProperty year;
    private Date weekendDate;
    private final StringProperty conditions;
    private final IntegerProperty airTemp;
    private final IntegerProperty groundTemp;
    private final IntegerProperty humidity;
    private Date startDate;
    private final StringProperty code;
    private final StringProperty durationMax;
    private final StringProperty type;
    private final IntegerProperty laps;

    public Session() {
        this(null, 0, null, null, 0, 0, 0, null, null, null, null, 0);
    }

    public Session(String className, int year, Date weekendDate, String conditions, int airTemp, int groundTemp,
            int humidity, Date startDate, String code, String durationMax, String type, Integer laps) {

        this.className = new SimpleStringProperty(className);
        this.year = new SimpleIntegerProperty(year);
        this.weekendDate = weekendDate;
        this.conditions = new SimpleStringProperty(conditions);
        this.airTemp = new SimpleIntegerProperty(airTemp);
        this.groundTemp = new SimpleIntegerProperty(groundTemp);
        this.humidity = new SimpleIntegerProperty(humidity);
        this.startDate = startDate;
        this.code = new SimpleStringProperty(code);
        this.durationMax = new SimpleStringProperty(durationMax);
        this.type = new SimpleStringProperty(type);
        this.laps = new SimpleIntegerProperty(laps);
    }

    // ClassName
    public StringProperty classNameProperty() {
        return className;
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    // Year
    public IntegerProperty yearProperty() {
        return year;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    // WeekendDate
    public Date getWeekendDate() {
        return weekendDate;
    }

    public void setWeekendDate(Date weekendDate) {
        this.weekendDate = weekendDate;
    }

    // Conditions
    public StringProperty conditionsProperty() {
        return conditions;
    }

    public String getConditions() {
        return conditions.get();
    }

    public void setConditions(String conditions) {
        this.conditions.set(conditions);
    }

    // AirTemp
    public IntegerProperty airTempProperty() {
        return airTemp;
    }

    public int getAirTemp() {
        return airTemp.get();
    }

    public void setAirTemp(int airTemp) {
        this.airTemp.set(airTemp);
    }

    // GroundTemp
    public IntegerProperty groundTempProperty() {
        return groundTemp;
    }

    public int getGroundTemp() {
        return groundTemp.get();
    }

    public void setGroundTemp(int groundTemp) {
        this.groundTemp.set(groundTemp);
    }

    // Humidity
    public IntegerProperty humidityProperty() {
        return humidity;
    }

    public int getHumidity() {
        return humidity.get();
    }

    public void setHumidity(int humidity) {
        this.humidity.set(humidity);
    }

    // StartDate
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Code
    public StringProperty codeProperty() {
        return code;
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    // DurationMax
    public StringProperty durationMaxProperty() {
        return durationMax;
    }

    public String getDurationMax() {
        return durationMax.get();
    }

    public void setDurationMax(String durationMax) {
        this.durationMax.set(durationMax);
    }

    // Type
    public StringProperty typeProperty() {
        return type;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    // Laps
    public IntegerProperty lapsProperty() {
        return laps;
    }

    public int getLaps() {
        return laps.get();
    }

    public void setLaps(int laps) {
        this.laps.set(laps);
    }

    @Override
    public String toString() {
        return code.get();
    }

}
