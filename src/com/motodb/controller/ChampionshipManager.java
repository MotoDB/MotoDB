package com.motodb.controller;

import com.motodb.model.Championship;
import com.motodb.model.ChampionshipsView;
import javafx.collections.ObservableList;

public interface ChampionshipManager {

    void insertChampionship(int year, int edition, ObservableList<String> classes, final ObservableList<String> sponsors);

    ObservableList<Championship>getChampionships();
    
    ObservableList<String> getClassesStrings(int year);
    
    ObservableList<String> getSponsorStrings(int year);
    
    ObservableList<ChampionshipsView> getChampionshipViews();

}