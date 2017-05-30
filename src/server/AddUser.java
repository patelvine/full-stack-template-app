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
 * Servlet implementation class AddUser
 * 
 * <p>get users data from front-end and insert them into database also update company
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * get user data from web page and store it into database 
	 * @param request - request for user input from web page
	 * @param response - response with true if successful stored user else response with false
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
			String password = request.getParameter("pass");
			String phone = request.getParameter("phone");
			String contactP = request.getParameter("contactP");
			String contactT = request.getParameter("contactT");
			String position = request.getParameter("position");
			String industry = request.getParameter("industry");
			String primaryLocation = request.getParameter("primaryLoc");
			int numOfLocation = Integer.parseInt(request.getParameter("numOfLoc"));
			int numOfEmployee = Integer.parseInt(request.getParameter("numOfEmp"));
			 
			if(checker =="false" && email!=null ){
				//add user to database
				if( username!=null && password!=null ){
					ps = con.prepareStatement("INSERT INTO users (email,password,username,phone,\"Contact Person\","
							+ "\"Contact Time\",position,industry,\"primary location\",\"number of locations\","
							+ "\"number of employees\") VALUES(?,?,?,?,?,cast (? as time),?,?,?,?,?) "
							+ "RETURNING email,password,username,phone,\"Contact Person\","
							+ "\"Contact Time\",position,industry,\"primary location\",\"number of locations\","
							+ "\"number of employees\""); 
					
					ps.setString(1,email); 
		            ps.setString(2,password); 
		            ps.setString(3,username);
		            ps.setString(4,phone);
		            ps.setString(5,contactP);
		            ps.setString(6,contactT);
		            ps.setString(7,position);
		            ps.setString(8,industry);
		            ps.setString(9,primaryLocation);
		            ps.setInt(10,numOfLocation);
		            ps.setInt(11,numOfEmployee);
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
