package com.smartlms.dao;

import com.smartlms.model.Assignment;
import com.smartlms.util.DBUtil;
import java.sql.*;
import java.util.*;

public class AssignmentDAO {
    public List<Assignment> findForStudent(long studentId) throws SQLException {
        String sql="SELECT a.id,a.course_id,c.title AS course_title,a.title,a.instructions,DATE_FORMAT(a.due_date,'%d %b %Y') AS due_date,"+
          "(s.id IS NOT NULL) AS submitted, s.answer_text FROM assignments a JOIN courses c ON c.id=a.course_id "+
          "JOIN enrollments e ON e.course_id=c.id AND e.student_id=? LEFT JOIN submissions s ON s.assignment_id=a.id AND s.student_id=? "+
          "ORDER BY a.due_date IS NULL,a.due_date";
        List<Assignment> list=new ArrayList<>();
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setLong(1,studentId); ps.setLong(2,studentId);
            try(ResultSet rs=ps.executeQuery()) { while(rs.next()) list.add(new Assignment(rs.getLong("id"),rs.getLong("course_id"),
                rs.getString("course_title"),rs.getString("title"),rs.getString("instructions"),rs.getString("due_date"),
                rs.getBoolean("submitted"),rs.getString("answer_text"))); }
        }
        return list;
    }
    public void create(long courseId,String title,String instructions,String dueDate) throws SQLException {
        String sql="INSERT INTO assignments(course_id,title,instructions,due_date) VALUES(?,?,?,?)";
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setLong(1,courseId); ps.setString(2,title); ps.setString(3,instructions);
            if(dueDate==null || dueDate.isBlank()) ps.setNull(4,Types.DATE); else ps.setDate(4,Date.valueOf(dueDate));
            ps.executeUpdate();
        }
    }
    public boolean submit(long assignmentId,long studentId,String answer) throws SQLException {
        String sql="INSERT INTO submissions(assignment_id,student_id,answer_text) "+
          "SELECT a.id,?,? FROM assignments a JOIN enrollments e ON e.course_id=a.course_id AND e.student_id=? WHERE a.id=? "+
          "ON DUPLICATE KEY UPDATE answer_text=VALUES(answer_text),submitted_at=CURRENT_TIMESTAMP";
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setLong(1,studentId); ps.setString(2,answer); ps.setLong(3,studentId); ps.setLong(4,assignmentId);
            return ps.executeUpdate()>0;
        }
    }
    public int countAssignments() throws SQLException {
        try(Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM assignments"); ResultSet rs=ps.executeQuery()) {
            rs.next(); return rs.getInt(1);
        }
    }
}
