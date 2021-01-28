package com.rest.demo.services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "student";
    static final String PASS = "STUDENT";
    private static Database database;

    private Database() {
    }

    public static Database getInstance() {
        synchronized (Database.class) {
            if (database == null) {
                database = new Database();
            }
        }
        return database;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }
}