package com.motodb.controller;

import com.motodb.model.Member;
import com.motodb.model.Rider;

import javafx.collections.ObservableList;

public interface MemberManager {
    
    void addRider(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, java.sql.Date dateOfBirth, int number, int weigth, int heigth, String acronym);
    
    void addEngineer(int personalCode, String firstName, String lastName, String photo,
            String birthplace, String state, String role, java.sql.Date dateOfBirth);
    
    void addMechanic(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, java.sql.Date dateOfBirth);
    
    ObservableList<Rider> getRiders();
        
    ObservableList<Member> getOtherMembers();
    
    ObservableList<Member> getMechanics();
    
    ObservableList<Member> getEngineers();

    ObservableList<Rider> getRidersFromTeamYear(int year, String teamName);
}
