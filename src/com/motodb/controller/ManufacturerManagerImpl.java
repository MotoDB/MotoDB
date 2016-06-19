package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Manufacturer;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManufacturerManagerImpl implements ManufacturerManager {

    @Override
    public ObservableList<Manufacturer> getManufacturers() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Manufacturer> manufacturer = FXCollections.observableArrayList();
        final String retrieve = "select * from MARCA";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve);
                final ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                manufacturer.add(new Manufacturer(result.getString("nome"), result.getString("logo")));
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return manufacturer;

    }

    public ObservableList<String> getManufacturersNames() {
        ObservableList<Manufacturer> manufacturers = this.getManufacturers();
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Manufacturer m : manufacturers) {
            names.add(m.getManufacturerName());
        }
        return names;
    }

    @Override
    public void addManufacturer(String name, String urlLogo) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        java.sql.PreparedStatement statement = null;
        final String insert = "insert into MARCA(nome, logo) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, urlLogo);
            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }
    }

    @Override
    public ObservableList<Manufacturer> getManufacturersByRiderAndYear(int rider, int year) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList();
        final String retrieve = "select MOTO.nomeMarca from MOTO where MOTO.nomeTeam =( select nomeTeam  from CONTRATTO_PILOTA c, PILOTA ps where c.codicePersonale = ? && ps.codicePersonale = ? && c.annoCampionato = ?)";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, rider);
            statement.setInt(2, rider);
            statement.setInt(3, year);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setManufacturerName(result.getString(1));
                    manufacturers.add(manufacturer);
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
        return manufacturers;
    }

}
