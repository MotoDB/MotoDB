package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Weekend;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WeekendManagerImpl implements WeekendManager{
	
    @Override
	 public ObservableList<Weekend> getWeekends() {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<Weekend> weekends = FXCollections.observableArrayList();
	        final String retrieve = "select * from WEEKEND";
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            statement = conn.prepareStatement(retrieve);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	weekends.add(new Weekend(result.getInt("annoCampionato"), result.getString("nomeCircuito"),
	                			result.getDate("dataInizio"), result.getDate("dataFine")));
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
	        
	        return weekends;
	        
	    }

	    @Override
	    public void addWeekend(Integer year, String circuit, Date startDate, Date endDate) {
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        java.sql.PreparedStatement statement = null;
	        final String insert = "insert into WEEKEND(annoCampionato, nomeCircuito, dataInizio, dataFine) values (?,?,?,?)";
	        try {
	            statement = conn.prepareStatement(insert);
	            statement.setInt(1, year);
	            statement.setString(2, circuit);
	            statement.setDate(3, startDate);
	            statement.setDate(4, endDate);
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
	    

	    @Override
	    public ObservableList<Weekend> getWeekendsFromYear(int year) {
	        
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<Weekend> list = FXCollections.observableArrayList();
	        final String retrieve = "select * from WEEKEND where annocampionato = ?";
	        try {
	            PreparedStatement statement = null;
	            ResultSet result = null;
	        	statement = conn.prepareStatement(retrieve);
	        	statement.setInt(1, year);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	list.add(new Weekend(result.getInt("annoCampionato"), result.getString("nomeCircuito"), result.getDate("dataInizio"), result.getDate("dataFine")));
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
