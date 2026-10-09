package com.smartlms.servlet;
import com.smartlms.dao.*;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
 private final CourseDAO courses=new CourseDAO(); private final AssignmentDAO assignments=new AssignmentDAO(); private final UserDAO users=new UserDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  User u=(User)req.getSession().getAttribute("user");
  try {
   req.setAttribute("courseCount",courses.countCourses()); req.setAttribute("assignmentCount",assignments.countAssignments());
   if(u.isAdmin()) { req.setAttribute("studentCount",users.countStudents()); req.setAttribute("enrollmentCount",courses.countEnrollments()); }
   else { req.setAttribute("courseList",courses.findForStudent(u.getId())); req.setAttribute("assignmentList",assignments.findForStudent(u.getId())); }
   req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
  } catch(SQLException e){throw new ServletException("Unable to load dashboard.",e);}
 }
}
