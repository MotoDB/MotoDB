package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Circuit;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CircuitManagerImpl implements CircuitManager {

    public ObservableList<Circuit> getCircuits() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Circuit> circuits = FXCollections.observableArrayList();
        final String retrieve = "select * from CIRCUITO";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
                circuits.add(new Circuit(result.getString("nomeCircuito"), result.getString("stato"),
                			result.getString("localita"), result.getInt("capienzaMassima"), 
                			result.getInt("curveDestra"), result.getInt("curveSinistra"), result.getInt("lunghezza"), 
                			result.getInt("rettilineoMax"), result.getDate("dataCostruzione"), 
                			result.getString("foto"), result.getInt("tempoRecord")));
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
        
        return circuits;
        
    }
    
    public ObservableList<String> getCircuitsNames() {
    	ObservableList<Circuit> circuitsNames = this.getCircuits();
    	ObservableList<String> names = FXCollections.observableArrayList();
    	for(Circuit s : circuitsNames){
    		names.add(s.getName());
    	}
    	return names;
    }
	
    @Override
    public void addCircuit(String name, String state, String location, int capacity, int rightHanders, int leftHanders, int lenght, int straight, Date date, String photo, int record) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        java.sql.PreparedStatement statement = null;
        final String insert = "insert into CIRCUITO(nomeCircuito, stato, localita, capienzaMassima, curveDestra, curveSinistra, lunghezza, rettilineoMax, dataCostruzione, foto, tempoRecord) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, state);
            statement.setString(3, location);
            statement.setInt(4, capacity);
            statement.setInt(5, rightHanders);
            statement.setInt(6, leftHanders);
            statement.setInt(7, lenght);
            statement.setInt(8, straight);
            statement.setDate(9, date);
            statement.setString(10, photo);
            statement.setInt(11, record);
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

}
