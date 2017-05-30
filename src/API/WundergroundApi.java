
package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
/**
 * 
 * @author Mujtaba Mirzad
 *
 */
public class WundergroundApi {
	private static final String key = "c388eb117cbfff89";
	private String format = "json";
	private static final String URL = "http://api.wunderground.com/api/" + key + "/";

	
	/**
	 * 
	 * @return - String containing 10 day weather forecast.
	 */
	public String getTENDayForecast(String countryCode, String city) {
		String buildurl = URL + "forecast10day/q/" + countryCode + "/" + city + "." + this.format;
		String result = null;
		try {
			result = getRequest(buildurl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	/**
	 * 
	 * @return - String containing Today weather for a  city.
	 */
	public String getCurrentDayWeatrher(String countryCode, String city) {
		String buildurl = URL + "conditions/q/" + countryCode + "/" + city + "." + this.format;
		String result = null;
		try {
			result = getRequest(buildurl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 
	 * @param date = YYYYMMDD format 
	 * @param countryCode = String value e.g. 'nz', 'uk' etc
	 * @param city = name of the city
	 * @return String containing one day past weather  in json format
	 */
	public String getPastWeather(String date, String countryCode, String city){
		
		String buildurl = URL + "history_"+date+"/q/" + countryCode + "/" + city + "." + this.format;
		String result = null;
		try {
			result = getRequest(buildurl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 
	 * @param url - target url api access http address
	 * @return - result from api call
	 * @throws Exception
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
	 * To change the format of a query to the Wunderground api.
	 *  
	 * @param newformat - can obly be json or xml
	 */
	public void setFormat(String newformat) {
		if (newformat.equals("json") || newformat.equals("xml")) {
			this.format = newformat;
		} else {
			System.out.println("Only 'json' and 'xml' format is allowed");
		}
	}
	
	private boolean checkformat(String type, String obj){
		if(type.equals("cCode")){
			if(obj.length()!= 2 || obj == null){
				System.out.println("Please check the country code.");
				return false;
			}
		}else if(type.equals("city")){
			if(obj == null){
				System.out.println("Please enter a city name");
				return false;
			}
		}else if(type.equals("date")){
			if(obj.length()!=4){
				System.out.println("Please check the date format. Should be 'YYYYMMDD'");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return either json or xml
	 */
	public String getFormat() {
		return format;
	}
	
	public static String getKey() {
		return key;
	}

	public static String getUrl() {
		return URL;
	}
}