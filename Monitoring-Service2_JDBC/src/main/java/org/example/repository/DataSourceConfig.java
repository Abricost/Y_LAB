package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConfig {

    //private static final String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=testSchema";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String USER_PASSWORD = "pass";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
    }
}
