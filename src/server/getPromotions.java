package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DBconnector;
import objects.promotion;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * Servlet implementation class UsersAPI
 * 
 * <p>get promotion info from database and send data to the front-end
 */
@WebServlet("/getPromotions")
public class getPromotions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get promotion info from database by requesting user's email
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<promotion> promotions = new ArrayList<promotion>();
			ResultSet rs = null;
			//get email from user input and check if users table contain that user
			String email = request.getParameter("email");
			boolean history = Boolean.parseBoolean(request.getParameter("history"));
			
			if(!history){
				//get current promotion data
				ps = con.prepareStatement("SELECT * FROM promotions where email =? "); 
			}else{
				//get history data
				ps = con.prepareStatement("SELECT * FROM promotion_history where email =? "); 
			}
			ps.setString(1,email); 
			rs = ps.executeQuery();
			while ( rs.next() ) {
				String name = rs.getString("name");
				String price = rs.getString("price");
				String time = rs.getString("time");
				String location = rs.getString("location");
				int score = rs.getInt("score");
				
				promotion p= new promotion("Name: "+name, "Price: "+price, "Time: "+time, "Location: "+location, score);
				promotions.add(p);
			}
			
			String json = new Gson().toJson(promotions);
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
