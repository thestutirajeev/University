//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
///**
// * Servlet implementation class LogoutServlet
// */
//public class LogoutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Invalidate the session
//        HttpSession session = request.getSession(false); // Fetch session without creating a new one
//        if(session != null) {
//        	session.invalidate(); //ends session;
//        }
//        response.sendRedirect("home.jsp?message=" + java.net.URLEncoder.encode("Log out Successfull!", "UTF-8"));
//	}
//}
