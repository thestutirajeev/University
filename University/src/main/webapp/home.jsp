<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Connection, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login / Signup</title>
    <script>
        // Show and hide forms based on user selection
        function toggleForm() {
            var isSignUp = document.getElementById("signup").checked;
            var isStudent = document.getElementById("student").checked;
            var isProfessor = document.getElementById("professor").checked;
			
         // Hide all forms initially 
         	document.getElementById("message").style.display = "none";
            document.getElementById("studentLoginForm").style.display = "none";
            document.getElementById("studentSignupForm").style.display = "none";
            document.getElementById("professorLoginForm").style.display = "none";
            document.getElementById("professorSignupForm").style.display = "none";
            
            if(isStudent && isSignUp){
            	document.getElementById("studentSignupForm").style.display = "block";
            }else if(isStudent && !isSignUp){
            	document.getElementById("studentLoginForm").style.display = "block";
            }
            else if(isProfessor && isSignUp){
            	document.getElementById("professorSignupForm").style.display = "block";
            }else{
            	document.getElementById("professorLoginForm").style.display = "block";
            }
        }
     	// This script will display the message sent from the servlet if it exists
        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("studentLoginForm").style.display = "block";
            const urlParams = new URLSearchParams(window.location.search);
            const message = urlParams.get('message');
            
            if (message) {
                const messageElement = document.getElementById("message");
                messageElement.textContent = message;
                messageElement.style.display = "block";  // Make the message visible
            }
        });
    </script>
</head>
<body>

    <h1>Login / Signup Page</h1>

    <!-- Login / Signup Checkbox -->
    <label>
        <input type="radio" id="login" name="action" value="login" checked onchange="toggleForm()"> Login
    </label>
    <label>
        <input type="radio" id="signup" name="action" value="signup" onchange="toggleForm()"> Sign Up
    </label>
    <br><br>

    <!-- Student / Professor Checkbox -->
    <label>
        <input type="radio" id="student" name="role" value="student" checked onchange="toggleForm()"> Student
    </label>
    <label>
        <input type="radio" id="professor" name="role" value="professor" onchange="toggleForm()"> Professor
    </label>

    <br><br>
    <!-- Student Login Form (Hidden by Default) -->
    <form id="studentLoginForm" action="StudentLoginServlet" method="post" style="display: none;">
        <h2>Student Login Form</h2>
        <label for="reg_id">Reg ID:</label>
        <input type="text" id="reg_id" name="reg_id" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <button type="submit">Login</button>
    </form>
    
    <!-- Student Signup Form (Hidden by Default) -->
    <form id="studentSignupForm" action="StudentSignupServlet" method="post" style="display: none;">
        <h2>Student <span class="form-type">Login</span> Form</h2>
        <label for="reg_id">Reg ID:</label>
        <input type="text" id="reg_id" name="reg_id" required><br><br>

        <label for="s_name">Name:</label>
        <input type="text" id="s_name" name="s_name" required><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br><br>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="mobile">Mobile:</label>
        <input type="tel" id="mobile" name="mobile" pattern="\d{10}" required title="Mobile number must be 10 digits"><br><br>
    
        <label for="password">Create Password:</label>
        <input type="password" id="password" name="password" pattern=".{8}" required title="Password must be 8 characters long"><br><br>
        
        <button type="submit"><span class="form-type">Login</span></button>
    </form>

	<!-- Professor Login Form (Hidden by Default) -->
    <form id="professorLoginForm" action="ProfessorLoginServlet" method="post" style="display: none;">
        <h2>Professor Login Form</h2>
        <label for="prof_id">Professor ID:</label>
        <input type="text" id="prof_id" name="prof_id" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
    
    <!-- Professor Form (Hidden by Default) -->
    <form id="professorSignupForm" action="ProfessorSignupServlet" method="post" style="display: none;">
        <h2>Professor Sign up Form</h2>
        <label for="prof_id">Professor ID:</label>
        <input type="text" id="prof_id" name="prof_id" required><br><br>

        <label for="prof_name">Name:</label>
        <input type="text" id="prof_name" name="prof_name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="mobile">Mobile:</label>
        <input type="tel" id="mobile" name="mobile" pattern="\d{10}" required title="Mobile number must be 10 digits"><br><br>

        <label for="dept_id">Department:</label>
		<select id = "dept_id" name = "dept_id">
        	<option value = "">Select Department</option>
			<%
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
					String sql = "SELECT Dept_id, Dname FROM DEPARTMENT";
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()) {
	                    String dept_id = rs.getString("Dept_id");
	                    String Dname = rs.getString("Dname");
	                    out.println("<option value='" + dept_id + "'>" + Dname + "</option>");
	                }
	                conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } 
			 %>
        </select><br><br>

        <label for="crs_id">Course:</label>
        <select id = "crs_id" name = "crs_id">
        	<option value = "">Select Course</option>
			<%
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
					String sql = "SELECT Crs_id, Crs_name FROM COURSE";
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()) {
	                    String crs_id = rs.getString("Crs_id");
	                    String crs_name = rs.getString("Crs_name");
	                    out.println("<option value='" + crs_id + "'>" + crs_name + "</option>");
	                }
	                conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } 
			 %>
        </select><br><br>

        <label for="password">Create Password:</label>
        <input type="password" id="password" name="password" pattern=".{8}" required title="Password must be 8 characters long"><br><br>

        <button type="submit">Sign up</button>
    </form>
    <p id="message" style = "display: none;"></p>

</body>
</html>
