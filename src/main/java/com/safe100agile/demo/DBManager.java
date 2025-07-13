package com.safe100agile.demo;

import java.sql.*;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Monday12$";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println(" Connected to MySQL successfully!");
            createUsersTable(connection);
            insertUser(connection, "John Doe", "john@example.com");

        } catch (SQLException e) {
            System.out.println(" Connection failed!");
            e.printStackTrace();
        }
    }

    public static void createUsersTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "email VARCHAR(100) NOT NULL UNIQUE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(" Table 'users' ensured.");
        }
    }

    public static void insertUser(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println(" User inserted: " + name + ", " + email);
        }
    }
}

