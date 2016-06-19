package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Session;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessionManagerImpl implements SessionManager {

    @Override
    public ObservableList<Session> getSessions() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Session> sessions = FXCollections.observableArrayList();
        final String retrieve = "select * from SESSIONE";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(retrieve);
            result = statement.executeQuery();
            while (result.next()) {
                sessions.add(new Session(result.getString("nomeClasse"), result.getInt("annoCampionato"),
                        result.getDate("dataInizioWeekend"), result.getString("condizioniPista"),
                        result.getInt("temperaturaEsterna"), result.getInt("temperaturaAsfalto"),
                        result.getInt("percentualeUmidita"), result.getDate("dataInizioSessione"),
                        result.getString("codiceSessione"), result.getString("durataMax"), result.getString("tipo"),
                        result.getInt("numeroGiri")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }

        return sessions;

    }

    @Override
    public void addSession(String className, int year, Date weekendDate, String conditions, int airTemp, int groundTemp,
            int humidity, Date startDate, String code, String durationMax, String type, int laps) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        java.sql.PreparedStatement statement = null;
        final String insert = "insert into SESSIONE(nomeClasse, annoCampionato, dataInizioWeekend, condizioniPista, temperaturaEsterna, temperaturaAsfalto, percentualeUmidita, dataInizioSessione, codiceSessione, durataMax, tipo, numeroGiri) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, className);
            statement.setInt(2, year);
            statement.setDate(3, weekendDate);
            statement.setString(4, conditions);
            statement.setInt(5, airTemp);
            statement.setInt(6, groundTemp);
            statement.setInt(7, humidity);
            statement.setDate(8, startDate);
            statement.setString(9, code);
            statement.setString(10, durationMax);
            statement.setString(11, type);
            statement.setInt(12, laps);

            statement.executeUpdate();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            e.printStackTrace();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }

    }

    @Override
    public ObservableList<Session> getSessionByWeekendAndClass(String className, Date startDate) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Session> sessions = FXCollections.observableArrayList();
        final String retrieve = "select s.codiceSessione from SESSIONE s, WEEKEND w where w.dataInizio = ? && s.dataInizioWeekend = ? && s.nomeClasse = ?";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setDate(1, startDate);
            statement.setDate(2, startDate);
            statement.setString(3, className);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Session session = new Session();
                    session.setCode(result.getString(1));
                    sessions.add(session);
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
        return sessions;
    }

    @Override
    public ObservableList<Session> getSessionsFromWeekend(Date weekend) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Session> list = FXCollections.observableArrayList();
        final String retrieve = "select * from SESSIONE where dataInizioWeekend = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setDate(1, weekend);
            result = statement.executeQuery();

            while (result.next()) {
                list.add(new Session(result.getString("nomeClasse"), result.getInt("annoCampionato"),
                        result.getDate("dataInizioWeekend"), result.getString("condizioniPista"),
                        result.getInt("temperaturaEsterna"), result.getInt("temperaturaAsfalto"),
                        result.getInt("percentualeUmidita"), result.getDate("dataInizioSessione"),
                        result.getString("codiceSessione"), result.getString("durataMax"), result.getString("tipo"),
                        result.getInt("numeroGiri")));

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
