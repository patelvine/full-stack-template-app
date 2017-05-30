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
 * <p>get products from front-end and insert them into database
 */
@WebServlet("/addProducts")
public class addProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * get products info from web page and add into product_list table
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
			String[] products  = request.getParameterValues("products[]");
			
			if(checker =="false" && email!=null ){
				//add product to product_list table
				if( products!=null){
					for(int i =0; i<products.length; i++){
						ps = con.prepareStatement("INSERT into product_list VALUES (?,?) "
								+ "returning email,product"); 
						
						ps.setString(1,email); 
			            ps.setString(2,products[i]);
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
