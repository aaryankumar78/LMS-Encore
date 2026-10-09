<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.smartlms.model.*" %>
<% request.setAttribute("pageTitle","Courses"); %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><span class="pill">THE CLASSROOM</span><h1>Courses</h1><p class="muted">Find a topic that interests you. Start with one small step.</p></div><div class="intro-art">▤</div></div>
<% if(request.getParameter("created")!=null){ %><div class="alert alert-success">Course created successfully.</div><% } %>
<% if(request.getParameter("enrolled")!=null){ %><div class="alert alert-success">You're enrolled in the course.</div><% } %>
<% if(currentUser.isAdmin()){ %>
<div class="panel form-panel"><div class="panel-heading"><div><h2>Create a course</h2><p class="muted">Add a useful course for your learners.</p></div><span class="panel-badge">ADMIN</span></div>
<form action="<%=ctx%>/admin/course" method="post" class="admin-form">
<label>Course title<input name="title" required minlength="3" maxlength="140" placeholder="e.g. Introduction to Java"></label>
<label>Instructor name<input name="instructor" required minlength="2" maxlength="100" placeholder="Instructor name"></label>
<label class="full-field">Course description<textarea name="description" required minlength="10" maxlength="4000" rows="3" placeholder="What will students learn in this course?"></textarea></label>
<div class="full-field form-actions"><button class="btn btn-primary" type="submit">Create course <span>＋</span></button></div>
</form></div><% } %>
<div class="section-heading"><div><h2><%=currentUser.isAdmin()?"Course library":"Explore the library"%></h2><p class="muted"><%=currentUser.isAdmin()?"Keep course details clear and up to date.":"Choose what you'd like to learn next."%></p></div><span class="count-chip">${courses.size()} courses</span></div>
<div class="course-grid">
<% List<Course> courses=(List<Course>)request.getAttribute("courses"); int i=0; for(Course c:courses){i++;%>
<div class="course-card"><div class="course-cover cover-<%=i%4%>"><span>LEARNING PATH</span><span class="cover-symbol"><%=i%2==0?"⌘":"✳"%></span></div><div class="course-card-body"><div class="course-meta"><span class="course-kicker"><%=c.getInstructor()%></span><span class="mini-tag">COURSE</span></div><h3><%=c.getTitle()%></h3><p><%=c.getDescription()%></p>
<% if(currentUser.isAdmin()){ %><div class="card-foot"><span class="muted small-text">Visible to students</span><span class="check-mark">✓</span></div>
<% } else if(c.isEnrolled()){ %><div class="card-foot"><span class="enrolled-label"><span>✓</span> Enrolled</span><a class="text-link" href="<%=ctx%>/assignments">Assignments →</a></div>
<% } else { %><form action="<%=ctx%>/enroll" method="post" class="card-foot"><input type="hidden" name="courseId" value="<%=c.getId()%>"><span class="muted small-text">Self-paced learning</span><button class="btn btn-small btn-primary" type="submit">Enroll →</button></form><% } %>
</div></div>
<% } if(courses.isEmpty()){ %><div class="empty-state"><h3>No courses yet</h3><p>Ask your admin to add the first course.</p></div><% } %>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
