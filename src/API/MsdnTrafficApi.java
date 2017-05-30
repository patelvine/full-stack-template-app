package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import objects.BoundingBox;
import objects.Coordinates;
import objects.TrafficIncident;

/**
 * Class for traffic incident api (microsoft) provide 10 000 calls per year (free version)
 * @author cheavleat
 */
public class MsdnTrafficApi {
	private final String testURL = "http://dev.virtualearth.net/REST/v1/Traffic/Incidents/37,-105,45,-94?key=Ar8iTJiNoOQIl8V60gzRIvhJUvOtRcfwdAq-mqAzfgVLMimdhWQctWXjrkkn-ZEn"; 
	private final String key = "?key=Ar8iTJiNoOQIl8V60gzRIvhJUvOtRcfwdAq-mqAzfgVLMimdhWQctWXjrkkn-ZEn"; // need to change key 
	private final String baseURL = "http://dev.virtualearth.net/REST/v1/Traffic/Incidents/";
	private ArrayList<TrafficIncident> incidents ; 
	private ArrayList<JsonObject> jsonObjectArray ;
	public MsdnTrafficApi(){
		this.incidents = new ArrayList<TrafficIncident>() ;
		this.jsonObjectArray = new ArrayList<JsonObject>() ;
	}
	
	/**
	 * Build the URL request according to the given lat lon and distance
	 * bbox will get the bouding box around the lat lon (by given distance)
	 * @param lat
	 * @param lon
	 * @param distance distance around coordinates
	 * @return URL string for the request 
	 */
	public String buildURL(double lat , double lon , double distance){
		Coordinates coords = new Coordinates(lat, lon ,"point");
		BoundingBox bbox = coords.getBoudingBox(distance);
		return this.baseURL + bbox.getMinLat()+","+bbox.getMinLon()+","+bbox.getMaxLat()+","+bbox.getMaxLon()  + this.key; 
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
	
	
	/**
	 * Read the response from get request then store all the events (with needed data)
	 * in the eventsList
	 * @param br
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void readResponse(BufferedReader br) throws IOException, ParseException{
		String inputLine;			
	 	JsonObject json = new JsonObject() ; 
		while ((inputLine = br.readLine()) != null) {
			json = jsonFromString(inputLine);		
		}
		JsonArray resourceSetsArray = (JsonArray) json.get("resourceSets");	
		JsonObject resourceSetArray0 = (JsonObject) resourceSetsArray.get(0);
		System.out.println("Estimate : "+ resourceSetArray0.get("estimatedTotal"));
		JsonArray resourcesArray = (JsonArray) resourceSetArray0.get("resources");
		/*Xml tree showing the structure of response
		 * <ResourceSet>
		 *	<Resources>
		 *		<__type></__type>
		 *		<point>
		 *			<type></type>
		 *			<coordinates>Array of 2 numbers(lat, lon)</coordinate>
		 *		</point>
		 *		<congestion></congestion>
		 *		<description></description>
		 *		<detour></detour>
		 *		<end>Date</end>
		 *		<incidentId></incidentId>
		 *		<lane></lane>
		 *		<lastModified></lastModified>
		 *		<roadClosed></roadClosed>
		 *		<severity></severity>
		 *		<start></start>
		 *		<type></type>
		 *		<verified></verified>
		 *	</Resources>
		 * </ResourceSet>
		
		 */
		for ( int i =0 ; i<resourcesArray.size() ; i++){
			JsonObject resourceArrayi = (JsonObject) resourcesArray.get(i);
			JsonPrimitive __type = (JsonPrimitive)resourceArrayi.get("__type");
			JsonObject point =  (JsonObject) resourceArrayi.get("point"); //System.err.println(point); // to do jsonTopoint 
			JsonPrimitive pointType = (JsonPrimitive)point.get("type");
			JsonArray pointCoord = (JsonArray)point.get("coordinates");
			JsonPrimitive pointlat = (JsonPrimitive)pointCoord.get(0);
			JsonPrimitive pointlon = (JsonPrimitive)pointCoord.get(1);
			JsonPrimitive congestion = (JsonPrimitive) resourceArrayi.get("congestion");
			JsonPrimitive description = (JsonPrimitive) resourceArrayi.get("description");
			JsonPrimitive detour = (JsonPrimitive) resourceArrayi.get("detour");
			JsonPrimitive end = (JsonPrimitive) resourceArrayi.get("end");
			JsonPrimitive incidentId = (JsonPrimitive) resourceArrayi.get("incidentId");
			JsonPrimitive lane = (JsonPrimitive) resourceArrayi.get("lane");
			JsonPrimitive lastModified = (JsonPrimitive) resourceArrayi.get("lastModified");
			JsonPrimitive roadClosed = (JsonPrimitive) resourceArrayi.get("roadClosed");
			JsonPrimitive severity = (JsonPrimitive) resourceArrayi.get("severity");
			JsonPrimitive start = (JsonPrimitive) resourceArrayi.get("start");
			JsonPrimitive type = (JsonPrimitive) resourceArrayi.get("type");
			JsonPrimitive verified = (JsonPrimitive) resourceArrayi.get("verified");			
			System.out.println(__type +"-"+point +"-"+congestion+"-"+description+"-"+detour+"-"+end+"-"+incidentId+"-"+lane+"-"+lastModified+"-"+severity+"-"+start+"-"+type+"-"+verified);			
			Coordinates coord = new Coordinates(pointlat.getAsDouble(), pointlon.getAsDouble(),pointType.getAsString());
			
			TrafficIncident traffic = new TrafficIncident(coord, incidentId.getAsInt(),severity.getAsInt(), type.getAsInt());
			//traffic.setCongestion(congestion.getAsString());
			traffic.setDescription(description.getAsString());
			//traffic.setLane(lane.getAsString());
			traffic.setroadClosed(roadClosed.getAsBoolean());
			traffic.setSeverity(severity.getAsInt());
			traffic.setType(type.getAsInt());
			traffic.setJson(resourceArrayi);
			this.jsonObjectArray.add(resourceArrayi);
			this.incidents.add(traffic);
			System.err.println(traffic.toString());
		}
		
		System.out.println(resourcesArray);			
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
	 * clear the incidents arrayList 
	 */
	public void clearIncidentArrayList(){
		this.incidents.clear();
	}
	
	public ArrayList<TrafficIncident> getIncidentArrayList(){
		return this.incidents;
	}
	
	public ArrayList<JsonObject> getJsonObjectArrayList(){
		return this.jsonObjectArray;
	}
	
	public static void main(String[] args) throws ParseException {
		MsdnTrafficApi mtraffic = new MsdnTrafficApi() ; 
		String url = mtraffic.buildURL(40.759212, -73.984627,10);
		System.out.println(url);
		mtraffic.setupConnection(url);
	}
		
	
}
