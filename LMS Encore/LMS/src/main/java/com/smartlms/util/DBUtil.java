package com.smartlms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {
    private DBUtil() {}
    private static final String URL = System.getenv().getOrDefault(
        "SMARTLMS_DB_URL", "jdbc:mysql://localhost:3306/smart_lms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata");
    private static final String USER = System.getenv().getOrDefault("SMARTLMS_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("SMARTLMS_DB_PASSWORD", "change_me");

    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new ExceptionInInitializerError(e); }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
