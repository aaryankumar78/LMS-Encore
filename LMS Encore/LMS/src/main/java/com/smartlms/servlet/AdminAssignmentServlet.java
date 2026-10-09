package com.smartlms.servlet;
import com.smartlms.dao.AssignmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/admin/assignment")
public class AdminAssignmentServlet extends HttpServlet {
 private final AssignmentDAO dao=new AssignmentDAO();
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  String title=clean(req.getParameter("title")), instructions=clean(req.getParameter("instructions")), due=clean(req.getParameter("dueDate"));
  if(title.length()<3||title.length()>140||instructions.length()<10||instructions.length()>4000) {resp.sendError(400,"Check assignment title and instructions.");return;}
  try {long courseId=Long.parseLong(req.getParameter("courseId")); dao.create(courseId,title,instructions,due); resp.sendRedirect(req.getContextPath()+"/assignments?created=1");}
  catch(NumberFormatException e){resp.sendError(400,"Invalid course.");}
  catch(IllegalArgumentException e){resp.sendError(400,"Due date must be YYYY-MM-DD.");}
  catch(SQLException e){throw new ServletException("Unable to create assignment.",e);}
 }
 private String clean(String s){return s==null?"":s.trim();}
}
