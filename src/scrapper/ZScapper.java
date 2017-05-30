package scrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import database.DBconnector;
import objects.Fuel;
import objects.Place;
import objects.stores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 *
 */

public class ZScapper {
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private String url = "http://z.co.nz/";
	private DBconnector con = new DBconnector();

	/**
	 * Method to return Fuel prices for the current day
	 * 
	 * @return List of Fuel object
	 */
	public List<Fuel> getZFuelPrices() {
		try {
			List<Fuel> result = new ArrayList<Fuel>();
			org.jsoup.Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();

			Elements fuelPrices = htmlDocument.getElementsByClass("footer__copy");
			for (Element fuel : fuelPrices) {
				String price = "";
				String unit = "";
				String type = "";
				String date = "";
				for (Element number : fuel.select("span")) {
					if (number.text().matches("[0-9]+") || number.text().equals(".")) {
						price += number.text();
					} else {
						unit += number.text();
					}
				}
				type += fuel.select("p").text();

				LocalDate localDate = LocalDate.now();
				date = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate).toString();

				result.add(new Fuel(date, Float.parseFloat(price), type, unit));
			}
			postToDB(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Fuel getFromDB(Fuel fuel) { // change this to return a certain
										// object. method will be flexible
										// rather than hard coded to the zfuek
										// class
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			String date = null;
			String type = null;
			Float price = (float) 0;
			String unit = null;
			 	
			ps = con.prepareStatement("select * from zfuel where date = ? and type = ?");
			ps.setString(1, fuel.getDate());  
			ps.setString(2, fuel.getType());
			ResultSet rs = ps.executeQuery();
  
			while (rs.next()) {
				date = rs.getString("date");
				type = rs.getString("type");
				price = rs.getFloat("price");
				unit = rs.getString("unit");
			}
			if (date != null && type != null) {
				Fuel result = new Fuel(date, price, type, unit);
				rs.close();
				con.close();
				return result;
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean postToDB(List<Fuel> fuelDetails) { // create an equals method to compare both input and output within the DB
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;

			for (Fuel fuel : fuelDetails) {
				if (getFromDB(fuel) == null || !getFromDB(fuel).equals(fuel)) {
					ps = con.prepareStatement(
							"INSERT INTO zfuel (date,price,type,unit) VALUES(?,?,?,?) RETURNING date,price,type,unit;");
					ps.setString(1, fuel.getDate());
					ps.setFloat(2, fuel.getPrice());
					ps.setString(3, fuel.getType());
					ps.setString(4, fuel.getUnit());
					ps.executeQuery();
				}
			}

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