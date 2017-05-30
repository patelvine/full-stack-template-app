package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DBconnector;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * Servlet implementation class UsersAPI
 * 
 * <p>get users data from database base on input email and send data to the front-end
 */
@WebServlet("/getUser")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * get user from database by requesting user's email
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			Map<String,Object> user = new HashMap<String,Object>();
			
			//get email from user input and check if users table contain that user
			String email = request.getParameter("email");
			String DBemail = "undefined";
			String username ="undefined";
			String password = "undefined";
			String  phone = "undefined";
			String contactP ="undefined";
			Time contactT = null;
			String position = "undefined";
			String industry ="undefined";
			String primaryLoc = "undefined";
			int numOfLocation = 0;
			int numOfEmp = 0;

			//check if input email already exists
			ps = con.prepareStatement("SELECT * FROM users where email =? "); 
			ps.setString(1,email); 
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				DBemail = rs.getString("email");
				username = rs.getString("username");
				password = rs.getString("password");
				phone = rs.getString("phone");
				contactP = rs.getString("Contact Person");
				contactT = rs.getTime("Contact Time");
				position = rs.getString("position");
				industry = rs.getString("industry");
				primaryLoc = rs.getString("primary location");
				numOfLocation = rs.getInt("number of locations");
				numOfEmp = rs.getInt("number of employees");
				
				user.put("email", DBemail);
				user.put("name", username);
				user.put("pass", password);
				user.put("phone", phone);
				user.put("contactP", contactP);
				user.put("contactT", contactT);
				user.put("position", position);
				user.put("industry", industry);
				user.put("primaryLoc", primaryLoc);
				user.put("numOfLocation", numOfLocation);
				user.put("numOfEmp", numOfEmp);
				
			}
			
			String json = new Gson().toJson(user);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
			rs.close();
	        con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
	}

}
