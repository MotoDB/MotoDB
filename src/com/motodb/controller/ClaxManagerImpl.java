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
        final Connection conn = db.getConnection();

        ObservableList<Clax> classes = FXCollections.observableArrayList();
        final String retrieve = "select * from CLASSE order by indiceImportanza";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve);
                final ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                classes.add(new Clax(result.getString("nomeClasse"), result.getString("regolamento"),
                        result.getInt("indiceImportanza")));
            }
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return classes;

    }

    @Override
    public ObservableList<String> getClassesNames() {
        ObservableList<Clax> classes = this.getClasses();
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Clax c : classes) {
            names.add(c.getName());
        }
        return names;
    }

    @Override
    public void addClass(final String name, final String rules, final Integer index) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        final String insert = "insert into CLASSE(nomeClasse, regolamento, indiceImportanza) values (?,?,?)";
        try (final PreparedStatement statement = conn.prepareStatement(insert)) {
            statement.setString(1, name);
            statement.setString(2, rules);
            statement.setInt(3, index);
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }
    }

    @Override
	public ObservableList<Clax> getClassesFromYear(int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Clax> list = FXCollections.observableArrayList();
        final String retrieve = "select c.* from CLASSE c, CLASSE_IN_CAMPIONATO i where c.nomeClasse = i.nomeClasse AND i.annoCampionato = ?";
        
        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, year);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                	list.add(new Clax(result.getString("nomeClasse"), result.getString("regolamento"), result.getInt("indiceImportanza")));
                } 
            } catch (SQLException e) {
                try {
                    AlertTypes alert = new AlertTypesImpl();
                    alert.showError(e);
                } catch (ExceptionInInitializerError ei) {
                    e.printStackTrace();
                }
            }
            } catch (SQLException e) {
                try {
                    AlertTypes alert = new AlertTypesImpl();
                    alert.showError(e);
                } catch (ExceptionInInitializerError ei) {
                    e.printStackTrace();
                }
            }
        
        return list;
	}

}
