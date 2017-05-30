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
 * <p>removed product info from front end and update user table
 */
@WebServlet("/removeProducts")
public class removeProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get product list from front-end and remove products from product_list table 
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
			String[] products  = request.getParameterValues("prodList[]");
			 
			if(checker =="false" && email!=null ){
				//add location to store_location table
				if( products!=null){
					for(int i =0; i<products.length; i++){
						ps = con.prepareStatement("delete from product_list where product=?"
								+ " returning product"); 
						
			            ps.setString(1,products[i]);
			            ps.executeQuery();
					}
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
