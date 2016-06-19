package com.motodb.controller;

import javafx.collections.ObservableList;

public interface RankingManager {

    ObservableList<com.motodb.model.Ranking> getRankingByYearAndClass(int year, String className);
}
