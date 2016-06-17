package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Manufacturer;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManufacturerManagerImpl implements ManufacturerManager {

	@Override
    public ObservableList<Manufacturer> getManufacturers() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Manufacturer> manufacturer = FXCollections.observableArrayList();
        final String retrieve = "select * from MARCA";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
            	manufacturer.add(new Manufacturer(result.getString("nome"), result.getString("logo")));
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
        
        return manufacturer;
        
    }
	
    @Override
    public void addManufacturer(String name, String urlLogo) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        java.sql.PreparedStatement statement = null;
        final String insert = "insert into MARCA(nome, logo) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, urlLogo);
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
