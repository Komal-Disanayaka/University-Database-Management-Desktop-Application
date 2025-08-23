package com.example.java_web_app;

import java.sql.*;

public class SampleDataLoader {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentInformation;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
    
    public static void main(String[] args) {
        SampleDataLoader loader = new SampleDataLoader();
        loader.loadAllSampleData();
    }
    
    public void loadAllSampleData() {
        try {
            loadCourses();
            loadStudents();
            loadModules();
            loadCourseModules();
            System.out.println("All sample data loaded successfully!");
        } catch (SQLException e) {
            System.err.println("Error loading sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadCourses() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Clear existing data
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Course");
            
            // Insert courses
            String sql = "INSERT INTO Course (CID, Cname, C_Description, C_fee) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // IT course
            pstmt.setString(1, "IT");
            pstmt.setString(2, "Information Technology");
            pstmt.setString(3, "The programme is designed for technically focused students who capabilities in programming");
            pstmt.setDouble(4, 175000);
            pstmt.executeUpdate();
            
            // SE course
            pstmt.setString(1, "SE");
            pstmt.setString(2, "Software Engineering");
            pstmt.setString(3, "Software engineering is the discipline of designing, creating and maintaining");
            pstmt.setDouble(4, 185000);
            pstmt.executeUpdate();
            
            // CSNE course
            pstmt.setString(1, "CSNE");
            pstmt.setString(2, "Computer Systems And Network Engineering");
            pstmt.setString(3, "The programme aims to provide students with the knowledge, skills, planning, and designing");
            pstmt.setDouble(4, 155000);
            pstmt.executeUpdate();
            
            // DS course
            pstmt.setString(1, "DS");
            pstmt.setString(2, "Data Science");
            pstmt.setString(3, "The meticulous curriculum focuses on the fundamentals of computer science, statistics, and applied mathematics");
            pstmt.setDouble(4, 170000);
            pstmt.executeUpdate();
            
            System.out.println("Courses loaded successfully!");
        }
    }
    
    private void loadStudents() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Clear existing data
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Student");
            
            // Insert students
            String sql = "INSERT INTO Student (SID, Sname, Address, Dob, NIC, CID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Kamal
            pstmt.setString(1, "CN18384756");
            pstmt.setString(2, "Kamal");
            pstmt.setString(3, "No122, Rose street, matale");
            pstmt.setDate(4, Date.valueOf("1994-05-02"));
            pstmt.setString(5, "946785467v");
            pstmt.setString(6, "CSNE");
            pstmt.executeUpdate();
            
            // Pubudu
            pstmt.setString(1, "DS18234876");
            pstmt.setString(2, "Pubudu");
            pstmt.setString(3, "No678 , 3rd new lane, Maharahgama");
            pstmt.setDate(4, Date.valueOf("1994-11-08"));
            pstmt.setString(5, "948763759v");
            pstmt.setString(6, "DS");
            pstmt.executeUpdate();
            
            // Ann
            pstmt.setString(1, "IT18234568");
            pstmt.setString(2, "Ann");
            pstmt.setString(3, "No12, Kings street, colombo");
            pstmt.setDate(4, Date.valueOf("1996-11-11"));
            pstmt.setString(5, "961234587v");
            pstmt.setString(6, "IT");
            pstmt.executeUpdate();
            
            // Malith
            pstmt.setString(1, "SE19238567");
            pstmt.setString(2, "Malith");
            pstmt.setString(3, "No08, st.thomas street, Kandy");
            pstmt.setDate(4, Date.valueOf("1992-12-20"));
            pstmt.setString(5, "922356785v");
            pstmt.setString(6, "SE");
            pstmt.executeUpdate();
            
            System.out.println("Students loaded successfully!");
        }
    }
    
    private void loadModules() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Clear existing data
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Module");
            
            // Insert modules
            String sql = "INSERT INTO Module (Mcode, Mname, M_Description, NoOfCredits) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // SE3050
            pstmt.setString(1, "SE3050");
            pstmt.setString(2, "User Experience Engineering");
            pstmt.setString(3, "subject under SE");
            pstmt.setInt(4, 4);
            pstmt.executeUpdate();
            
            // IT1010
            pstmt.setString(1, "IT1010");
            pstmt.setString(2, "Introduction to Programming");
            pstmt.setString(3, "subject under IT");
            pstmt.setInt(4, 3);
            pstmt.executeUpdate();
            
            // IT2050
            pstmt.setString(1, "IT2050");
            pstmt.setString(2, "Computer Networks");
            pstmt.setString(3, "subject under IT");
            pstmt.setInt(4, 4);
            pstmt.executeUpdate();
            
            // IT3051
            pstmt.setString(1, "IT3051");
            pstmt.setString(2, "Fundamentals of Data Mining");
            pstmt.setString(3, "subject under DS");
            pstmt.setInt(4, 4);
            pstmt.executeUpdate();
            
            System.out.println("Modules loaded successfully!");
        }
    }
    
    private void loadCourseModules() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Clear existing data
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM CourseModule");
            
            // Insert course modules
            String sql = "INSERT INTO CourseModule (CID, Mcode, Accadamic_year, Semester) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // SE3050 - Y3, Semester 2
            pstmt.setString(1, "SE");
            pstmt.setString(2, "SE3050");
            pstmt.setString(3, "Y3");
            pstmt.setInt(4, 2);
            pstmt.executeUpdate();
            
            // IT1010 - Y1, Semester 2
            pstmt.setString(1, "IT");
            pstmt.setString(2, "IT1010");
            pstmt.setString(3, "Y1");
            pstmt.setInt(4, 2);
            pstmt.executeUpdate();
            
            // IT2050 - Y2, Semester 1
            pstmt.setString(1, "IT");
            pstmt.setString(2, "IT2050");
            pstmt.setString(3, "Y2");
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();
            
            // IT3051 - Y3, Semester 2
            pstmt.setString(1, "DS");
            pstmt.setString(2, "IT3051");
            pstmt.setString(3, "Y3");
            pstmt.setInt(4, 2);
            pstmt.executeUpdate();
            
            System.out.println("Course Modules loaded successfully!");
        }
    }
} 