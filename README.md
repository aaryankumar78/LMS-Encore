# LMS Encore — Learning Management System

## 1. Project Description
LMS Encore is a Java-based web application designed to simplify online learning and academic course management. It provides a common platform where students can enroll in courses, access assignments, and submit their answers, while administrators can manage courses and assignments.

## 2. Features
- Student Registration and Login
- Admin Login and Role-Based Access
- Student and Admin Dashboards
- Course Creation and Management
- Student Course Enrollment
- Assignment Publishing
- Assignment Submission and Updating
- MySQL Database Integration
- Responsive Blue-and-White User Interface
- Secure Password Hashing

## 3. Technologies Used
- Java 17
- JSP (JavaServer Pages)
- Jakarta Servlets
- JDBC (Java Database Connectivity)
- MySQL 8+
- HTML
- CSS
- JavaScript
- Apache Tomcat 10.1
- Maven
- VS Code
- MySQL Workbench

## 4. System Requirements
- JDK 17 or newer
- Apache Maven 3.8+
- MySQL Server 8+
- MySQL Workbench
- Apache Tomcat 10.1
- Visual Studio Code

## 5. Installation and Setup

### Step 1: Database Setup
1. Open MySQL Workbench.
2. Connect to your MySQL server.
3. Open the `database.sql` file.
4. Execute the complete SQL script.
5. The database `smart_lms` and its tables will be created.

### Step 2: Configure Database Connection
Open the following file:

`src/main/java/com/smartlms/util/DBUtil.java`

Set your MySQL username and password using environment variables:

```powershell
$env:SMARTLMS_DB_USER="root"
$env:SMARTLMS_DB_PASSWORD="your_mysql_password"
```

Replace `your_mysql_password` with your actual MySQL password.

### Step 3: Build the Project
1. Extract the project ZIP file.
2. Open the project folder containing `pom.xml` in VS Code.
3. Open the integrated terminal.
4. Run the following command:

```bash
mvn clean package
```

After a successful build, the WAR file will be generated at:

`target/SmartLMS.war`

### Step 4: Run the Application
1. Copy `SmartLMS.war` into the Apache Tomcat 10.1 `webapps` folder.
2. Start Tomcat using `bin/startup.bat` on Windows.
3. Open your browser.
4. Visit:

`http://localhost:8080/SmartLMS/`

Note: The current deployment context is named SmartLMS. You can change the Maven WAR name to LMS-Encore if you want the application URL to reflect the project name.

## 6. Demo Admin Login

Email: `admin@smartlms.com`

Password: `Admin@123`

This account is intended for local demonstration only. Change or remove the demo credentials before any public deployment.

## 7. How to Use the Application

### Student
1. Open the application.
2. Click on Create a Student Account.
3. Register with your name, email, and password.
4. Log in to your account.
5. Browse available courses.
6. Enroll in a course.
7. Open Assignments.
8. Write and submit your answers.

### Admin
1. Log in using the demo admin account.
2. Open the Courses section.
3. Create a new course.
4. Open the Assignments section.
5. Publish assignments with instructions and optional due dates.
6. View course, assignment, student, and enrollment statistics on the dashboard.

## 8. Project Structure

```text
LMS-Encore/
├── pom.xml
├── database.sql
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/smartlms/
        │       ├── model/
        │       ├── dao/
        │       ├── servlet/
        │       ├── util/
        │       └── filter/
        └── webapp/
            ├── css/
            ├── js/
            ├── WEB-INF/
            ├── login.jsp
            ├── register.jsp
            ├── dashboard.jsp
            ├── courses.jsp
            └── assignments.jsp
```

## 9. Assessment Rubric Mapping (33 Marks)

### Problem Understanding and Solution Design — 8 Marks
The application addresses course and assignment management through defined student and administrator workflows and a relational database design.

### Core Java Concepts — 10 Marks
The project uses classes, objects, encapsulation, model classes, DAO classes, collections, validation, and exception handling.

### Database Integration (JDBC) — 8 Marks
The application uses JDBC connections, prepared statements, SQL queries, database constraints, and relational tables to manage data.

### Servlets and Web Integration — 7 Marks
Jakarta Servlets handle login, registration, logout, course enrollment, and assignment submissions. JSP pages provide the user interface, while sessions and filters manage access.

Final marks depend on the implementation, testing, demonstration, and ability to explain the code.

## 10. Troubleshooting

- Access Denied: Check the MySQL username and password.
- Unknown Database: Execute `database.sql` in MySQL Workbench.
- 404 Error: Check the Tomcat deployment and application URL.
- Servlet Errors: Use Apache Tomcat 10.1 because the project uses Jakarta Servlets.
- Maven Not Found: Install Maven and configure the system PATH.
- Database Connection Error: Ensure MySQL Server is running and the application has the correct database credentials.

## 11. Future Enhancements
- Assignment grading and feedback
- Password reset functionality
- Course search and filtering
- Attendance tracking
- Learning progress reports
- Email notifications
- Automated testing
- Improved security with CSRF protection and HTTPS

## 12. Conclusion
LMS Encore is an educational Java Web Application that demonstrates object-oriented programming, JDBC database connectivity, JSP, Servlets, and web application development. It provides a foundation for managing courses, student enrollment, and assignment submissions in a simple and organized environment.

Project Name: LMS Encore

Project Type: Java Web Application

Purpose: Academic Course and Assignment Management
