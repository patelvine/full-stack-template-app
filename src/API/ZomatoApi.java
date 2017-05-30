package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import objects.Place;
import objects.Restaurant;

/**
 * 
 * @author cheavleat
 *
 */
public class ZomatoApi {
	private String key = "8d119a557104ec3a65639e17df09afce";
	private String baseSearchURL = "https://developers.zomato.csom/api/v2.1/search?";
	//private int pageSize = 30;
	private ArrayList<JsonObject> json;

	public ZomatoApi() {
		json = new ArrayList<JsonObject>();
	}


	public String buildURL(double lat, double lon, int radius) {
		//String url = this.baseSearchURL + "count=" + this.pageSize;
		return baseSearchURL + this.searchWithLatLonWithIn(lat, lon, radius);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Search with the given Latitude and Longitude and given Radius
	 * 
	 * @param lat
	 * @param lon
	 * @param radius
	 * @return
	 */
	public String searchWithLatLonWithIn(double lat, double lon, int radius) {
		return "&lat=" + lat + "lon=" + lon + "radius=" + radius;
	}

	/**
	 * Read the response and filter the needed data and store in json
	 * (ArrayList<JsonObject>)
	 * 
	 * @param br
	 * @throws IOException
	 */
	public void readResponse(BufferedReader br) throws IOException {
		String inputLine;
		JsonObject json = new JsonObject();
		while ((inputLine = br.readLine()) != null) {
			System.out.println(inputLine);
			json = jsonFromString(inputLine);
		}
		JsonArray restaurantsArray = (JsonArray) json.get("restaurants");
		// System.out.print("Array"+restaurantsArray);
		for (int i = 0; i < restaurantsArray.size(); i++) {
			JsonObject restaurant1 = (JsonObject) restaurantsArray.get(i);
			JsonObject restaurant = (JsonObject) restaurant1.get("restaurant");
			Restaurant res = new Restaurant(restaurant.get("name").getAsString(), restaurant.get("id").getAsInt());
			res.setJson(restaurant);
			res.setLocation((JsonObject) restaurant.get("location"));
			res.setURL(restaurant.get("url").getAsString());
			// System.out.println("restuarant "+restaurant);
			this.json.add(restaurant);
			System.out.println(res.getJson());
		}

	}

	/**
	 * Make the HttpUrlConnection to the zomato api by provide all the request
	 * header such as (user-key) and call on readResponse to read the response
	 * from request
	 * 
	 * @param urlString
	 * @throws ParseException
	 */
	public void setupConnection(String urlString) throws ParseException {
		try {
			//String u = URLEncoder.encode(urlString, "UTF-8");
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Accept", "json");
			connection.setRequestProperty("user-key", this.key);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//int responseCode = connection.getResponseCode();
			InputStreamReader inReader = new InputStreamReader(connection.getInputStream());
			BufferedReader inBr = new BufferedReader(inReader);
			readResponse(inBr);
			inBr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Build the search url to request from zomato api by lat, lon and radius
	 * 
	 * @param lat
	 * @param lon
	 * @param radius
	 * @return
	 */
	public String buildSearchURL(double lat, double lon, int radius) {
		//String url = this.baseSearchURL + "count=" + this.pageSize;
		return baseSearchURL + this.searchWithLatLonWithIn(lat, lon, radius);
	}

	/**
	 * Convert String to JsonObject
	 * 
	 * @param string
	 * @return
	 */
	private static JsonObject jsonFromString(String string) {
		JsonParser jsonParser = new JsonParser();
		return (JsonObject) jsonParser.parse(string);
	}

	/**
	 * Convert any object to Json String
	 * 
	 * @param o
	 * @return
	 */
	public String objectToJson(Object obj) {
		String json = new Gson().toJson(obj);
		return json;
	}

	public ArrayList<JsonObject> getJsonObjectArrayList() {
		return this.json;
	}

	/**
	 * set size of the number
	 * 
	 * @param size
	 */
	//public void setSize(int size) {
	//	this.pageSize = size;
	//}
}