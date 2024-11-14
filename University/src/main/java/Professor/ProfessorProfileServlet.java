package Professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessorProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false); // Fetch session without creating a new one
	    if (session == null || session.getAttribute("Prof_id") == null) {
	        response.sendRedirect("home.jsp?message=" + java.net.URLEncoder.encode("Login required!", "UTF-8"));
	        return;
	    }

	    String Prof_id = session.getAttribute("Prof_id").toString();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        String sql = "SELECT Prof_name, email, mobile, dname, crs_name, password FROM PROFESSOR NATURAL JOIN COURSE NATURAL JOIN DEPARTMENT WHERE Prof_id LIKE ?";
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, Prof_id);
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            request.setAttribute("Prof_name", rs.getString("Prof_name"));
	            request.setAttribute("email", rs.getString("email"));
	            request.setAttribute("mobile", rs.getString("mobile"));
	            request.setAttribute("dname", rs.getString("dname"));
	            request.setAttribute("crs_name", rs.getString("crs_name"));
	            request.setAttribute("password", rs.getString("password"));
	        }
	        request.getRequestDispatcher("/professor/profileProf.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
