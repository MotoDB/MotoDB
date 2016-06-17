package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
                			result.getString("localita"), result.getInt("curveDestra"), result.getInt("curveSinistra"), result.getInt("lunghezza"), 
                			result.getInt("rettilineoMax"), result.getString("foto"), result.getString("record"), result.getInt("pilotaRecord"), result.getInt("annoRecord")));
            }
        } catch (SQLException e) {
        	try{
	            AlertTypes alert = new AlertTypesImpl();
	            alert.showError(e);
        	} catch(ExceptionInInitializerError ei){
	        	e.printStackTrace();
	        }
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
            	try{
    	            AlertTypes alert = new AlertTypesImpl();
    	            alert.showError(e);
            	} catch(ExceptionInInitializerError ei){
    	        	e.printStackTrace();
    	        }
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
    public void addCircuit(String name, String state, String location, int rightHanders, int leftHanders, int lenght, int straight, String photo, Optional<String> record, Optional<Integer> recordRider, Optional<Integer> recordYear) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        java.sql.PreparedStatement statement = null;
        final String insert = "insert into CIRCUITO(nomeCircuito, stato, localita, curveDestra, curveSinistra, lunghezza, rettilineoMax, foto, record, pilotaRecord, annoRecord) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, state);
            statement.setString(3, location);
            statement.setInt(4, rightHanders);
            statement.setInt(5, leftHanders);
            statement.setInt(6, lenght);
            statement.setInt(7, straight);
            statement.setString(8, photo);
            if(record.isPresent() && recordRider.isPresent() && recordYear.isPresent()){
	            statement.setString(9, record.get());
	            statement.setInt(10, recordRider.get());
	            statement.setInt(11, recordYear.get());
            }else{
            	statement.setNull(9, java.sql.Types.INTEGER);
            	statement.setNull(10, java.sql.Types.INTEGER);
            	statement.setNull(11, java.sql.Types.INTEGER);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
        	try{
	            AlertTypes alert = new AlertTypesImpl();
	            alert.showError(e);
        	} catch(ExceptionInInitializerError ei){
	        	e.printStackTrace();
	        }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
            	try{
    	            AlertTypes alert = new AlertTypesImpl();
    	            alert.showError(e);
            	} catch(ExceptionInInitializerError ei){
    	        	e.printStackTrace();
    	        }
            }
        }
    }

}
