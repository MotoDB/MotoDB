package com.motodb.controller;

import com.motodb.model.Team;

import javafx.collections.ObservableList;

public interface TeamManager {

    public void addTeam(int year, String name, String location, String logo, ObservableList<String> classes,
            ObservableList<String> sponsors, ObservableList<String> manufacturers);

    public ObservableList<Team> getTeams();

    public ObservableList<String> getTeamsByYearAndClass(int year, String clax);

    public Team getTeamByName(final String name);

    public ObservableList<Team> getTeamsFromYear(int year);

}
