package com.motodb.controller;

import com.motodb.model.Sponsor;

import javafx.collections.ObservableList;

public interface SponsorManager {

    void addSponsor(String name, String urlLogo);

    ObservableList<Sponsor> getSponsors();

    ObservableList<String> getSponsorsNames();

}