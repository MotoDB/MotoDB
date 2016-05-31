package com.motodb.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DBManager db = new DBManager();
        Connection conn = db.getConnection();
        
        ChampionshipManager ch = new ChampionshipManager();
        ch.insertChampionship(2013, 64);

    }

}
