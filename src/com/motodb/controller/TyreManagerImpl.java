package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.motodb.model.Tyre;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TyreManagerImpl implements TyreManager {
        
    public ObservableList<Tyre> getTyres() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Tyre> tyres = FXCollections.observableArrayList();
        final String retrieve = "select * from PNEUMATICO";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
                tyres.add(new Tyre(result.getString("marca"), result.getString("modello"), result.getString("misura"), result.getString("mescola")));
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
        
        return tyres;
        
    }
    
    @Override
    public void addTyre(final String make, final String model, final String size, final String compound) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final String insert = "insert into PNEUMATICO(marca, modello, misura, mescola) values (?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, make);
            statement.setString(2, model);
            statement.setString(3, size);
            statement.setString(4, compound);
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
