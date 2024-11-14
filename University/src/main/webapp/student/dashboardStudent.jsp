<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<script type="text/javascript">
	//This script will display the message sent from the servlet if it exists
	document.addEventListener("DOMContentLoaded", function() {
	    const urlParams = new URLSearchParams(window.location.search);
	    const message = urlParams.get('message');
	    document.getElementById("message").textContent = message;;
	});
</script>
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("studentName") == null) {        
    	response.sendRedirect(request.getContextPath() + "/home.jsp?message=" + java.net.URLEncoder.encode("Please log in first!", "UTF-8"));
        return;
    }
    String studentName = session.getAttribute("studentName").toString();
%>

</head>
<body>
		<p>Hi, <strong><%= studentName %></strong>!</p>
		    
		 <!-- Logout Button 
	    <form action="${pageContext.request.contextPath}/LogoutServlet" method="get">
	        <button type="submit">Logout</button>
	    </form>-->
</body>
</html>