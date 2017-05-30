package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.DBconnector;
import objects.Event;

/**
 * 
 * @author cheavleat
 */
public class EVApi {
	private final String apiKeyId = "2LK4BR6cknDWqWHP&id=20218701" ;
	public final String testUrl ="http://api.eventful.com/json/events/search?app_key=2LK4BR6cknDWqWHP&id=20218701&location=wellington";
	private final String baseURL ="http://api.eventful.com/json/events/search?app_key=2LK4BR6cknDWqWHP&id=20218701";
	private ArrayList<Event> eventsList ; 
	private int pageSize = 10 ;
	private ArrayList<JsonObject> jsonObjectArray ; 
	private String city = "Not specify" ; 
	public EVApi(){
		
		jsonObjectArray = new ArrayList<JsonObject>() ; 
	}
	
	/**
	 * set page size , default pagesize is 10 if not set
	 * @param size
	 */
	public void setPageSize(int size){
		this.pageSize = size ; 
	}
	
	/**
	 * Convert String to JsonObject
	 * @param string
	 * @return
	 */
	private static JsonObject jsonFromString(String string) {		
		JsonParser jsonParser = new JsonParser();
		return (JsonObject)jsonParser.parse(string);			    
	}
	
	/**
	 * Get arrayList of Events that return from response 
	 * @return
	 */
	public ArrayList<Event> getEventsArrayList(){
		return this.eventsList;
	}
	
	/**
	 * clear the events ArrayList
	 */
	public void clearEventsArrayList(){
		this.eventsList.clear();
	}
	
	/**
	 * Build up the URL string base on input provide, you can leave the keyword "" or null 
	 * same as location and category 
	 * @param keyword
	 * @param keyword1
	 * @param keyword2
	 * @param location
	 * @param category
	 * @return
	 */
	public String buildRequestURL(String keyword ,String keyword1, String keyword2, String location , String category  ){
		String url = this.baseURL ; 
		if(keyword !="" && keyword != null){
			url+= searchByKeywords(keyword, keyword1, keyword2);
		}
		if(location != "" && location != null){
			city = location  ; 
			url+= searchByLocation(location);
		}
		if(category !="" && category != null){
			url+= searchByCategory(category);
		}
		return url+"&page_size="+this.pageSize ;
		
	}
	
	/**
	 * search query by providing keyword: keyword can be up to 3 
	 * @param keyword 
	 * @param keyword1 can be "" or null
	 * @param keyword2 can be "" or null
	 * @return
	 */
	public String searchByKeywords(String keyword, String keyword1, String keyword2){
		if(keyword2!="" && keyword2 != null ){
			return "&keywords="+keyword+","+keyword1+","+keyword2;
		}else if (keyword1 != "" && keyword1 !=null){
			return "&keywords="+keyword+","+keyword1;
		}else if(keyword != "" && keyword != null){
			return "&keywords="+keyword;
		}else{
			return "";
		}			
	}
	
	/**
	 * Search query by providing the location
	 * @param location
	 * @return
	 */
	public String searchByLocation(String location){
		return "&location="+location;
	}
	
	/**
	 * Search query by providing category 
	 * @param category
	 * @return
	 */
	public String searchByCategory(String category){
		return "&category="+category;
	}
	
	/**
	 * Search the event within radius of Point(lat,lon) 
	 * @param lat
	 * @param lon
	 * @param radius: 
	 * @param mile
	 * @return
	 */
	public String searchWithIn(float lat, float lon, int radius , boolean mile){
		if(mile){
			return "&where="+lat+","+lon+"&within="+radius ;
		}else {
			return "&where="+lat+","+lon+"&within="+radius+"&units=km";
		}
	}
	/**
	 * Setup HttpURLConnection and do the request to given urlString (link)
	 * Then read the response from server.
	 * @param urlString
	 * @throws ParseException 
	 */
	public void setupConnection(String urlString) throws ParseException{
		try {
			String u = URLEncoder.encode(urlString, "UTF-8");
			URL url = new URL(urlString);
			HttpURLConnection connection =  (HttpURLConnection)url.openConnection() ;
			connection.setRequestProperty("Content-Language", "en-US");  			
		    connection.setUseCaches (false);
		    connection.setDoInput(true);
		    connection.setDoOutput(true);
		    int responseCode = connection.getResponseCode();
		    InputStreamReader inReader = new InputStreamReader(connection.getInputStream());
		    BufferedReader inBr = new BufferedReader(inReader);
		    readResponse(inBr);		
			inBr.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**EventList renew everytime this method get calls
	 * Read the response from get request then filter all needed data and store in the events object
	 * in the eventsList
	 * @param br
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void readResponse(BufferedReader br) throws IOException, ParseException{
			eventsList = new ArrayList<Event>() ; 
			String inputLine;			
		 	JsonObject json = new JsonObject() ; 
			while ((inputLine = br.readLine()) != null) {
				json = jsonFromString(inputLine);	
				
			}
			
			JsonObject events = (JsonObject) json.get("events");
			JsonArray event = (JsonArray) events.get("event");
			for (int i =0 ; i < event.size() ; i++){
				JsonObject object = (JsonObject) event.get(i); 
				//System.out.println(object.get("title")+" "+ object.get("start_time")+" "+ object.get("url"));
				//System.out.println(object.get("start_time"));
				
				String dateString = object.get("start_time").getAsString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateString);
				//Date d = new Date ();
				Event ev = new Event(this.city , object.get("title").toString(),  date, object.get("url").toString());
				ev.setJson(object);
				this.jsonObjectArray.add(object);
				this.eventsList.add(ev);
			}			
	}
	
	
	/**
	 * After retrieving from the eventfinda api , This method will upload every events in 
	 * event arraylist to events table in database.
	 */
	public void uploadToDB(){
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;	
			ArrayList<Event> events = this.getEventsArrayList() ; 
			for (Event e :events){
				
				String name = e.getName();
				Date date = e.getDate() ; 
				Date todayDate = new Date() ; 
				java.sql.Timestamp todaySqlDate = new java.sql.Timestamp(todayDate.getTime());
				java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
				System.err.println(sqlDate);
				String url = e.getUrl();
				ps = con.prepareStatement("INSERT INTO events (city,name,date,url,lastupdate) "+ "VALUES(?,?,?,?,?)"); 
				ps.setString(1, this.city);
				ps.setString(2, name);
				ps.setTimestamp(3, sqlDate);
				ps.setString(4, url);
				ps.setTimestamp(5, todaySqlDate);
				//ps.setTime(5, time);
				ps.execute();
				
			}
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteOutdatedEvent(Date d ){
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(d.getTime());
			ps = con.prepareStatement("DELETE FROM events WHERE date < ? ");
			ps.setTimestamp(1, sqlDate);
			ps.execute();
			con.close();
						
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	/**
	 * This method will automatically build the url with the specification from parameters, after that it will
	 * request to the eventfinda api, results that retrived will upload into database 
	 * @param keyword
	 * @param keyword1
	 * @param keyword2
	 * @param location
	 * @param category
	 * @throws ParseException
	 */
	public void doRequestAndUploadDB(String keyword ,String keyword1, String keyword2, String location , String category) throws ParseException{
		String url = buildRequestURL(keyword, keyword1, keyword2, location, category); 
		System.out.println(url);
		setupConnection(url);
		uploadToDB();		
	}
	
	public boolean checkLocationExisted(){return true ; }
	
	
	public void cachRequestUploadDB(String keyword ,String keyword1, String keyword2, String location , String category){
		try {
			Date todayDate = new Date() ;
			deleteOutdatedEvent(todayDate);
			Connection con = DBconnector.getConnection();
			PreparedStatement selectps;
			 
			ArrayList<Event> e = new ArrayList<Event>();
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(todayDate.getTime());
			selectps = con.prepareStatement("SELECT * FROM events where city =? "); 
			selectps.setString(1,location);
			ResultSet rs = selectps.executeQuery();
			if( !rs.next()){
				System.err.println("NO result from the query - start request to api and upload to db");
				doRequestAndUploadDB(keyword, keyword1, keyword2, location , category);
			}else {
				System.err.println("Results");
				Date latestUpdate = new Date (02,01,2015);
				while ( rs.next() ) {
					String name = rs.getString("name");
					Date date1 = rs.getTimestamp("date");
					String url = rs.getString("url");
					Date lastUpdate = rs.getTimestamp("lastupdate");
					if(lastUpdate.after(latestUpdate)){ // get the latest update date 
						latestUpdate = lastUpdate ; 
					}					
					e.add(new Event(location, name, date1,url ));
					System.out.println(name + "-"+date1);
				}
				Date today = new Date() ; 
				double hoursDiff = getDateDiffInHours (latestUpdate , today)  ; 
				if(hoursDiff>= 48){
					System.out.println("last update is more than 2 days- start request to api and upload to db "+hoursDiff);
					doRequestAndUploadDB(keyword, keyword1, keyword2, location , category);
				}else {
					System.out.println("last update is "+ hoursDiff);
				}
				
				
			}
			
			con.close();
			
			
			
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public static long getDateDiffInHours(Date date1, Date date2) {
		TimeUnit timeUnit = TimeUnit.HOURS ; 
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public static void main(String[] args) throws ParseException {
		
		
		EVApi ev = new EVApi();
		ev.setPageSize(30);
		//ev.doRequestAndUploadDB("",null,null, "manchester", "");
		ev.cachRequestUploadDB("",null,null, "ireland", "");
//		String url =ev.buildRequestURL("",null,null, "england", "");
//		System.out.println(url);		
//		ev.setupConnection(url);
//		ev.uploadToDB();
		ArrayList<Event> events =  ev.getEventsArrayList();
		/*
		for ( Event e : events){
			System.out.println(e.toString());
		}
		*/
	}


}
