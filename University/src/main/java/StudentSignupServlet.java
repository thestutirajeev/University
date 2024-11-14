import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import POJO.Student;

/**
 * Servlet implementation class StudentSignupServlet
 */
public class StudentSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a new Student object
		Student student = new Student();
		// Set the requested values to the student object
		student.setRegId(request.getParameter("reg_id"));
		student.setName(request.getParameter("s_name"));
		student.setAddress(request.getParameter("address"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Same format pattern
		java.util.Date utilDate = null;
		try {
			utilDate = sdf.parse(request.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Parse String to util.Date
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());  // Convert util.Date to sql.Date
		
		student.setDob(sqlDate);
		student.setEmail(request.getParameter("email"));
		student.setMobile(request.getParameter("mobile"));
		student.setPassword(request.getParameter("password"));

		String message = "";
		AccessDatabase accessDatabase = null;
        try {
        	accessDatabase = AccessDatabase.getInstance();
        }catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	return;
		}
        boolean inserted = accessDatabase.insertStudent(student);
        // Handle successful signup
        if (inserted) {
            message = "Student registered successfully!";
        } else {
            message = "Registration failed. Please try again.";
        }
        response.sendRedirect("home.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
 	}

}
