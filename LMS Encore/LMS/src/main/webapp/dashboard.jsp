<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.smartlms.model.*" %>
<% request.setAttribute("pageTitle","Overview"); %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="welcome-row"><div><span class="pill">YOUR OVERVIEW</span><h1>Make today count.</h1><p class="muted"><%= currentUser.isAdmin() ? "Here’s a quick look at how your learning space is doing." : "A little progress each day adds up to big results." %></p></div>
<div class="date-chip"><span>✦</span> One step at a time</div></div>
<% if(request.getParameter("enrolled")!=null){ %><div class="alert alert-success">You're enrolled. The course is now part of your learning list.</div><% } %>
<div class="stats-grid">
<div class="stat-card"><div class="stat-top"><span class="stat-icon blue-icon">▤</span><span class="stat-label">COURSES</span></div><div class="stat-number">${courseCount}</div><div class="stat-foot">Available to explore</div></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon purple-icon">✓</span><span class="stat-label">ASSIGNMENTS</span></div><div class="stat-number">${assignmentCount}</div><div class="stat-foot">Across all courses</div></div>
<% if(currentUser.isAdmin()){ %><div class="stat-card"><div class="stat-top"><span class="stat-icon green-icon">♙</span><span class="stat-label">STUDENTS</span></div><div class="stat-number">${studentCount}</div><div class="stat-foot">Registered learners</div></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon orange-icon">↗</span><span class="stat-label">ENROLLMENTS</span></div><div class="stat-number">${enrollmentCount}</div><div class="stat-foot">Course sign-ups</div></div>
<% } else { %><div class="stat-card"><div class="stat-top"><span class="stat-icon green-icon">✦</span><span class="stat-label">MY COURSES</span></div><div class="stat-number"><%= ((List<Course>)request.getAttribute("courseList")).stream().filter(Course::isEnrolled).count() %></div><div class="stat-foot">Courses you're taking</div></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon orange-icon">◷</span><span class="stat-label">TO DO</span></div><div class="stat-number"><%= ((List<Assignment>)request.getAttribute("assignmentList")).stream().filter(a->!a.isSubmitted()).count() %></div><div class="stat-foot">Assignments to submit</div></div><% } %>
</div>
<% if(currentUser.isAdmin()){ %>
<div class="section-heading"><div><h2>Manage your learning space</h2><p class="muted">Small updates make a big difference for students.</p></div></div>
<div class="quick-grid"><a class="quick-card" href="<%=ctx%>/courses"><span class="quick-icon">＋</span><div><strong>Add a course</strong><p>Create a new course for your students.</p></div><span class="arrow">→</span></a>
<a class="quick-card" href="<%=ctx%>/assignments"><span class="quick-icon quick-purple">✓</span><div><strong>Post an assignment</strong><p>Share the next task and due date.</p></div><span class="arrow">→</span></a></div>
<% } else { List<Course> list=(List<Course>)request.getAttribute("courseList"); %>
<div class="section-heading"><div><h2>Your courses</h2><p class="muted">Jump back into something you're learning.</p></div><a class="text-link" href="<%=ctx%>/courses">Browse courses →</a></div>
<div class="course-grid">
<% int shown=0; for(Course c:list){ if(!c.isEnrolled()) continue; shown++; %>
<div class="course-card"><div class="course-cover cover-<%=shown%4%>"><span>COURSE <%=String.format("%02d",shown)%></span><span class="cover-symbol">✳</span></div><div class="course-card-body"><span class="course-kicker"><%=c.getInstructor()%></span><h3><%=c.getTitle()%></h3><p><%=c.getDescription()%></p><a class="text-link" href="<%=ctx%>/assignments">View assignments →</a></div></div>
<% } if(shown==0){ %><div class="empty-state"><div class="empty-icon">▤</div><h3>Your learning journey starts here</h3><p>Browse available courses and enroll in one to get started.</p><a class="btn btn-primary" href="<%=ctx%>/courses">Explore courses →</a></div><% } %>
</div><% } %>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
