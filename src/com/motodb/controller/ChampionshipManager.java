package com.motodb.controller;


import com.motodb.model.Championship;
import com.motodb.model.Classes;

import javafx.collections.ObservableList;

public interface ChampionshipManager {

    void insertChampionship(int year, int edition, ObservableList<String> classes, final ObservableList<String> sponsors);

    ObservableList<Championship> showChampionship();

    ObservableList<Classes> showClasses(int year);

}