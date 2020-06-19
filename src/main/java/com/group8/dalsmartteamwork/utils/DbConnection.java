package com.group8.dalsmartteamwork.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static final String IGNORE_TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static DbConnection dbConnection;
    private String environment;
    private String user;
    private String password;
    private String connection;
    private String database;
    private Statement statement;
    private Connection conn;

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public void createDbConnection() {
        try {
            this.environment = System.getenv("db.environment");
            this.connection = System.getenv("db.connection");
            switch (this.environment) {
                case "TEST":
                    this.database = System.getenv("db.test.database");
                    this.user = System.getenv("db.test.user");
                    this.password = System.getenv("db.test.password");
                    break;

                case "PRODUCTION":
                    this.database = System.getenv("db.prod.database");
                    this.user = System.getenv("db.prod.user");
                    this.password = System.getenv("db.prod.password");
                    break;

                default:
                    this.database = System.getenv("db.dev.database");
                    this.user = System.getenv("db.dev.user");
                    this.password = System.getenv("db.dev.password");
            }
            conn = DriverManager.getConnection(this.connection + this.database + IGNORE_TIME_ZONE, this.user,
                    this.password);
            this.statement = conn.createStatement();
        } catch (Exception e) {
            // TODO: Add to Log
            e.printStackTrace();
            this.statement = null;
        }
    }

    public void closeConnection() {
        try {
            if (conn.isValid(120)) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return this.statement;
    }

    public Connection getConnection() {
        return this.conn;
    }
}