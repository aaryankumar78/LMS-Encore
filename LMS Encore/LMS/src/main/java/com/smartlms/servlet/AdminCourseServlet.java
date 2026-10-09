package com.smartlms.servlet;
import com.smartlms.dao.CourseDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/admin/course")
public class AdminCourseServlet extends HttpServlet {
 private final CourseDAO dao=new CourseDAO();
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  String title=clean(req.getParameter("title")), description=clean(req.getParameter("description")), instructor=clean(req.getParameter("instructor"));
  if(title.length()<3||title.length()>140||description.length()<10||description.length()>4000||instructor.length()<2||instructor.length()>100) {
   resp.sendError(400,"Check title, description and instructor lengths."); return;
  }
  User u=(User)req.getSession().getAttribute("user");
  try {dao.create(title,description,instructor,u.getId());resp.sendRedirect(req.getContextPath()+"/courses?created=1");}
  catch(SQLException e){throw new ServletException("Unable to create course.",e);}
 }
 private String clean(String s){return s==null?"":s.trim();}
}
