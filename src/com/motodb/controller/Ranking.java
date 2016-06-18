package com.motodb.controller;

import javafx.collections.ObservableList;

public interface Ranking {

	ObservableList<com.motodb.model.Ranking> getRankingByYearAndClass(int year, String className);
}
