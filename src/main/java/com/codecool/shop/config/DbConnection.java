package com.codecool.shop.config;

import java.sql.*;

public class DbConnection {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/webshop";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
