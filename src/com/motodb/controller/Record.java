package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.util.Pair;

public class Record {

    public Pair<String, Integer> getMostPointsByClass(String className) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        Pair<String, Integer> pair = null;

        final String retrieve = "SELECT p.cognomeMembro, "
                + "SUM(ps.valorePunteggio) as punteggio "
                + "from PILOTA_IN_SESSIONE ps inner join PILOTA p " + "on ps.codicePersonalePilota = p.codicePersonale "
                + "where ps.codiceSessione = ? "
                + "&& ps.nomeClasse = ? group by p.codicePersonale order by punteggio desc";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setString(1, "RACE");
            statement.setString(2, className);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    pair = new Pair<String, Integer>(result.getString(1), result.getInt(2));
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

        return pair;
    }
}
