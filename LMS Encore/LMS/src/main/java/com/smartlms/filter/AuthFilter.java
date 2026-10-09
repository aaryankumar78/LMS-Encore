package com.smartlms.filter;

import com.smartlms.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns={"/dashboard","/courses","/assignments","/admin/*","/enroll","/submit"})
public class AuthFilter implements Filter {
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        User user=(User)req.getSession().getAttribute("user");
        if(user==null) { res.sendRedirect(req.getContextPath()+"/login"); return; }
        String path=req.getRequestURI().substring(req.getContextPath().length());
        if(path.startsWith("/admin/") && !user.isAdmin()) { res.sendError(HttpServletResponse.SC_FORBIDDEN,"Admin access only"); return; }
        if((path.equals("/enroll")||path.equals("/submit")) && user.isAdmin()) { res.sendError(HttpServletResponse.SC_FORBIDDEN); return; }
        chain.doFilter(request,response);
    }
}
