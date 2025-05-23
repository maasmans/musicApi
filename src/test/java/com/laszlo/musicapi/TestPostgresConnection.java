package com.laszlo.musicapi;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestPostgresConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
