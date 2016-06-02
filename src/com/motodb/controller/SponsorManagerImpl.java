package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SponsorManagerImpl implements SponsorManager {

    @Override
    public void addSponsor(final String name, final String urlLogo) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final java.sql.PreparedStatement statement;
        final String insert = "insert into SPONSOR(nomeSponsor, logo) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, urlLogo);
            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
    }
    
    @Override
    public ObservableList<String> getSponsor() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final ObservableList<String> listSponsors = FXCollections.observableArrayList();
        final String retrieve = "select nomeSponsor from SPONSOR";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                listSponsors.add(result.getString("nomeSponsor"));
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listSponsors;
        
    }
}
