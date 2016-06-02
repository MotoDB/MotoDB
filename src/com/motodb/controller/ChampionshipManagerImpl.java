package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.motodb.model.Championship;
import com.motodb.model.ChampionshipsView;
import com.motodb.model.Classes;
import com.motodb.model.Sponsor;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChampionshipManagerImpl implements ChampionshipManager {    
    
    @Override
    public void insertChampionship(final int year, final int edition, final ObservableList<String> classes, final ObservableList<String> sponsors) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        boolean ok = true;
        
        final java.sql.PreparedStatement statement;
        final String insert = "insert into CAMPIONATO(anno, edizione) values (?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setInt(1, year);
            statement.setInt(2, edition);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
        	ok = false;
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        if (ok) {
	        final String insert2 = "insert into CLASSE_IN_CAMPIONATO(annoCampionato, nomeClasse) values (?,?)";
	        classes.forEach(e -> {
	            java.sql.PreparedStatement statement2;
	            try {
	                statement2 = conn.prepareStatement(insert2);
	                statement2.setInt(1, year);
	                statement2.setString(2, e);
	                statement2.executeUpdate();
	                statement2.close();
	            } catch (SQLException ex) {
	                AlertTypes alert = new AlertTypesImpl();
	                alert.showError(ex);
	            }
	        });
	        
	        final String insert3 = "insert into SPONSORIZZAZIONE(annoCampionato, nomeSponsor) values (?,?)";
	        sponsors.forEach(e -> {
	            java.sql.PreparedStatement statement3;
	            try {
	                statement3 = conn.prepareStatement(insert3);
	                statement3.setInt(1, year);
	                statement3.setString(2, e);
	                statement3.executeUpdate();
	                statement3.close();
	            } catch (SQLException ex) {
	                AlertTypes alert = new AlertTypesImpl();
	                alert.showError(ex);
	            }
	        });
        }
        

    }
  
    @Override
    public ObservableList<Championship> showChampionship() {

        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Championship> listChampionship = FXCollections.observableArrayList();
        final String retrieve = "select * from CAMPIONATO order by anno";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                Championship championship = new Championship();
                championship.setYear(result.getInt("anno"));
                championship.setEdition(result.getInt("edizione"));
                listChampionship.add(championship);
            }
            result.close();
            statement.close();
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
            statement.close();
            result.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listClasses;
        
    }
    
    public ObservableList<String> getClassesStrings(int year){
    	
    	ObservableList<String> list = FXCollections.observableArrayList();
    	this.showClasses(year).forEach(e->{
    		list.add(e.getName());
    	});
    	return list;
    }

    public ObservableList<Sponsor> getSponsor(int year) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<Sponsor> listSponsor = FXCollections.observableArrayList();
        final String retrieve = "SELECT sponsor.nomeSponsor " +
                                "from SPONSOR sponsor, SPONSORIZZAZIONE s, CAMPIONATO cam " +
                                "where s.annoCampionato = ? " + 
                                "AND cam.anno = ? " +
                                "and s.nomeSponsor = sponsor.nomeSponsor ";
        
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            statement.setInt(1, year);
            statement.setInt(2, year);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                Sponsor sponsor = new Sponsor();
                sponsor.setName(result.getString("nomeSponsor"));
                listSponsor.add(sponsor);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return listSponsor;
    }
    
    
    public ObservableList<String> getSponsorStrings(int year){
    	
    	ObservableList<String> list = FXCollections.observableArrayList();
    	this.getSponsor(year).forEach(e->{
    		list.add(e.getName());
    	});
    	
    	return list;
    }
    
    
    public ObservableList<ChampionshipsView> getChampionshipViews() {
        
        ObservableList<ChampionshipsView> listCh = FXCollections.observableArrayList();
        
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        final String insert =   "SELECT anno, edizione, group_concat(distinct c.nomeClasse SEPARATOR ', ') as 'classi', group_concat(distinct spo.nomeSponsor SEPARATOR ', ') as 'sponsor' " +
                                "from CLASSE_IN_CAMPIONATO c, CAMPIONATO ca, SPONSORIZZAZIONE spo " +
                                "WHERE c.annoCampionato=ca.anno "+
                                "AND c.annoCampionato=spo.annoCampionato "+
                                "group by anno";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(insert);
            result = statement.executeQuery();
            while (result.next()) {
                ChampionshipsView view = new ChampionshipsView();
                view.setYear(result.getInt("anno"));
                view.setEdition(result.getInt("edizione"));
                view.setClasses(result.getString(3));
                view.setSponsors(result.getString(4));
                listCh.add(view);
            }
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
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
        return listCh;
    }
    
}

