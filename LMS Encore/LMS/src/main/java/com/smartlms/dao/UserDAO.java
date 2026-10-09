package com.smartlms.dao;

import com.smartlms.model.User;
import com.smartlms.util.DBUtil;
import com.smartlms.util.PasswordUtil;
import java.sql.*;

public class UserDAO {
    public User authenticate(String email, String password) throws SQLException {
        String sql = "SELECT id, full_name, email, password_hash, role FROM users WHERE email = ?";
        try (Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs=ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verify(password, rs.getString("password_hash"))) {
                    return new User(rs.getLong("id"), rs.getString("full_name"), rs.getString("email"), rs.getString("role"));
                }
            }
        }
        return null;
    }
    public boolean registerStudent(String name, String email, String password) throws SQLException {
        String sql = "INSERT INTO users(full_name,email,password_hash,role) VALUES(?,?,?,'STUDENT')";
        try (Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,name); ps.setString(2,email); ps.setString(3,PasswordUtil.hash(password));
            return ps.executeUpdate()==1;
        }
    }
    public int countStudents() throws SQLException {
        try (Connection con=DBUtil.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM users WHERE role='STUDENT'");
             ResultSet rs=ps.executeQuery()) { rs.next(); return rs.getInt(1); }
    }
}
