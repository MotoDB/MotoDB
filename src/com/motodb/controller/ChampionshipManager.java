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

public class ChampionshipManager {    
    
    public void insertChampionship(final int year, final int edition) {
        final DBManager db = new DBManager();
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

    }
    
    /*public void insertClasses(final int year, ) {
        final DBManager db = new DBManager();
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

    }*/
    
    public List<Championship> showChampionship() {

        final DBManager db = new DBManager();
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
    
    public List<Classes> showClasses(int year) {

        final DBManager db = new DBManager();
        final Connection conn  = db.getConnection();
        
        List<Classes> listClasses = new LinkedList<>();
        final String retrieve = "SELECT nomeClasse " +
                                "from CLASSE_IN_CAMPIONATO c, CAMPIONATO ca " +
                                "WHERE c.annoCampionato = ? " + 
                                "AND WHERE ca.anno = ? " +
                                "order by indiceImportanza";
        
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            statement.setInt(1, year);
            statement.setInt(2, year);
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

