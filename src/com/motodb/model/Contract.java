package com.motodb.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contract {
	
	private final IntegerProperty year;
    private final StringProperty memberType;
    private final IntegerProperty member;
    private final StringProperty team;

    public Contract() {
        this(0, null, 0, null);
    }
 
    public Contract(Integer year, String memberType, Integer member, String team) {

        this.year = new SimpleIntegerProperty(year);
        this.memberType = new SimpleStringProperty(memberType);
        this.member = new SimpleIntegerProperty(member);
        this.team = new SimpleStringProperty(team);
        
    }

    // Member Type
    public StringProperty memberTypeProperty() {
        return memberType;
    }

    public String getName() {
        return memberType.get();
    }

    public void setMemberType(String memberType) {
        this.memberType.set(memberType);
    } 

    // Member
    public IntegerProperty memberProperty() {
        return member;
    }

    public Integer getMember() {
        return member.get();
    }

    public void setMember(int member) {
        this.member.set(member);
    }
    
    // Location
    public StringProperty teamProperty() {
        return team;
    }

    public String getTeam() {
        return team.get();
    }

    public void setTeam(String team) {
        this.team.set(team);
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
}
