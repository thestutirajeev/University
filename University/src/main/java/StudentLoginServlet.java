import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class StudentLoginServlet
 */
public class StudentLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regId = request.getParameter("reg_id");
        String password = request.getParameter("password"); 
        String message = "";
        boolean isAuthenticated = false;  // Variable to track if authentication is successful
        AccessDatabase accessDatabase = null;
        try {
        	accessDatabase = AccessDatabase.getInstance();
        }catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	return;
		}
        //Fetch Student Name - S_name
        String S_name = null;
        S_name = accessDatabase.getStudentForLogin(regId, password);
        if(S_name != null) {
        	// Set session attribute
            HttpSession session = request.getSession();
            session.setAttribute("studentName", S_name);  // Store student name in session
            isAuthenticated = true;
        }
        else {
            message = "Invalid Credentials. Please try again.";
        }
     
        // Redirect based on authentication status
        if (isAuthenticated) {
            response.sendRedirect("student/dashboardStudent.jsp");
        } else {
            response.sendRedirect("home.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        }
    }
}
