package com.smartlms.servlet;
import com.smartlms.dao.CourseDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
 private final CourseDAO dao=new CourseDAO();
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  try { long id=Long.parseLong(req.getParameter("courseId")); User u=(User)req.getSession().getAttribute("user");
   dao.enroll(u.getId(),id); resp.sendRedirect(req.getContextPath()+"/courses?enrolled=1");
  } catch(NumberFormatException e){resp.sendError(400,"Invalid course.");}
    catch(SQLException e){throw new ServletException("Unable to enroll.",e);}
 }
}
