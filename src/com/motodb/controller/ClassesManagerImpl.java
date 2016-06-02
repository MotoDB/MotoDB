package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.motodb.model.Classes;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassesManagerImpl implements ClassesManager {
        
    private ObservableList<Classes> showClasses() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Classes> listClasses = FXCollections.observableArrayList();
        final String retrieve = "select * from CLASSE";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                Classes classe = new Classes();
                classe.setName(result.getString("nomeClasse"));
                classe.setRules(result.getString("regolamento"));
                listClasses.add(classe);
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listClasses;
        
    }
    
    @Override
    public ObservableList<String> getClassesNames() {
    	ObservableList<Classes> classes = this.showClasses();
    	ObservableList<String> classesNames = FXCollections.observableArrayList();
    	for(Classes c : classes){
    		classesNames.add(c.getName());
    	}
    	return classesNames;
    }
    
    @Override
    public void addClass(final String name, final String rules) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final java.sql.PreparedStatement statement;
        final String insert = "insert into CLASSE(nomeClasse, regolamento) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, rules);
            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
    }

}
