package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Sponsor;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SponsorManagerImpl implements SponsorManager {

    public ObservableList<Sponsor> getSponsors() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Sponsor> sponsors = FXCollections.observableArrayList();
        final String retrieve = "select * from SPONSOR";
        try (final PreparedStatement statement = conn.prepareStatement(retrieve);
                final ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                sponsors.add(new Sponsor(result.getString("nomeSponsor"), result.getString("logo")));
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return sponsors;

    }

    public ObservableList<String> getSponsorsNames() {
        ObservableList<Sponsor> sponsors = this.getSponsors();
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Sponsor s : sponsors) {
            names.add(s.getName());
        }
        return names;
    }

    @Override
    public void addSponsor(final String name, final String urlLogo) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        final String insert = "insert into SPONSOR(nomeSponsor, logo) values (?,?)";
        try (final PreparedStatement statement = conn.prepareStatement(insert)) {
            statement.setString(1, name);
            statement.setString(2, urlLogo);
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }
    }

}
