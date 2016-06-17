package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.ManufacturerContract;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManufacturerContractManagerImpl implements ManufacturerContractManager{
	
     	@Override
	 	public ObservableList<ManufacturerContract> getManufacturerContracts() {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<ManufacturerContract> contracts = FXCollections.observableArrayList();
	        
	        final String retrieve = "select * from CONTRATTO_MARCA";
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            statement = conn.prepareStatement(retrieve);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	contracts.add(new ManufacturerContract(result.getString("nomeMarca"), result.getInt("annoCampionato"), result.getString("nomeTeam")));
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
	        
	        return contracts;
	        
	    }
     	
          	
	    @Override
	    public void addManufacturerContract(String manufacurer, Integer year, String team) {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        java.sql.PreparedStatement statement = null;
	        
	        final String insert;
	       
	        insert = "insert into CONTRATTO_PILOTA(nomeMarca, annoCampionato, nomeTeam) values (?,?,?)";
	       
	        
	        try {
	            statement = conn.prepareStatement(insert);
	            statement.setString(1, manufacurer);
	            statement.setInt(2, year);
	            statement.setString(3, team);
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
