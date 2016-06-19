package com.motodb.controller;

import com.motodb.model.Member;
import com.motodb.model.Rider;

import javafx.collections.ObservableList;

public interface MemberManager {

    void addRider(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, java.sql.Date dateOfBirth, int number, int weigth, int heigth, String acronym);

    void addEngineer(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, java.sql.Date dateOfBirth);

    void addMechanic(int personalCode, String firstName, String lastName, String photo, String birthplace, String state,
            String role, java.sql.Date dateOfBirth);

    ObservableList<Rider> getRiders();

    ObservableList<Member> getOtherMembers();

    ObservableList<Member> getMechanics();

    ObservableList<Member> getEngineers();

    public ObservableList<Rider> getRidersFromYear(int year);

    public ObservableList<Rider> getRidersFromClassAndYear(String className, int year);

    public ObservableList<Member> getMembersFromTeam(String teamName, int annoCampionato);

    public ObservableList<Rider> getRidersFromTeamAndYear(String teamName, int year);

	ObservableList<Rider> getRidersFromTeamAndYearAndClass(String teamName, int year, String clax);
}
