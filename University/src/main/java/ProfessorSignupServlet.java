import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;

import POJO.Professor;

/**
 * Servlet implementation class ProfessorSignupServlet
 */
public class ProfessorSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a new Professor object
		Professor professor = new Professor();
		// Set the requested values to the professor object
		professor.setProfId(request.getParameter("prof_id"));
		professor.setProfName(request.getParameter("prof_name"));
		professor.setEmail(request.getParameter("email"));
		professor.setMobile(request.getParameter("mobile"));
		professor.setDeptId(request.getParameter("dept_id"));
		professor.setCrsId(request.getParameter("crs_id"));
		professor.setPassword(request.getParameter("password"));
		String message = "";
		AccessDatabase accessDatabase = null;
        try {
        	accessDatabase = AccessDatabase.getInstance();
        }catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	return;
		}
        boolean inserted = accessDatabase.insertProfessor(professor);   
        if(inserted) {
        	message = "Professor registered successfully!";
        }else {
        	message = "Registration failed. Please try again.";
        }
		response.sendRedirect("home.jsp?message="+URLEncoder.encode(message, "UTF-8"));
	}
}
