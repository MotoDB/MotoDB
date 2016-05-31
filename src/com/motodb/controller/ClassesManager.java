package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.motodb.model.Classes;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

public class ClassesManager {
    
    
    public List<Classes> showClasses() {

        final DBManager db = new DBManager();
        final Connection conn  = db.getConnection();
        
        List<Classes> listClasses = new LinkedList<>();
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

}
