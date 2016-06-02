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

public class ClassesManager {
    
    
    public ObservableList<Classes> showClasses() {

        final DBManager db = new DBManager();
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
    
    public ObservableList<String> getClassesNames() {
    	ObservableList<Classes> classes = this.showClasses();
    	ObservableList<String> classesNames = FXCollections.observableArrayList();
    	for(Classes c : classes){
    		classesNames.add(c.getName());
    	}
    	return classesNames;
    }

}
