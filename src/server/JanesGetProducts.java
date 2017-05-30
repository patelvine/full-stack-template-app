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

@WebServlet("/products")
public class JanesGetProducts  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<product> products = new ArrayList<product>();

			// get email from user input and check if users table contain that
			// user
			String matching = request.getParameter("matching");

			// check if input email already exists
			ps = con.prepareStatement("SELECT * FROM product_list where product =? ");
			ps.setString(1, matching);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product lc = new product(rs.getString("product"));
				products.add(lc);
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