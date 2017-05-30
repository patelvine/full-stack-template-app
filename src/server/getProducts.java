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
import objects.product;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * Servlet implementation class UsersAPI
 * 
 * <p>get products info from database base on input email and send data to the front-end
 */
@WebServlet("/getProducts")
public class getProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get product info from database by requesting user's email
	 * @param request - request for user email from web page
	 * @param response - response with all info that belong to the requested email
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<product> products = new ArrayList<product>();
			
			//get email from user input and check if users table contain that user
			String email = request.getParameter("email");
			String pro="";
			
			//check if input email already exists
			ps = con.prepareStatement("SELECT * FROM product_list where email =? "); 
			ps.setString(1,email); 
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				pro = rs.getString("product");
				product product = new product(pro);
				products.add(product);
			}
			
			String json = new Gson().toJson(products);
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
