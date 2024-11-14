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

public class ProfessorLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Prof_id = request.getParameter("prof_id").trim();
        String password = request.getParameter("password").trim();
        String message = "";
        boolean isAuthenticated = false;
        AccessDatabase accessDatabase = null;
        try {
        	accessDatabase = AccessDatabase.getInstance();
        }catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	return;
		}
        //Fetch Professor Name - Prof_name
        String Prof_name = null;
        Prof_name = accessDatabase.getProfessorForLogin(Prof_id, password);
        // Handle successful login
            if (Prof_name != null) {
            	//Set Session
                HttpSession session = request.getSession();
                session.setAttribute("professorName", Prof_name);// Store professor name in session
                session.setAttribute("Prof_id", Prof_id);// Store professor id in session
                isAuthenticated = true;
            } else {
                message = "Invalid Credentials. Please try again.";
            }
        // Redirect based on authentication status
        if (isAuthenticated) {
            response.sendRedirect("professor/dashboardProf.jsp");                
        } else {
            response.sendRedirect("home.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        }
    }
}
