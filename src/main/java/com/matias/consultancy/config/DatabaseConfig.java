package com.matias.consultancy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
        private static final String URL = "jdbc:postgresql://localhost:5432/consultancy";
        private static final String USER = "postgres";
        private static final String PASSWORD = "matias2459";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
}
