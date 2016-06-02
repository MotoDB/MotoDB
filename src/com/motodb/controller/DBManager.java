package com.motodb.controller;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBManager {
    
    // Info connessioni DB
    private static final String dbName = "MotoDB";
    private static final String url = "motodb.c1s5lipxxzvy.eu-west-1.rds.amazonaws.com";
    private static final String username = "Administrator";
    private static final String password = "cocomero";
    private static final DBManager DB = new DBManager();

    private Connection connection;
    private MysqlDataSource dataSource;
    
    private DBManager() {
    }
    
    public static DBManager getDB() {
        return DB;
    }
    
    public Connection getConnection()  {        
        return this.connection;
    }
    
    public void createConnection() {
        dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(url);
        dataSource.setDatabaseName(dbName);
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
