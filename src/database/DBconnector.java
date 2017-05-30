package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jason Chen <difuchen@gmail.com>
 *
 *         <p>
 *         A method call to connect Postgres database with java
 */
public class DBconnector {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;

		try {
			Class.forName("org.postgresql.Driver");
			String host = "ec2-54-243-48-178.compute-1.amazonaws.com";
			int port = 5432;
			String username = "pdgpubhccdflji";
			String password = "S3WOWaprM_X6rUQ1ntTIx4MDQc";
			String dbName = "/d9ldtuu7o5tam2?sslmode=require&";
			String dbUrl = "jdbc:postgresql://" + host + ':' + port + dbName;

			conn = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("Connected to the PostgreSQL server successfully.\n");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;

	}

	public boolean doPost(HashMap<String, String> map, String table) throws ServletException, IOException {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			String insertInto = "INSERT INTO "+table+"(";
			String values = " VALUES(";
			String returning = " RETURNING ";
			
			//ps = con.prepareStatement("INSERT INTO places (place_id,lon,lat,name,rating,website,formatted_address,url,vicinity) VALUES(?,?,?,?,?,?,?,?,?)"
			//		+ "RETURNING place_id,lon,lat,name,rating,website,formatted_address,url,vicinity;"); 
			
			Iterator<Entry<String, String>> entries = map.entrySet().iterator();
			while (entries.hasNext()) {
			    Entry<String, String> entry = entries.next();
			    
			    insertInto+=entry.getKey();
			    values+="?";
			    returning+=entry.getKey();
			    
			    if(entries.hasNext()){
			    	insertInto += ",";
			    	values += ",";
			    	returning+= ",";
			    	}else{
			    	insertInto += ")";
			    	values += ")";
			    }
			}
			
			ps = con.prepareStatement(insertInto+values+returning+";");
			int i = 0;
			while (entries.hasNext()) {
				i++;
			    Entry<String, String> entry = entries.next();
			    ps.setString(i,entry.getValue()); 
			}
			
			ps.executeQuery();
			con.close();
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
