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

@WebServlet("/feed")
public class JanesGetFeedItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get promotion info from database by requesting user's email
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<promotion> promotions = new ArrayList<promotion>();
			ResultSet rs = null;
			//get email from user input and check if users table contain that user
			String offset = request.getParameter("offset");
			String limit = request.getParameter("limit");
			String types = request.getParameter("types");
			String locations = request.getParameter("locations");
			String time2 = request.getParameter("time");
			boolean history = Boolean.parseBoolean(request.getParameter("history"));
			
			if(!history){
				//get current promotion data
				ps = con.prepareStatement("SELECT * FROM promotions where location =? AND time =? LIMIT ? OFFSET ?"); 
			}else{
				//get history data
				ps = con.prepareStatement("SELECT * FROM promotion_history where location =? AND time=? LIMIT ? OFFSET ?"); 
			}
			ps.setString(1,locations); 
			ps.setString(2,time2); 
			ps.setString(3,limit); 
			ps.setString(4,offset); 
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