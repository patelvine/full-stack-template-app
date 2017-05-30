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
import objects.saleGraph;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * Servlet implementation class UsersAPI
 * 
 * <p>get sales info from database and send data to the front-end
 */

@WebServlet("/getSales")
public class getSales extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get sales info from database by requesting user's email
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<saleGraph> graph = new ArrayList<saleGraph>();
			ArrayList<Integer> y = new ArrayList<Integer>();
			ArrayList<Double> x = new ArrayList<Double>();
			ResultSet rs = null;
			//get email from user input and check if users table contain that user
			String email = request.getParameter("email");
	
			//get sales data from DB
			ps = con.prepareStatement("SELECT * FROM sales where email =? order by time asc"); 
			ps.setString(1,email); 
			rs = ps.executeQuery();
			while ( rs.next() ) {
				int num_sales = rs.getInt("num_of_sales");
				String temp_time = new String(rs.getString("time"));
				Double time = Double.parseDouble(temp_time.replace(":", ".").substring(0,5));
				
				y.add(num_sales);
				x.add(time);
			}
			
			saleGraph g = new saleGraph(x, y);
			graph.add(g);
			
			String json = new Gson().toJson(graph);
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
