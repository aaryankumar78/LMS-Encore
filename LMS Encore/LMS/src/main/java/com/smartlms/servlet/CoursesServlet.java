package com.smartlms.servlet;
import com.smartlms.dao.CourseDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
 private final CourseDAO dao=new CourseDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  User u=(User)req.getSession().getAttribute("user");
  try { req.setAttribute("courses",u.isAdmin()?dao.findAll():dao.findForStudent(u.getId())); req.getRequestDispatcher("/courses.jsp").forward(req,resp); }
  catch(SQLException e){throw new ServletException("Unable to load courses.",e);}
 }
}
