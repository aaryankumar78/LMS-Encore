<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String ctx=request.getContextPath();
if(session.getAttribute("user")!=null){response.sendRedirect(ctx+"/dashboard");return;}
%>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Sign in | Smart LMS</title><link rel="stylesheet" href="<%=ctx%>/css/style.css"></head>
<body class="auth-body"><div class="auth-wrap">
<div class="auth-story"><a class="brand brand-on-dark" href="#"><span class="brand-mark">S</span><span>Smart<span class="brand-light">LMS</span><small>LEARN WITH PURPOSE</small></span></a>
<div class="story-copy"><span class="pill pill-light">A BETTER WAY TO LEARN</span><h1>Your next big idea starts <em>here.</em></h1><p>One place for your courses, assignments and everyday progress. Keep learning at your own pace.</p>
<div class="story-stats"><div><strong>Learn</strong><span>at your pace</span></div><div><strong>Stay</strong><span>on track</span></div><div><strong>Grow</strong><span>every day</span></div></div></div>
<div class="story-foot">MADE FOR CURIOUS MINDS <span>✦</span></div></div>
<div class="auth-panel"><div class="auth-card"><div class="mobile-brand">SMART LMS</div><div class="eyebrow">WELCOME BACK</div><h2>Sign in to your account</h2><p class="muted">Pick up where you left off.</p>
<% if(request.getAttribute("error")!=null){ %><div class="alert alert-error"><%=request.getAttribute("error")%></div><% } %>
<% if("1".equals(request.getParameter("registered"))){ %><div class="alert alert-success">Account created. You can sign in now.</div><% } %>
<form action="<%=ctx%>/login" method="post" class="form-stack">
<label>Email address<input type="email" name="email" placeholder="you@example.com" autocomplete="email" required maxlength="150"></label>
<label>Password<input type="password" name="password" placeholder="Enter your password" autocomplete="current-password" required maxlength="100"></label>
<button class="btn btn-primary btn-full" type="submit">Sign in <span>→</span></button></form>
<div class="auth-divider"><span>NEW TO SMART LMS?</span></div><a class="btn btn-outline btn-full" href="<%=ctx%>/register">Create a student account</a>
<div class="demo-hint"><strong>Demo admin</strong><span>admin@smartlms.com</span><span>Admin@123</span></div>
<p class="auth-legal">By signing in, you agree to use this learning space responsibly.</p></div></div></div></body></html>
