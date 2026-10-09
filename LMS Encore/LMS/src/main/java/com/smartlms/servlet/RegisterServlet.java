package com.smartlms.servlet;
import com.smartlms.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
 private final UserDAO dao=new UserDAO();
 private static final Pattern EMAIL=Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
   req.getRequestDispatcher("/register.jsp").forward(req,resp);
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
   String name=clean(req.getParameter("name")), email=clean(req.getParameter("email")).toLowerCase(), pass=req.getParameter("password");
   if(name.length()<2||name.length()>100||!EMAIL.matcher(email).matches()||pass==null||pass.length()<8||pass.length()>100) {
     req.setAttribute("error","Enter a name, valid email and password (8–100 characters)."); req.getRequestDispatcher("/register.jsp").forward(req,resp); return;
   }
   try { dao.registerStudent(name,email,pass); resp.sendRedirect(req.getContextPath()+"/login?registered=1"); }
   catch(SQLException e) {
     if("23000".equals(e.getSQLState())) { req.setAttribute("error","This email is already registered."); req.getRequestDispatcher("/register.jsp").forward(req,resp); }
     else throw new ServletException("Could not register student.",e);
   }
 }
 private String clean(String s){return s==null?"":s.trim();}
}
