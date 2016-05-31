package com.motodb.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}

