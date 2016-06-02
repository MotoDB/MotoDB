package com.motodb.controller;

import javafx.collections.ObservableList;

public interface SponsorManager {

    void addSponsor(String name, String urlLogo);

    ObservableList<String> getSponsor();

}