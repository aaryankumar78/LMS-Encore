CREATE DATABASE IF NOT EXISTS smart_lms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE smart_lms;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('ADMIN','STUDENT') NOT NULL DEFAULT 'STUDENT',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(140) NOT NULL,
  description TEXT NOT NULL,
  instructor VARCHAR(100) NOT NULL,
  created_by BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_course_creator FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS enrollments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  student_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  enrolled_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_enrollment (student_id, course_id),
  CONSTRAINT fk_enroll_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_enroll_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS assignments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  title VARCHAR(140) NOT NULL,
  instructions TEXT NOT NULL,
  due_date DATE NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_assignment_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS submissions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  assignment_id BIGINT NOT NULL,
  student_id BIGINT NOT NULL,
  answer_text TEXT NOT NULL,
  submitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_submission (assignment_id, student_id),
  CONSTRAINT fk_submission_assignment FOREIGN KEY (assignment_id) REFERENCES assignments(id) ON DELETE CASCADE,
  CONSTRAINT fk_submission_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Demo admin login: admin@smartlms.com / Admin@123
-- The password is PBKDF2-HMAC-SHA256 (120,000 iterations), stored as salt:hash in Base64.

INSERT IGNORE INTO users (full_name, email, password_hash, role)
VALUES ('Smart LMS Admin', 'admin@smartlms.com', 'U21hcnRMTVMtZGVtby1zYWx0:r+ygrzJfTOMLP5w0reLe8sDdAchPNp5M/wxVC4qWnGc=', 'ADMIN');

INSERT IGNORE INTO courses (title, description, instructor, created_by)
SELECT 'Java Programming Basics', 'Learn classes, objects, methods, inheritance and exception handling with practical examples.', 'Smart LMS Team', id
FROM users WHERE email = 'admin@smartlms.com';

INSERT IGNORE INTO courses (title, description, instructor, created_by)
SELECT 'Database Fundamentals', 'Understand relational databases, SQL queries, keys and JDBC connectivity.', 'Smart LMS Team', id
FROM users WHERE email = 'admin@smartlms.com';

INSERT IGNORE INTO assignments (course_id, title, instructions, due_date)
SELECT id, 'OOP Mini Task', 'Create a Java class with private fields, constructors, getters/setters and a short main method to test it.', DATE_ADD(CURDATE(), INTERVAL 7 DAY)
FROM courses WHERE title = 'Java Programming Basics';
