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
        
        ResultSet rs;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM CAMPIONATO");
            while (rs.next()) {
                System.out.println(rs.getString("anno"));
                System.out.println(rs.getString("edizione"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
