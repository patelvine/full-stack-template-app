package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import objects.City;
import objects.Forecast;
import objects.Weather;

/**
 * This class is used for retrieving weather data from Open Weather Map
 * web-site. Given the appropriate user input, class can either return's current
 * weather or 5 day weather forecast.
 * 
 * Current weather is return in form of class, which stores the main weather
 * condition, temperature, minimum temperature, maximum temperature, rain (3hour
 * per mm), snow (3 hour per mm), cloud (in %), wind speed (meter/sec) and wind
 * degree. Also stores the general information about the location e.g. City
 * name, country code, and city ID.
 * 
 * 
 * 5 day weather forecast data is return in the form of ArrayList of Forecast.
 * Which stores the same data as weather class, accept one additional data
 * field, Date (is String shows the time and date when data was retrieved,
 * updated every 3 hours).
 * 
 * @author Mujtaba Mirzad
 *
 */
public class OpenWeatherMapApi {
	private static String key = "ddbafa1e5bb4d00114e1dc30bdfd4f43";
	private final String URL = "http://api.openweathermap.org/data/2.5/";
	private String cnt = "10";

	/**
	 * Returns a Json Object containing the weather details. Given the right
	 * input, this method can return current day weather or 5 day weather
	 * forecast.
	 * 
	 * This method always returns a json object, even when one or more input
	 * field is left empty, there are four input parameters/fields: (mode, type,
	 * parameter 1, parameter 2).
	 *
	 * Some of the call does not required all the input fields e.g. retrieving
	 * current weather by (mode ='group') set of cityIDs, getting the weather by
	 * a circle (mode = 'find') or getting weather by a box shape (mode = 'box')
	 * The return data is in the metric units.
	 * 
	 * There are different ways of retrieving the weather data. (the first three
	 * can used to get 5 day weather forecast by changing 'weather' to
	 * 'forecast' ) - ("weather", "cityName", "Wellington", "nz") - current day
	 * weather by city name - ("weather", cityID, "ID", "")- by city ID (note:
	 * last field is left empty) - ("weather", "coord", "latitude", "longitude")
	 * - by Coordinates - ("weather", "zip", "zipcode", "country code") - by zip
	 * code
	 * 
	 * -("group", "", "list of city ids", "") -("find", "", "Latitude",
	 * "Longitude") - returns data from cities laid within definite circle that
	 * is specified by center point ('lat', 'lon') and expected number of cities
	 * ('cnt') around this point.
	 * 
	 * -("box", "", "[lon-left,lat-bottom,lon-right,lat-top]", "cluster")-
	 * cluster use server clustering of points. Possible values are [yes, no].
	 * Returns the data from cities within the defined rectangle specified by
	 * the geographic coordinates.
	 * 
	 * @param mode
	 * @param type
	 * @param p1
	 * @param p2
	 * @return
	 */
	public JsonObject getWeather(String mode, String type, String p1, String p2) {
		String tragetURL;
		String result = null;

		/*
		 * 5 day forecast has only three options at this stage search by city
		 * name, by city id and coordinates. (city id is recommended to get
		 * precise location)
		 */

		if (mode.equals("forecast")) {
			tragetURL = "";
			switch (type) {
			case "cityName":
				tragetURL = URL + mode + "?q=" + p1 + "," + p2 + "&units=metric" + "&appid=" + key;
				break;
			case "cityID":
				tragetURL = URL + mode + "?id=" + p1 + "&units=metric" + "&appid=" + key;
				break;
			case "coord":
				tragetURL = URL + mode + "?lat=" + p1 + "&units=metric" + "&lon=" + p2 + "&appid=" + key;
				break;
			}
			try {
				result = getRequest(tragetURL);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (mode.equals("weather")) {
			tragetURL = "";
			switch (type) {
			case "cityName":
				tragetURL = URL + mode + "?q=" + p1 + "," + p2 + "&units=metric" + "&appid=" + key;
				break;
			case "cityID":
				tragetURL = URL + mode + "?id=" + p1 + "&units=metric" + "&appid=" + key;
				break;
			case "coord":
				tragetURL = URL + mode + "?lat=" + p1 + "&lon=" + p2 + "&units=metric" + "&appid=" + key;
				break;
			case "zip":
				tragetURL = URL + mode + "?zip=" + p1 + "," + p2 + "&units=metric" + "&appid=" + key;
				break;
			}
			try {
				result = getRequest(tragetURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* extra mode to retrieved data for multiple city */
		} else if (!mode.equals("weather") && !mode.equals("forecast")) {
			tragetURL = "";
			switch (mode) {
			case "group":
				tragetURL = URL + mode + "?id=" + p1 + "&units=metric" + "&appid=" + key;
				break;
			case "find":
				tragetURL = URL + mode + "?lat=" + p1 + "&lon=" + p2 + "&cnt=" + cnt + "&units=metric" + "&appid="
						+ key;
				break;
			case "box":
				tragetURL = URL + mode + "/city?bbox=" + p1 + "&cluster=" + p2 + "&units=metric" + "&appid=" + key;
				break;
			}
			try {
				result = getRequest(tragetURL);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Not found! Mode can only be the following.\n forecast\n current\n group");
		}
		JsonObject jsonObj = jsonFromString(result);

		return jsonObj;

	}

	/**
	 * This method is used to make http: get call, given the target URL.
	 * 
	 * @param url
	 *            to make the http: get call to.
	 * @return a string containing the response from url parameter.
	 * @throws Exception
	 *             if the call connection to url is unsuccessful.
	 */
	private String getRequest(String url) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	/**
	 * Returns a Weather object containing current weather data. Which is
	 * extracted from an JsonObject
	 * 
	 * @param jsonFile2
	 *            - is Json object containing weather data.
	 * @return a Weather Object with important data.
	 */
	// private Weather extractInfo(JsonObject jsonFile2) {
	// JsonArray weather = jsonFile2.getAsJsonArray("weather");
	// JsonObject main = (JsonObject) jsonFile2.get("main");
	// JsonObject wind = (JsonObject) jsonFile2.get("wind");
	// JsonObject clouds = (JsonObject) jsonFile2.get("clouds");
	// JsonObject sys = (JsonObject) jsonFile2.get("sys");
	//
	// float rain = 0;
	// float snow = 0;
	//
	// if(jsonFile2.has("rain")){//checking rain field exist
	// if(!jsonFile2.getAsJsonObject("rain").has("3h")){// sometime value is
	// missing
	// rain = 0.00f;
	// }else{
	// rain = jsonFile2.getAsJsonObject("rain").get("3h").getAsFloat();
	// }
	// }
	// if(jsonFile2.has("snow")){// checking snow field exist
	// if(!jsonFile2.getAsJsonObject("snow").has("3h")){// sometime value is
	// missing
	// snow = 0.00f;
	// }else{
	// snow = jsonFile2.getAsJsonObject("snow").get("3h").getAsFloat();
	// }
	// }
	// /*Storing location data*/
	// int cID = jsonFile2.get("id").getAsInt();
	// String cName = jsonFile2.get("name").getAsString();
	// String cCode = sys.get("country").getAsString();
	// /*Storing weather info*/
	// String wMain = "";
	// JsonObject o = (JsonObject) weather.get(0);
	// wMain = o. get("main").getAsString();
	//
	// double temp = main.get("temp").getAsDouble();
	// double temp_min = main.get("temp_min").getAsDouble();
	// double temp_max = main.get("temp_max").getAsDouble();
	// double wSpeed = wind.get("speed").getAsDouble();
	// double wDeg = wind.get("deg").getAsDouble();
	// double clds = clouds.get("all").getAsDouble();
	// /*Creating Weather object*/
	// Weather w = new Weather(cID, cName, cCode, wMain, temp, temp_min,
	// temp_max, wSpeed, wDeg, clds, rain, snow);
	//
	// return w;
	//
	// }
	/**
	 * Method is used to convert string in json format to json object for data
	 * retrieval.
	 * 
	 * @param jsonObjectStr
	 *            is a string containing weather data in json format.
	 * @return a JsonObject
	 */
	private JsonObject jsonFromString(String jsonObjectStr) {

		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject) jsonParser.parse(jsonObjectStr);

		return object;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public static String getKey() {
		return key;
	}
}