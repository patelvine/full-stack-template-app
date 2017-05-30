package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Auckland Transport API
 * @author cheavleat
 *
 */
public class ATApi {

	private String key = "api_key=26dfb0f0-5908-4835-8f3a-4c7f7453f97b";
	private String baseUrl = "https://api.at.govt.nz/v1" ; 
	//Real Time
	public final String gtfsRealTime = "/public/realtime?";
	public final String gtfsRealTimeVehicleLocation ="/public/realtime/vehiclelocations?";
	public final String gtfsRealTimeTripUpdate = "/public/realtime/tripupdates?";
	//General Transit Feed Specification
	public final String gtfsVersion = "/gtfs/versions?";
	public final String gtfsAgency = "/gtfs/agency?";
	public final String gtfsCalendar = "gtfs/calendar";
	public final String gtfsCalendarByService  = "gtfs/calendar/serviceid/:service_id";
	public final String gtfsExceptionList = "gtfs/calendarDate";
	public final String gtfsExceptionByService ="gtfs/calendarDate/serviceid/:service_id";
	public final String gtfsRouteList = "gtfs/routes";
	public final String gtfsRouteListById = "gtfs/routes/routesId/:routeid";
	public final String gtfsRouteByLongName = "gtfs/routes/routeLongName/:routeLongName";
	public final String gtfsRouteByShortName = "gtfs/routes/routeShortName/:routeShortName";
	public final String gtfsRouteSearch = "gtfs/routes/search/:searchtext";
	public final String gtfsRouteByLatLon = "gtfs/routes/geosearch";
	public final String gtfsRouteByStopId = "gtfs/routes/stopid/:stop_id";
	public final String gtfsShapeById = "gtfs/shapes/shapesId/:shapes_id";
	public final String gtfsShapeByTripId = "gtfs/shapes/tripid/:trip_id";
	public final String gtfsShapeGeometryById = "gtfs/shpae/geometry/:shape_id";
	public final String gtfsStopsList = "gtfs/stops";
	public final String gtfsStopsById = "gtfs/stops/stopId/stop_id";
	public final String gtfsStopByCode = "gtfs/stops/stopCode/:stopCode";
	public final String gtfsStopByTripId = "gtfs/stops/tripId/:trip_id";
	public final String gtfsStopByName = "gtfs/stops/search/:searchText";
	public final String gtfsStopByPosition = "gtfs/stops/geosearch";
	public final String gtfsStopTimeByStopId = "gtfs/stopTimes/:stop_id";
	public final String gtfsStopTimeByTripId = "gtfs/stopTimes/tripId/:trip_id";
	public final String gtfsStopTimeByTripIdAndTripSequence = "gtfs/stopTimes/tripId/:tripId/stopSequence/:stopSequence";
	public final String gtfsTripList = "gtfs/trips";
	public final String gtfsTripById = "gtfs/trips/tripsId/:trip_id";
	public final String gtfsTripByRouteId = "gtfs/trips/routeId?:route_id";
	//Displays
	public final String parkingLocationUrl = "/public/display/parkinglocations?";
	public final String schedualWork = "/public/display/scheduledWorks";
	public final String customerServiceCenter = "/public/display/customerservicecenters";
	
	
	public ATApi(){
		
	}
	
	public String buildUrlGet(String url){
		return this.baseUrl+url+this.key;
	}
	

	
	/**
	 * Setup HttpURLConnection and do the request to given urlString (link)
	 * Then read the response from server.
	 * @param urlString
	 */
	public void setupConnection(String urlString){
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
	 * 
	 * @param br
	 * @throws IOException
	 */
	public void readResponse(BufferedReader br) throws IOException{
		 String inputLine;
			//StringBuffer response = new StringBuffer();
			JsonArray jsonResponseArray = new JsonArray();
			int count = 0 ;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(count);
				//response.append(inputLine);
				JsonObject json = jsonFromString(inputLine);
				jsonResponseArray = (JsonArray) json.get("response");
			}
			System.out.println(jsonResponseArray);
			for (int i =0 ; i < jsonResponseArray.size() ; i++){
				JsonObject object = (JsonObject) jsonResponseArray.get(i); 
				System.out.println(object.get("id"));
			}
	}
	
	
	/**
	 * Convert String to JSON object
	 * @param jsonObjectStr
	 * @return
	 * Credit: MUJTABA
	 */
	private static JsonObject jsonFromString(String string) {		
		JsonParser jsonParser = new JsonParser();
		return (JsonObject)jsonParser.parse(string);			    
	}
	
	public static void main(String[] args) {
		ATApi at = new ATApi();
		String url =at.buildUrlGet(at.parkingLocationUrl);
		at.setupConnection(url);
		
	}
	
	
	
}
