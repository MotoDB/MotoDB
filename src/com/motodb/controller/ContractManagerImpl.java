package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Contract;
import com.motodb.view.AddContractControl.MemberType;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContractManagerImpl implements ContractManager{
	
	@Override
	public ObservableList<Contract> getContracts() {
		
		ObservableList<Contract> contracts = FXCollections.observableArrayList();
		
		contracts.addAll(this.getRiderContracts());
		contracts.addAll(this.getMechanicContracts());
		contracts.addAll(this.getEngineerContracts());
		
		return contracts;
	}
     
     	@Override
	 	public ObservableList<Contract> getRiderContracts() {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<Contract> contracts = FXCollections.observableArrayList();
	        
	        final String retrieve = "select * from CONTRATTO_PILOTA";
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            statement = conn.prepareStatement(retrieve);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Rider.toString(),
	                			result.getInt("codicePersonale"), result.getString("nomeTeam")));
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
	 	public ObservableList<Contract> getMechanicContracts() {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<Contract> contracts = FXCollections.observableArrayList();
	        
	        final String retrieve = "select * from CONTRATTO_MECCANICO";
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            statement = conn.prepareStatement(retrieve);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Mechanic.toString(),
	                			result.getInt("codicePersonale"), result.getString("nomeTeam")));
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
	 	public ObservableList<Contract> getEngineerContracts() {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        
	        ObservableList<Contract> contracts = FXCollections.observableArrayList();
	        
	        final String retrieve = "select * from CONTRATTO_INGEGNERE";
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            statement = conn.prepareStatement(retrieve);
	            result = statement.executeQuery();
	            while (result.next()) {
	            	contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Engineer.toString(),
	                			result.getInt("codicePersonale"), result.getString("nomeTeam")));
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
	    public void addContract(Integer year, MemberType memberType, Integer member, String team) {
	    	
	        final DBManager db = DBManager.getDB();
	        final Connection conn  = db.getConnection();
	        java.sql.PreparedStatement statement = null;
	        
	        final String insert;
	        
	        if(memberType.equals(MemberType.Rider)){
	        	insert = "insert into CONTRATTO_PILOTA(annoCampionato, codicePersonale, nomeTeam) values (?,?,?)";
	        }else if(memberType.equals(MemberType.Engineer)){
	        	insert = "insert into CONTRATTO_INGEGNERE(annoCampionato, codicePersonale, nomeTeam) values (?,?,?)";
	        }else{
	        	insert = "insert into CONTRATTO_MECCANICO(annoCampionato, codicePersonale, nomeTeam) values (?,?,?)";
	        }
	        
	        try {
	            statement = conn.prepareStatement(insert);
	            statement.setInt(1, year);
	            statement.setInt(2, member);
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
