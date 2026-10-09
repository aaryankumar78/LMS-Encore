package com.smartlms.servlet;

import com.smartlms.dao.UserDAO;
import com.smartlms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO=new UserDAO();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        if(req.getSession().getAttribute("user")!=null) { resp.sendRedirect(req.getContextPath()+"/dashboard"); return; }
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        String email=clean(req.getParameter("email")).toLowerCase();
        String password=req.getParameter("password");
        if(email.isBlank()||password==null||password.isBlank()) { req.setAttribute("error","Please enter your email and password."); req.getRequestDispatcher("/login.jsp").forward(req,resp); return; }
        try {
            User user=userDAO.authenticate(email,password);
            if(user==null) { req.setAttribute("error","Email or password is incorrect."); req.getRequestDispatcher("/login.jsp").forward(req,resp); return; }
            req.getSession().invalidate();
            HttpSession session=req.getSession(true);
            session.setAttribute("user",user); session.setMaxInactiveInterval(30*60);
            resp.sendRedirect(req.getContextPath()+"/dashboard");
        } catch(SQLException e) { throw new ServletException("Unable to connect to the database. Check DB settings.",e); }
    }
    private String clean(String s){return s==null?"":s.trim();}
}
