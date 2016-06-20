package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Bike;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BikeManagerImpl implements BikeManager {

    public ObservableList<Bike> getBikes() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Bike> bikes = FXCollections.observableArrayList();
        final String retrieve = "select * from MOTO order by nomeMarca";
        try (final PreparedStatement statement = conn.prepareStatement(retrieve);
                final ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                bikes.add(new Bike(result.getString("nomeMarca"), result.getString("modello"), result.getString("foto"),
                        result.getInt("peso"), result.getInt("annoCampionato"), result.getString("nomeTeam")));
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return bikes;

    }

    @Override
    public void addBike(final String manufacturerName, final String model, final String photoUrl, final int weight,
            final int championshipYear, final String teamName) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        final String insert = "insert into MOTO(nomeMarca, modello, foto, peso, annoCampionato, nomeTeam) values (?,?,?,?,?,?)";
        try (final PreparedStatement statement = conn.prepareStatement(insert)) {
            statement.setString(1, manufacturerName);
            statement.setString(2, model);
            statement.setString(3, photoUrl);
            statement.setInt(4, weight);
            statement.setInt(5, championshipYear);
            statement.setString(6, teamName);
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

    public ObservableList<Bike> getBikesFromManufacturer(String manufacturer, int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();
        
        ObservableList<Bike> list = FXCollections.observableArrayList();
        final String retrieve = "select * from MOTO where nomeMarca = ? && MOTO.annoCampionato = ?";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setString(1, manufacturer);
            statement.setInt(2, year);
            try (final ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    list.add(new Bike(result.getString("nomeMarca"), result.getString("modello"),
                            result.getString("foto"), result.getInt("peso"), result.getInt("annoCampionato"),
                            result.getString("nomeTeam")));

                }
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public Bike getBikeByTeamAndYearAndRider(int year, int rider) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        Bike bike = new Bike();
        final String retrieve = "select MOTO.modello, MOTO.nomeMarca from MOTO where MOTO.nomeTeam =( select nomeTeam  from CONTRATTO_PILOTA c, PILOTA ps where c.codicePersonale = ? && ps.codicePersonale = ? && c.annoCampionato = ? && MOTO.annoCampionato = ?)";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, rider);
            statement.setInt(2, rider);
            statement.setInt(3, year);
            statement.setInt(4, year);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    bike.setModel(result.getString(1));
                    bike.setManufacturerName(result.getString(2));
                }
            } catch (SQLException e) {
                try {
                    AlertTypes alert = new AlertTypesImpl();
                    alert.showError(e);
                } catch (ExceptionInInitializerError ei) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }
        return bike;
    }

	@Override
	public Bike getBikeByYear(int year) {
		final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        Bike bike = new Bike();
        final String retrieve = "select m.nomeMarca, m.foto, m.modello, m.nomeTeam from MOTO m where m.annoCampionato = ?";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, year);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    bike.setModel(result.getString(1));
                    bike.setManufacturerName(result.getString(2));
                    bike.setPhotoUrl(result.getString(2));
                    bike.setTeamName(result.getString(4));
                }
            } catch (SQLException e) {
                try {
                    AlertTypes alert = new AlertTypesImpl();
                    alert.showError(e);
                } catch (ExceptionInInitializerError ei) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }
        return bike;
	}
}
