package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.motodb.model.Championship;
import com.motodb.model.Classes;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChampionshipManagerImpl implements ChampionshipManager {    
    
    @Override
    public void insertChampionship(final int year, final int edition, final List<String> classes) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final java.sql.PreparedStatement statement;
        final String insert = "insert into CAMPIONATO(anno, edizione) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setInt(1, year);
            statement.setInt(2, edition);
            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        final String insert2 = "insert into CLASSE_IN_CAMPIONATO(annoCampionato, nomeClasse) values (?,?)";
        classes.forEach(e -> {
            java.sql.PreparedStatement statement2;
            try {
                statement2 = conn.prepareStatement(insert2);
                statement2.setInt(1, year);
                statement2.setString(2, e);
                statement2.executeUpdate();
            } catch (SQLException ex) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(ex);
            }
        });
        

    }
  
    @Override
    public List<Championship> showChampionship() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        List<Championship> listChampionship = new LinkedList<>();
        final String retrieve = "select * from CAMPIONATO";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                Championship championship = new Championship();
                championship.setYear(result.getInt("anno"));
                championship.setEdition(result.getInt("edizione"));
                listChampionship.add(championship);
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listChampionship;
        
    }
    
    @Override
    public ObservableList<Classes> showClasses(int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Classes> listClasses = FXCollections.observableArrayList();
        final String retrieve = "SELECT cla.nomeClasse " +
                                "from CLASSE_IN_CAMPIONATO c, CAMPIONATO ca, CLASSE cla " +
                                "WHERE c.annoCampionato=? " + 
                                "AND ca.anno=? " +
                                "AND cla.nomeClasse = c.nomeClasse " +
                                "order by indiceImportanza";
        
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            statement.setInt(1, year);
            statement.setInt(2, year);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                Classes classe = new Classes();
                classe.setName(result.getString("nomeClasse"));
                listClasses.add(classe);
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listClasses;
        
    }
    
}

