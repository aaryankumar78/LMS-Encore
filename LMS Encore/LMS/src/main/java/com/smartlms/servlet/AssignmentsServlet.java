package com.smartlms.servlet;
import com.smartlms.dao.AssignmentDAO;
import com.smartlms.dao.CourseDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/assignments")
public class AssignmentsServlet extends HttpServlet {
 private final AssignmentDAO dao=new AssignmentDAO(); private final CourseDAO courseDAO=new CourseDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  User u=(User)req.getSession().getAttribute("user");
  try {
   req.setAttribute("assignments",u.isAdmin()?java.util.List.of():dao.findForStudent(u.getId()));
   if(u.isAdmin()) req.setAttribute("courses",courseDAO.findAll());
   req.getRequestDispatcher("/assignments.jsp").forward(req,resp);
  } catch(SQLException e){throw new ServletException("Unable to load assignments.",e);}
 }
}
