# University Database Management System

A JavaFX desktop application for managing university database operations including INSERT, UPDATE, DELETE, and VIEW operations on SQL Server.

## Features

- **Insert Data**: Add new courses, students, modules, and course-module relationships
- **Update Data**: Modify existing student information (e.g., update address)
- **Delete Data**: Remove modules from the database
- **View Data**: Display all records from different tables
- **User Feedback**: Real-time status updates for all database operations

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Microsoft SQL Server (with the `StudentInformation` database)
- SQL Server JDBC Driver (already included in pom.xml)

## Database Setup

1. **Create the Database**: Ensure you have a SQL Server instance running with a database named `StudentInformation`

2. **Database Connection**: Update the connection parameters in `DatabaseController.java` if needed:
   ```java
   private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentInformation;encrypt=false;";
   private static final String USER = "sa";
   private static final String PASSWORD = "12345";
   ```

3. **Load Sample Data**: Run the `SampleDataLoader` class to populate the database with the sample data from Lab Sheet 04:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.java_web_app.SampleDataLoader"
   ```

## Running the Application

### Option 1: Using Maven
```bash
mvn clean javafx:run
```

### Option 2: Using IDE
Run the `HelloApplication` class as a Java application.

## Application Usage

### 1. Insert Data Tab
- **Insert Course**: Add new courses with ID, name, description, and fee
- **Insert Student**: Add new students with all required information
- **Insert Module**: Add new modules with code, name, description, and credits
- **Insert Course Module**: Link courses with modules for specific academic years and semesters

### 2. Update Data Tab
- **Update Student Address**: Change the address of an existing student by name

### 3. Delete Data Tab
- **Delete Module**: Remove a module by name (with confirmation dialog)

### 4. View Data Tab
- **View Courses**: Display all course records
- **View Students**: Display all student records
- **View Modules**: Display all module records
- **View Course Modules**: Display all course-module relationships

## Sample Data (Lab Sheet 04)

The application is designed to work with the following sample data:

### Courses
- IT (Information Technology) - 175000
- SE (Software Engineering) - 185000
- CSNE (Computer Systems And Network Engineering) - 155000
- DS (Data Science) - 170000

### Students
- Kamal (CN18384756) - CSNE
- Pubudu (DS18234876) - DS
- Ann (IT18234568) - IT
- Malith (SE19238567) - SE

### Modules
- SE3050 (User Experience Engineering) - 4 credits
- IT1010 (Introduction to Programming) - 3 credits
- IT2050 (Computer Networks) - 4 credits
- IT3051 (Fundamentals of Data Mining) - 4 credits

## Lab Sheet Requirements

This application fulfills all the objectives from Lab Sheet 04:

✅ **Database Connectivity**: Connects to Microsoft SQL Server  
✅ **Form Input Capture**: Captures user inputs via JavaFX forms  
✅ **Data Manipulation**: Supports INSERT, UPDATE, and DELETE operations  
✅ **User Feedback**: Provides real-time feedback for all database operations  

### Part 1: Insert Data
- Insert courses, students, modules, and course-module relationships
- Follows proper order to avoid foreign key constraint errors

### Part 2: Update Data
- Update student address (e.g., update Ann's address)

### Part 3: Delete Data
- Delete modules (e.g., remove 'User Experience Engineering')

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/java_web_app/
│   │       ├── HelloApplication.java          # Main application class
│   │       ├── DatabaseController.java        # Main controller for database operations
│   │       ├── SampleDataLoader.java          # Utility to load sample data
│   │       └── MSSQLConnect.java              # Database connection utility
│   └── resources/
│       └── com/example/java_web_app/
│           └── database-view.fxml             # Main UI layout
```

## Troubleshooting

1. **Connection Issues**: Verify SQL Server is running and connection parameters are correct
2. **Database Not Found**: Ensure the `StudentInformation` database exists
3. **Permission Errors**: Check that the database user has appropriate permissions
4. **Foreign Key Constraints**: Insert data in the correct order (Courses → Students → Modules → CourseModules)

## Dependencies

- JavaFX 17.0.6
- SQL Server JDBC Driver 13.2.0
- ControlsFX 11.2.1
- Other UI enhancement libraries

## License

This project is created for educational purposes as part of Lab Sheet 04. 