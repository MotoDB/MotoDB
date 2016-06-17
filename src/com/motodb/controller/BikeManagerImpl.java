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
        final Connection conn  = db.getConnection();
        
        ObservableList<Bike> bikes = FXCollections.observableArrayList();
        final String retrieve = "select * from MOTO order by nomeMarca";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
                bikes.add(new Bike(result.getString("nomeMarca"), result.getString("modello"), result.getString("foto"), result.getInt("peso"), result.getInt("annoCampionato"), result.getString("nomeTeam")));
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (result != null) { 
                    result.close();
                }
            }
            catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }
        
        return bikes;
        
    }
    
    @Override
    public void addBike(final String manufacturerName, final String model, final String photoUrl, final int weight, final int championshipYear, final String teamName) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final String insert = "insert into MOTO(nomeMarca, modello, foto, peso, annoCampionato, nomeTeam) values (?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, manufacturerName);
            statement.setString(2, model);
            statement.setString(3, photoUrl);
            statement.setInt(4, weight);
            statement.setInt(5, championshipYear);
            statement.setString(6, teamName);
            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }
    }

    public ObservableList<Bike> getBikesFromManufacturer(String manufacturer){

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Bike> list = FXCollections.observableArrayList();
        final String retrieve = "select * from MOTO where nomeMarca = ?";
        
        try {
            PreparedStatement statement = null;
            ResultSet result = null;
        	statement = conn.prepareStatement(retrieve);
        	statement.setString(1, manufacturer);
            result = statement.executeQuery();
            
            while (result.next()) {
            	list.add(new Bike(result.getString("nomeMarca"), result.getString("modello"), result.getString("foto"), result.getInt("peso"), result.getInt("annoCampionato"), result.getString("nomeTeam")));
                
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return list;
    }
}
