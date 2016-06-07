package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.motodb.model.Clax;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClaxManagerImpl implements ClaxManager {
        
    public ObservableList<Clax> getClasses() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Clax> classes = FXCollections.observableArrayList();
        final String retrieve = "select * from CLASSE";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
                classes.add(new Clax(result.getString("nomeClasse"), result.getString("regolamento")));
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
        
        return classes;
        
    }
    
    @Override
    public ObservableList<String> getClassesNames() {
    	ObservableList<Clax> classes = this.getClasses();
    	ObservableList<String> names = FXCollections.observableArrayList();
    	for(Clax c : classes){
    		names.add(c.getName());
    	}
    	return names;
    }
    
    @Override
    public void addClass(final String name, final String rules, final Integer index) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final String insert = "insert into CLASSE(nomeClasse, regolamento, indiceImportanza) values (?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, rules);
            statement.setInt(3, index);
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
