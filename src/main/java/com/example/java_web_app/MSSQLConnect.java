package com.example.java_web_app;

import java.sql.*;

public class MSSQLConnect {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentInformation;encrypt=false;";
        String user = "sa";
        String password = "12345";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to SQL Server!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(  "SELECT * FROM Student"); // example query

            while (rs.next()) {
                System. out.println("Student Name: " + rs.getString( "sName"));

                System.out.println("Student Name: " + rs.getString( "sName"));

            }

        }
        catch (SQLException e){
            System.out.println("X Connection failed: " + e.getMessage());
     }
    }
}
