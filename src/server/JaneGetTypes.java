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
import objects.location;

@WebServlet("/types")
public class JaneGetTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ArrayList<String> types = new ArrayList<String>();

			// get email from user input and check if users table contain that
			// user

			// check if input email already exists
			ps = con.prepareStatement("SELECT DISTINCT type FROM types");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String type = rs.getString("type");
				types.add(type);
			}

			String json = new Gson().toJson(types);
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
