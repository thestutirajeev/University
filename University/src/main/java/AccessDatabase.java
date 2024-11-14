
import java.sql.*;

import POJO.Professor;
import POJO.Student;

public class AccessDatabase {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "123";

    // Singleton instance
    private static AccessDatabase instance = null;
    private Connection connection;

    // Private constructor to prevent instantiation from outside
    private AccessDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }

    // Public static method to get the single instance
    public static AccessDatabase getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (AccessDatabase.class) {
                if (instance == null) {
                    instance = new AccessDatabase();
                }
            }
        }
        return instance;
    }
//    // Get user list by starting letter
//    public List<User> getUsersByStartingLetter(String startingLetter) throws SQLException {
//        List<User> userList = new ArrayList<>();
//        String query = "SELECT * FROM users WHERE uname LIKE ?";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setString(1, startingLetter + "%");
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String username = rs.getString("uname");
//                userList.add(new User(id, username));
//            }
//        }
//        return userList;
//    }

	public String getStudentForLogin(String regId, String password) {
		// Prepare the SQL statement to prevent SQL injection
        String sql = "SELECT S_name FROM STUDENT WHERE Reg_id = ? AND Password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
        	pstmt.setString(1, regId);
            pstmt.setString(2, password);  // Password should ideally be hashed and compared
            ResultSet rs = pstmt.executeQuery();
            // Handle successful login
            if (rs.next()) {
                return rs.getString("S_name");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getProfessorForLogin(String prof_id, String password) {
		// Prepare the SQL statement to prevent SQL injection
				String sql = "SELECT Prof_name FROM PROFESSOR WHERE Prof_id = ? AND Password = ?";
		        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
		        	pstmt.setString(1, prof_id);
		            pstmt.setString(2, password);  // Password should ideally be hashed and compared
		            ResultSet rs = pstmt.executeQuery();
		            // Handle successful login
		            if (rs.next()) {
		                return rs.getString("Prof_name");
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}

	public boolean insertProfessor(Professor professor) {
		String sql = "INSERT INTO PROFESSOR (Prof_id, Prof_name, Email, Mobile, Dept_id, Crs_id, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //Prepare the Statement
		try (PreparedStatement pstmt = connection.prepareStatement(sql)){		
	        pstmt.setString(1, professor.getProfId());
	        pstmt.setString(2, professor.getProfName());
	        pstmt.setString(3, professor.getEmail());
	        pstmt.setString(4, professor.getMobile());
	        pstmt.setString(5, professor.getDeptId());
	        pstmt.setString(6, professor.getCrsId());
	        pstmt.setString(7, professor.getPassword());
	        
        int rows = pstmt.executeUpdate();
        
        if(rows > 0) {
        	return true;
        }
		return false;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertStudent(Student student) {
    	// SQL insert query
        String sql = "INSERT INTO STUDENT (Reg_id, S_name, Address, DoB, Email, Mobile, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
	        stmt.setString(1, student.getRegId());
	        stmt.setString(2, student.getName());
	        stmt.setString(3, student.getAddress());
	        stmt.setDate(4, student.getDob()); // Convert string to date
	        stmt.setString(5, student.getEmail());
	        stmt.setString(6, student.getMobile());
	        stmt.setString(7, student.getPassword());
	        int rows = stmt.executeUpdate();
        if(rows > 0) {
        	return true;
        }
		return false;
		}catch (Exception e) {
			e.printStackTrace();
		}
        
		return false;
	}

}
