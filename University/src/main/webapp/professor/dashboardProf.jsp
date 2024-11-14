<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Professor Dashboard</title>
<script type="text/javascript">
	//This script will display the message sent from the servlet if it exists
	document.addEventListener("DOMContentLoaded", function() {
	    const urlParams = new URLSearchParams(window.location.search);
	    const message = urlParams.get('message');
	    document.getElementById("message").textContent = message;;
	});
</script>
<%
	session = request.getSession(false); //fetch session without creation new one
	if(session == null || session.getAttribute("professorName")  == null){
		response.sendRedirect(request.getContextPath() + "/home.jsp?message=" + java.net.URLEncoder.encode("Login Required!", "UTF-8"));
		return;
	}
	String professorName = session.getAttribute("professorName").toString();
%>
</head>
<body>
	<!-- Menu Bar -->
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath}/ProfessorProfileServlet">Profile</a></li>
			<li><a href ="">Logout</a></li>
		</ul>
	</nav>
	    <p>Hi <strong><%=professorName %></strong>!</p>
</body>
</html>