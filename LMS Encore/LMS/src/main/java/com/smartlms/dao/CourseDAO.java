package com.smartlms.dao;

import com.smartlms.model.Course;
import com.smartlms.util.DBUtil;
import java.sql.*;
import java.util.*;

public class CourseDAO {
    public List<Course> findForStudent(long studentId) throws SQLException {
        String sql = "SELECT c.id,c.title,c.description,c.instructor, (e.id IS NOT NULL) AS enrolled " +
                     "FROM courses c LEFT JOIN enrollments e ON c.id=e.course_id AND e.student_id=? ORDER BY c.created_at DESC";
        List<Course> list=new ArrayList<>();
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setLong(1,studentId);
            try(ResultSet rs=ps.executeQuery()) { while(rs.next()) list.add(new Course(rs.getLong("id"),rs.getString("title"),
                rs.getString("description"),rs.getString("instructor"),rs.getBoolean("enrolled"))); }
        }
        return list;
    }
    public List<Course> findAll() throws SQLException {
        List<Course> list=new ArrayList<>();
        String sql="SELECT id,title,description,instructor FROM courses ORDER BY created_at DESC";
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) list.add(new Course(rs.getLong("id"),rs.getString("title"),rs.getString("description"),rs.getString("instructor"),false));
        }
        return list;
    }
    public void create(String title,String description,String instructor,long adminId) throws SQLException {
        String sql="INSERT INTO courses(title,description,instructor,created_by) VALUES(?,?,?,?)";
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,title); ps.setString(2,description); ps.setString(3,instructor); ps.setLong(4,adminId); ps.executeUpdate();
        }
    }
    public boolean enroll(long studentId,long courseId) throws SQLException {
        String sql="INSERT IGNORE INTO enrollments(student_id,course_id) SELECT ?,id FROM courses WHERE id=?";
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setLong(1,studentId); ps.setLong(2,courseId); return ps.executeUpdate()==1;
        }
    }
    public int countCourses() throws SQLException {
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM courses"); ResultSet rs=ps.executeQuery()) {
            rs.next(); return rs.getInt(1);
        }
    }
    public int countEnrollments() throws SQLException {
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM enrollments"); ResultSet rs=ps.executeQuery()) {
            rs.next(); return rs.getInt(1);
        }
    }
}
