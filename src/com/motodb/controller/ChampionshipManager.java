package com.motodb.controller;

import java.util.List;

import com.motodb.model.Championship;
import com.motodb.model.Classes;

import javafx.collections.ObservableList;

public interface ChampionshipManager {

    void insertChampionship(int year, int edition, List<String> classes);

    List<Championship> showChampionship();

    ObservableList<Classes> showClasses(int year);

}