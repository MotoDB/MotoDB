package com.motodb.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChampionshipManager {
    
    private int year;
    private int edition;
    
    
    public void insertChampionship(final int year, final int edition) {
        final DBManager db = new DBManager();
        final Connection conn  = db.getConnection();
        
        final String sql = "INSERT INTO CAMPIONATO" +
                           "VALUES (year, edition);";
        try {
            final Statement stm = conn.createStatement();
            stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
}

