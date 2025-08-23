# Quick Start Guide - University Database Management System

## 🚀 Immediate Setup

### 1. Prerequisites Check
- ✅ Java 17+ installed
- ✅ SQL Server running with `StudentInformation` database
- ✅ Database user `sa` with password `12345`

### 2. Load Sample Data (First Time Only)
```bash
# Double-click this file or run in terminal:
load-sample-data.bat
```

### 3. Run the Application
```bash
# Double-click this file or run in terminal:
run-app.bat
```

## 🎯 Lab Sheet 04 Requirements - COMPLETED ✅

### Part 1: Insert Data into Tables
- **Courses**: IT, SE, CSNE, DS with descriptions and fees
- **Students**: Kamal, Pubudu, Ann, Malith with full details
- **Modules**: SE3050, IT1010, IT2050, IT3051 with credits
- **Course Modules**: All relationships properly established

### Part 2: Update Data
- **Update Ann's Address**: Use the "Update Data" tab
- Enter "Ann" in student name field
- Enter new address
- Click "Update Student Address"

### Part 3: Delete Data
- **Delete Module**: Use the "Delete Data" tab
- Enter "User Experience Engineering" in module name field
- Click "Delete Module" (with confirmation)

## 🔧 Database Connection
- **Server**: localhost:1433
- **Database**: StudentInformation
- **User**: sa
- **Password**: 12345
- **Encryption**: false

## 📱 Application Features
- **Tab 1**: Insert Data (Courses, Students, Modules, Course-Modules)
- **Tab 2**: Update Data (Student Address Updates)
- **Tab 3**: Delete Data (Module Removal)
- **Tab 4**: View Data (Browse All Tables)
- **Status Bar**: Real-time operation feedback

## 🚨 Troubleshooting
1. **Connection Failed**: Check SQL Server is running
2. **Database Not Found**: Create `StudentInformation` database
3. **Permission Denied**: Verify user `sa` has full access
4. **Foreign Key Error**: Insert data in order: Courses → Students → Modules → CourseModules

## 📁 Files Created
- `HelloApplication.java` - Main application entry point
- `DatabaseController.java` - Database operations controller
- `SampleDataLoader.java` - Sample data utility
- `database-view.fxml` - Main UI layout
- `run-app.bat` - Windows launcher
- `load-sample-data.bat` - Data loader launcher

## 🎉 Ready to Use!
The application is fully functional and meets all Lab Sheet 04 requirements. 