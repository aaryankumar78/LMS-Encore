<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.smartlms.model.*" %>
<% request.setAttribute("pageTitle","Assignments"); %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><span class="pill">KEEP MOVING FORWARD</span><h1>Assignments</h1><p class="muted">A place to turn what you learn into something real.</p></div><div class="intro-art intro-purple">✓</div></div>
<% if(request.getParameter("created")!=null){ %><div class="alert alert-success">Assignment published successfully.</div><% } %>
<% if(request.getParameter("submitted")!=null){ %><div class="alert alert-success">Your answer has been saved. You can update it until you are ready.</div><% } %>
<% if(currentUser.isAdmin()){ List<Course> courses=(List<Course>)request.getAttribute("courses"); %>
<div class="panel form-panel"><div class="panel-heading"><div><h2>Publish an assignment</h2><p class="muted">Set a clear task and a realistic due date.</p></div><span class="panel-badge">ADMIN</span></div>
<form action="<%=ctx%>/admin/assignment" method="post" class="admin-form">
<label>Course<select name="courseId" required><% for(Course c:courses){ %><option value="<%=c.getId()%>"><%=c.getTitle()%></option><% } %></select></label>
<label>Due date<input type="date" name="dueDate"></label>
<label class="full-field">Assignment title<input name="title" required minlength="3" maxlength="140" placeholder="e.g. OOP practice task"></label>
<label class="full-field">Instructions<textarea name="instructions" required minlength="10" maxlength="4000" rows="3" placeholder="Explain what students need to submit..."></textarea></label>
<div class="full-field form-actions"><button class="btn btn-primary" type="submit" <%=courses.isEmpty()?"disabled":""%>>Publish assignment <span>→</span></button></div>
</form></div>
<div class="section-heading"><div><h2>Student workflow</h2><p class="muted">Students see assignments for courses they have joined.</p></div></div>
<div class="empty-state compact-empty"><div class="empty-icon">✓</div><h3>Keep tasks clear and useful</h3><p>Published assignments appear on each enrolled student's assignment page.</p></div>
<% } else { List<Assignment> assignments=(List<Assignment>)request.getAttribute("assignments"); %>
<div class="assignment-list">
<% for(Assignment a:assignments){ %><article class="assignment-card"><div class="assignment-icon <%=a.isSubmitted()?"done-icon":""%>"><%=a.isSubmitted()?"✓":"↗"%></div><div class="assignment-main"><div class="assignment-meta"><span><%=a.getCourseTitle()%></span><span class="meta-dot">·</span><span>Due <%=a.getDueDate()==null?"date not set":a.getDueDate()%></span></div><h3><%=a.getTitle()%></h3><p><%=a.getInstructions()%></p><form action="<%=ctx%>/submit" method="post" class="submission-form"><input type="hidden" name="assignmentId" value="<%=a.getId()%>"><label class="sr-only" for="answer-<%=a.getId()%>">Your answer</label><textarea id="answer-<%=a.getId()%>" name="answer" rows="3" maxlength="10000" required placeholder="Write your answer or a short summary here..."><%=a.getAnswerText()==null?"":a.getAnswerText()%></textarea><div class="submission-foot"><span class="<%=a.isSubmitted()?"enrolled-label":"muted"%>"><%=a.isSubmitted()?"✓ Saved submission":"Not submitted yet"%></span><button class="btn btn-primary btn-small" type="submit"><%=a.isSubmitted()?"Update answer":"Submit answer"%> →</button></div></form></div></article>
<% } if(assignments.isEmpty()){ %><div class="empty-state"><div class="empty-icon">✓</div><h3>Nothing on your list yet</h3><p>Enroll in a course to see its assignments here.</p><a class="btn btn-primary" href="<%=ctx%>/courses">Browse courses →</a></div><% } %>
</div><% } %>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
