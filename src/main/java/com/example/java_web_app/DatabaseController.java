package com.example.java_web_app;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.sql.*;
import java.util.Optional;

public class DatabaseController {
    
    // Database connection parameters
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentInformation;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
    
    // FXML elements for Insert Data tab
    @FXML private TextField courseIdField, courseNameField, courseFeeField;
    @FXML private TextArea courseDescField;
    
    @FXML private TextField studentIdField, studentNameField, studentDobField, studentNicField, studentCourseIdField;
    @FXML private TextArea studentAddressField;
    
    @FXML private TextField moduleCodeField, moduleNameField, moduleCreditsField;
    @FXML private TextArea moduleDescField;
    
    @FXML private TextField cmCourseIdField, cmModuleCodeField, cmYearField, cmSemesterField;
    
    // FXML elements for Update Data tab
    @FXML private TextField updateStudentNameField;
    @FXML private TextArea updateAddressField;
    
    // FXML elements for Delete Data tab
    @FXML private TextField deleteModuleNameField;
    
    // FXML elements for View Data tab
    @FXML private TextArea dataDisplayArea;
    
    // Status label
    @FXML private Label statusLabel;
    
    @FXML
    public void initialize() {
        updateStatus("Ready - Connected to database");
    }
    
    // Database connection method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Update status method
    private void updateStatus(String message) {
        statusLabel.setText(message);
        System.out.println(message);
    }
    
    // Insert Course
    @FXML
    private void insertCourse() {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Course (CID, Cname, C_Description, C_fee) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, courseIdField.getText());
            pstmt.setString(2, courseNameField.getText());
            pstmt.setString(3, courseDescField.getText());
            pstmt.setDouble(4, Double.parseDouble(courseFeeField.getText()));
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                updateStatus("Course inserted successfully!");
                clearCourseFields();
            } else {
                updateStatus("Failed to insert course.");
            }
            
        } catch (SQLException e) {
            updateStatus("Error inserting course: " + e.getMessage());
        } catch (NumberFormatException e) {
            updateStatus("Please enter a valid fee amount.");
        }
    }
    
    // Insert Student
    @FXML
    private void insertStudent() {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Student (SID, Sname, Address, Dob, NIC, CID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, studentIdField.getText());
            pstmt.setString(2, studentNameField.getText());
            pstmt.setString(3, studentAddressField.getText());
            pstmt.setDate(4, Date.valueOf(studentDobField.getText()));
            pstmt.setString(5, studentNicField.getText());
            pstmt.setString(6, studentCourseIdField.getText());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                updateStatus("Student inserted successfully!");
                clearStudentFields();
            } else {
                updateStatus("Failed to insert student.");
            }
            
        } catch (SQLException e) {
            updateStatus("Error inserting student: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            updateStatus("Please enter a valid date in YYYY-MM-DD format.");
        }
    }
    
    // Insert Module
    @FXML
    private void insertModule() {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Module (Mcode, Mname, M_Description, NoOfCredits) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, moduleCodeField.getText());
            pstmt.setString(2, moduleNameField.getText());
            pstmt.setString(3, moduleDescField.getText());
            pstmt.setInt(4, Integer.parseInt(moduleCreditsField.getText()));
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                updateStatus("Module inserted successfully!");
                clearModuleFields();
            } else {
                updateStatus("Failed to insert module.");
            }
            
        } catch (SQLException e) {
            updateStatus("Error inserting module: " + e.getMessage());
        } catch (NumberFormatException e) {
            updateStatus("Please enter a valid number of credits.");
        }
    }
    
    // Insert Course Module
    @FXML
    private void insertCourseModule() {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO CourseModule (CID, Mcode, Accadamic_year, Semester) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, cmCourseIdField.getText());
            pstmt.setString(2, cmModuleCodeField.getText());
            pstmt.setString(3, cmYearField.getText());
            pstmt.setInt(4, Integer.parseInt(cmSemesterField.getText()));
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                updateStatus("Course Module inserted successfully!");
                clearCourseModuleFields();
            } else {
                updateStatus("Failed to insert course module.");
            }
            
        } catch (SQLException e) {
            updateStatus("Error inserting course module: " + e.getMessage());
        } catch (NumberFormatException e) {
            updateStatus("Please enter a valid semester number.");
        }
    }
    
    // Update Student Address
    @FXML
    private void updateStudentAddress() {
        String studentName = updateStudentNameField.getText();
        String newAddress = updateAddressField.getText();
        
        if (studentName.isEmpty() || newAddress.isEmpty()) {
            updateStatus("Please enter both student name and new address.");
            return;
        }
        
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Student SET Address = ? WHERE Sname = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, newAddress);
            pstmt.setString(2, studentName);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                updateStatus("Student address updated successfully!");
                updateStudentNameField.clear();
                updateAddressField.clear();
            } else {
                updateStatus("No student found with that name.");
            }
            
        } catch (SQLException e) {
            updateStatus("Error updating student address: " + e.getMessage());
        }
    }
    
    // Delete Module
    @FXML
    private void deleteModule() {
        String moduleName = deleteModuleNameField.getText();
        
        if (moduleName.isEmpty()) {
            updateStatus("Please enter a module name to delete.");
            return;
        }
        
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete Module");
        alert.setContentText("Are you sure you want to delete the module: " + moduleName + "?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection conn = getConnection()) {
                String sql = "DELETE FROM Module WHERE Mname = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, moduleName);
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    updateStatus("Module deleted successfully!");
                    deleteModuleNameField.clear();
                } else {
                    updateStatus("No module found with that name.");
                }
                
            } catch (SQLException e) {
                updateStatus("Error deleting module: " + e.getMessage());
            }
        }
    }
    
    // View Courses
    @FXML
    private void viewCourses() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Course";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            StringBuilder sb = new StringBuilder();
            sb.append("COURSES:\n");
            sb.append("CID\tCname\t\tC_Description\t\t\t\tC_fee\n");
            sb.append("---\t-----\t\t-------------\t\t\t\t-----\n");
            
            while (rs.next()) {
                sb.append(rs.getString("CID")).append("\t");
                sb.append(rs.getString("Cname")).append("\t\t");
                sb.append(rs.getString("C_Description")).append("\t\t");
                sb.append(rs.getDouble("C_fee")).append("\n");
            }
            
            dataDisplayArea.setText(sb.toString());
            updateStatus("Courses displayed successfully!");
            
        } catch (SQLException e) {
            updateStatus("Error viewing courses: " + e.getMessage());
        }
    }
    
    // View Students
    @FXML
    private void viewStudents() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Student";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            StringBuilder sb = new StringBuilder();
            sb.append("STUDENTS:\n");
            sb.append("SID\t\tSname\tAddress\t\t\tDob\t\tNIC\t\tCID\n");
            sb.append("---\t\t-----\t-------\t\t\t---\t\t---\t\t---\n");
            
            while (rs.next()) {
                sb.append(rs.getString("SID")).append("\t");
                sb.append(rs.getString("Sname")).append("\t");
                sb.append(rs.getString("Address")).append("\t\t");
                sb.append(rs.getDate("Dob")).append("\t");
                sb.append(rs.getString("NIC")).append("\t");
                sb.append(rs.getString("CID")).append("\n");
            }
            
            dataDisplayArea.setText(sb.toString());
            updateStatus("Students displayed successfully!");
            
        } catch (SQLException e) {
            updateStatus("Error viewing students: " + e.getMessage());
        }
    }
    
    // View Modules
    @FXML
    private void viewModules() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Module";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            StringBuilder sb = new StringBuilder();
            sb.append("MODULES:\n");
            sb.append("Mcode\tMname\t\t\tM_Description\t\tNoOfCredits\n");
            sb.append("-----\t-----\t\t\t-------------\t\t-----------\n");
            
            while (rs.next()) {
                sb.append(rs.getString("Mcode")).append("\t");
                sb.append(rs.getString("Mname")).append("\t\t\t");
                sb.append(rs.getString("M_Description")).append("\t\t");
                sb.append(rs.getInt("NoOfCredits")).append("\n");
            }
            
            dataDisplayArea.setText(sb.toString());
            updateStatus("Modules displayed successfully!");
            
        } catch (SQLException e) {
            updateStatus("Error viewing modules: " + e.getMessage());
        }
    }
    
    // View Course Modules
    @FXML
    private void viewCourseModules() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM CourseModule";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            StringBuilder sb = new StringBuilder();
            sb.append("COURSE MODULES:\n");
            sb.append("CID\tMcode\tAccadamic_year\tSemester\n");
            sb.append("---\t-----\t---------------\t--------\n");
            
            while (rs.next()) {
                sb.append(rs.getString("CID")).append("\t");
                sb.append(rs.getString("Mcode")).append("\t");
                sb.append(rs.getString("Accadamic_year")).append("\t\t");
                sb.append(rs.getInt("Semester")).append("\n");
            }
            
            dataDisplayArea.setText(sb.toString());
            updateStatus("Course Modules displayed successfully!");
            
        } catch (SQLException e) {
            updateStatus("Error viewing course modules: " + e.getMessage());
        }
    }
    
    // Clear field methods
    private void clearCourseFields() {
        courseIdField.clear();
        courseNameField.clear();
        courseDescField.clear();
        courseFeeField.clear();
    }
    
    private void clearStudentFields() {
        studentIdField.clear();
        studentNameField.clear();
        studentAddressField.clear();
        studentDobField.clear();
        studentNicField.clear();
        studentCourseIdField.clear();
    }
    
    private void clearModuleFields() {
        moduleCodeField.clear();
        moduleNameField.clear();
        moduleDescField.clear();
        moduleCreditsField.clear();
    }
    
    private void clearCourseModuleFields() {
        cmCourseIdField.clear();
        cmModuleCodeField.clear();
        cmYearField.clear();
        cmSemesterField.clear();
    }
} 