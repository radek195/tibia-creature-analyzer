package com.example.database;

import java.sql.*;

public class DbConnection {
    private final Connection connection;
    public static String SCHEMA = "tibia-creature-analyzer";

    public DbConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "password";

        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Could not connect to database with url: %s, username: %s, password: %s", url, username, password)
            );
        }
    }

    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public void executeUpdate(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }
}
