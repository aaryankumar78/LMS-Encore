<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String ctx=request.getContextPath(); %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Create account | Smart LMS</title><link rel="stylesheet" href="<%=ctx%>/css/style.css"></head>
<body class="register-body"><div class="register-card"><a class="brand" href="<%=ctx%>/login"><span class="brand-mark">S</span><span>Smart<span class="brand-light">LMS</span><small>LEARN WITH PURPOSE</small></span></a>
<div class="eyebrow">LET'S GET STARTED</div><h1>Create your account</h1><p class="muted">Join your courses and keep all your learning in one place.</p>
<% if(request.getAttribute("error")!=null){ %><div class="alert alert-error"><%=request.getAttribute("error")%></div><% } %>
<form action="<%=ctx%>/register" method="post" class="form-stack">
<label>Full name<input name="name" required minlength="2" maxlength="100" placeholder="Your name" autocomplete="name"></label>
<label>Email address<input type="email" name="email" required maxlength="150" placeholder="you@example.com" autocomplete="email"></label>
<label>Password<input type="password" name="password" required minlength="8" maxlength="100" placeholder="At least 8 characters" autocomplete="new-password"></label>
<button class="btn btn-primary btn-full" type="submit">Create account <span>→</span></button></form>
<p class="auth-bottom">Already have an account? <a href="<%=ctx%>/login">Sign in</a></p></div></body></html>
