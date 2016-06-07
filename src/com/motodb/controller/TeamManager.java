package com.motodb.controller;

import com.motodb.model.Team;

import javafx.collections.ObservableList;

public interface TeamManager {

    void addTeam(int year, String name, String location, String logo, ObservableList<String> classes, ObservableList<String> sponsors);
    
    ObservableList<Team> getTeams();
    
}
