package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import API.OpenWeatherMapApi;
import database.DBconnector;
import objects.City;
import objects.Forecast;
import objects.Weather;


public class AddForecast{
	
	public boolean AddForecastToDB(JsonObject obj) throws ServletException, IOException {
		Forecast f = extractInfoForecast(obj);
		
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			/*Adding the City dat into the DB*/
				if( f.getCity() != null){
					//add the city info to db
					ps = con.prepareStatement("INSERT INTO city (cityid, cityname, country, lat, lon)"
							+ "VALUES(?,?,?,?,? ) RETURNING cityid, cityname, country, lat, lon;"); 
					 
					ps.setInt(1,f.getCity().getId()); 
		            ps.setString(2,f.getCity().getCity()); 
		            ps.setString(3,f.getCity().getCountryCode()); 
		            ps.setFloat(4,f.getCity().getLat());
		            ps.setFloat(5,f.getCity().getLon()); 
		            ps.executeQuery();
				}
				if( f.getForecast() != null){
					//add the city info to db
					
					for(Weather w: f.getForecast()){
						ps = con.prepareStatement("INSERT INTO Weather (cityid, UTCDate, main, description, temp, pressure, humidity, windspeed, winddeg, clouds, rain, snow)"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) RETURNING cityid, UTCDate, main, description, temp;"); 
						 
						ps.setInt(1,f.getCity().getId()); 
			            ps.setInt(2,w.getUTCdate()); 
			            ps.setString(3,w.getMain()); 
			            ps.setString(4,w.getDeceiption());
			            ps.setFloat(5,w.getTemp()); 
			            ps.setFloat(6,w.getPressure()); 
			            ps.setFloat(7,w.getHumidity()); 
			            ps.setFloat(8,w.getWindspeed()); 
			            ps.setFloat(9,w.getWindDeg()); 
			            ps.setFloat(10,w.getClouds()); 
			            ps.setFloat(11,w.getRain());
			            ps.setFloat(12,w.getSnow()); 
			            ps.executeQuery();
					}
					
				}
		
	        con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * Given the json object, return an arraylist of forecast.
	 * 
	 * @param obj is a Json Object containing 5 day weather data
	 * @return a list containing important weather information.
	 */
	private Forecast extractInfoForecast(JsonObject obj) {
		ArrayList<Weather> list = new ArrayList<Weather>();
		//dividing the json object into two. One containing location 
		//info and other weather data
		JsonObject city = obj.getAsJsonObject("city");
		JsonArray wList = obj.getAsJsonArray("list");
		
		//Variable to store location data
		String cName = city.get("name").getAsString();
		String cCode = city.get("country").getAsString();	
		int cID = city.get("id").getAsInt();
		float lat = city.get("coord").getAsJsonObject().get("lat").getAsFloat();
		float lon = city.get("coord").getAsJsonObject().get("lon").getAsFloat();
		City cityinfo = new City(cName, cCode, cID, lat, lon);
		
		/*Iterating through jsonArray to retrieve weather data */
		for(int i = 0; i< wList.size(); i++){
			JsonObject o = (JsonObject) wList.get(i);
			
			/*Overall Weather description*/
			JsonArray weather = o.getAsJsonArray("weather");
			String wMain = ((JsonObject)weather.get(0)).get("main").getAsString();
			String wDescription = ((JsonObject)weather.get(0)).get("description").getAsString();
			
			/*Weather detail*/
			JsonObject main = (JsonObject) o.get("main");
			float temp = main.get("temp").getAsFloat();
			float pressure = main.get("pressure").getAsFloat();
			float humidity = main.get("humidity").getAsFloat();
			float windSpeed = ((JsonObject) o.get("wind")).get("speed").getAsFloat();
			float windDeg = ((JsonObject) o.get("wind")).get("deg").getAsFloat();
			float clouds = ((JsonObject) o.get("clouds")).get("all").getAsFloat();
			int date = o.get("dt").getAsInt();
			float rain = 0;
			float snow = 0;
			
			if(o.has("rain")){//checking if rain data is not null
				if(!o.getAsJsonObject("rain").has("3h")){//sometime value is missing (null pointer exception)
					rain = 0.00f;
				}else{
					rain = ((JsonObject) o.get("rain")).get("3h").getAsFloat();
				}			
			}
			if(o.has("snow")){//checking if Snow data is not null
				if(!o.getAsJsonObject("snow").has("3h")){//sometime value is missing (null pointer exception)
					snow = 0.00f;
				}else{
					snow = ((JsonObject) o.get("snow")).get("3h").getAsFloat();
				}
			}
			Weather newobj = new Weather(wMain, wDescription, temp, pressure ,humidity, windSpeed, windDeg, clouds, rain, snow, date);
			list.add(newobj);

		}
		
		Forecast f = new Forecast(cityinfo, list);
		return f;
	}

}
