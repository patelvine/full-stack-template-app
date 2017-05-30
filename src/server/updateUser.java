package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBconnector;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * Servlet implementation class UsersAPI
 * 
 * <p>get users data from front end and update user table
 */
@WebServlet("/updateUser")
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get users info from front-end and update users table in database
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			String checker = "false";
			
			//get values from user input
			String email = request.getParameter("email");
			String username = request.getParameter("name");
			String phone = request.getParameter("phone");
			String position = request.getParameter("position");
			String industry = request.getParameter("industry");
			String primaryLocation = request.getParameter("primaryLoc");
			String new_password = request.getParameter("new_pass");
			 
			if(checker =="false" && email!=null ){
				//update user table
				if( email!=null && username!=null && phone!=null && position!=null && industry!=null && primaryLocation!=null && new_password==null ){
					ps = con.prepareStatement("update users set username=?, phone=?, position=?, "
							+ "industry=?, \"primary location\"=? where email =? "
							+ "returning username,phone,position,industry,\"primary location\",email"); 
		            ps.setString(1,username);
		            ps.setString(2,phone);
		            ps.setString(3,position);
		            ps.setString(4,industry);
		            ps.setString(5,primaryLocation);
		            ps.setString(6,email); 
		            ps.executeQuery();
		            
				}
				else if(new_password!=null){
					ps = con.prepareStatement("update users set password=? where email =? "
							+ "returning password,email"); 
		            ps.setString(1,new_password);
		            ps.setString(2,email);
		            ps.executeQuery();
				}
				checker="true";
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(checker);

	        con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
