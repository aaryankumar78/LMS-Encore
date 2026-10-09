package com.smartlms.servlet;
import com.smartlms.dao.AssignmentDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {
 private final AssignmentDAO dao=new AssignmentDAO();
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  String answer=req.getParameter("answer"); if(answer==null||answer.trim().isEmpty()||answer.length()>10000){resp.sendError(400,"Answer is required (max 10,000 characters).");return;}
  try {long id=Long.parseLong(req.getParameter("assignmentId")); User u=(User)req.getSession().getAttribute("user");
   if(!dao.submit(id,u.getId(),answer.trim())) {resp.sendError(403,"Enroll in the course before submitting.");return;}
   resp.sendRedirect(req.getContextPath()+"/assignments?submitted=1");
  } catch(NumberFormatException e){resp.sendError(400,"Invalid assignment.");}
    catch(SQLException e){throw new ServletException("Unable to save submission.",e);}
 }
}
