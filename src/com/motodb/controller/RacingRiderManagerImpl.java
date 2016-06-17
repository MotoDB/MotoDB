package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Mechanic;
import com.motodb.model.Member;
import com.motodb.model.Rider;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RacingRiderManagerImpl implements RacingRiderManager {

    @Override
    public void addRacingRider(int year, Date weekendDate, String className, String sessionCode,
            String fastestTime, Integer position, Integer averageSpeed, boolean finished, int personalCode, String manufacturer, String bikeModel,
            int points) {
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        java.sql.PreparedStatement statement = null;
        final String insert = "insert into PILOTA_IN_SESSIONE(annoCampionato, dataInizioWeekend, nomeClasse, codiceSessione, tempoVeloce, indicePosizione, velocitaMedia, posizionato, codicePersonalePilota, nomeMarcaMoto, modelloMoto, valorePunteggio) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setInt(1, year);
            statement.setDate(2, weekendDate);
            statement.setString(3, className);
            statement.setString(4, sessionCode);
            statement.setString(5, fastestTime);
            statement.setInt(6, position);
            statement.setInt(7, averageSpeed);
            statement.setBoolean(8, finished);
            statement.setInt(9, personalCode);
            statement.setString(10, manufacturer);
            statement.setString(11, bikeModel);
            statement.setInt(12, points);
            statement.executeUpdate();
            statement.close();
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

   @Override
   public ObservableList<RacingRider> getRacingRiders() {
        
        final DBManager db = DBManager.getDB();
        final Connection conn  = db.getConnection();
        
        ObservableList<RacingRider> list = FXCollections.observableArrayList();
        final String retrieve = "select * from PILOTA_IN_SESSIONE";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new RacingRider(result.getInt("annoCampionato"), result.getDate("dataInizioWeekend"), result.getString("nomeClasse"), result.getString("codiceSessione"), result.getString("tempoVeloce"), result.getInt("indicePosizione"), result.getInt("velocitaMedia"), result.getBoolean("posizionato"), result.getInt("codicePersonalePilota"), result.getString("nomeMarcaMoto"), result.getString("modelloMoto"), result.getInt("valorePunteggio")));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }
        
        return list;
    }

}
