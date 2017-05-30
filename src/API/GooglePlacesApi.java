package API;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import database.DBconnector;
import objects.Place;

import com.google.gson.Gson;

/**
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 * 
 *         <p>
 *         This class implements the Google Places API to obtain local places
 *         information
 * 
 *         <p>
 *         Method call examples
 *         <p>
 *         instance.getNearbySearch("-33.8670522","151.1957362","500",params);
 *         <p>
 *         instance.getTextSearch("restaurants in Sydney", params);
 *         <p>
 *         instance.getDetails("ChIJt-7u4hGuEmsRb6tmlnmtZLU"));
 */

public class GooglePlacesApi {
	private final String key;
	private final String nearbySearchUrl;
	private final String textSearchUrl;
	private final String detailsUrl;
	private DBconnector con = new DBconnector();

	/**
	 * Constructs a GooglePlacesApi object.
	 * <p>
	 * No parameters are required
	 */
	public GooglePlacesApi() {
		this.key = "AIzaSyBzjhmgd92BKuHFD9RR5wenhTe_NyWnEVk";
		this.nearbySearchUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?";
		this.textSearchUrl = "https://maps.googleapis.com/maps/api/place/textsearch/xml?";
		this.detailsUrl = "https://maps.googleapis.com/maps/api/place/details/xml?placeid=";
	}

	/**
	 * Search for places within a specified location and radius
	 * 
	 * @param lat
	 *            The locations latitude
	 * @param lon
	 *            The locations longitude
	 * @param radius
	 *            The amount of area to search with in the given location
	 * @param params
	 *            A HashMap containing extra parameters for a more specific
	 *            search
	 * @return List of Strings containing places details in a XML format
	 */
	public List<Place> getNearbySearch(String lat, String lon, String radius, HashMap<String, String> params) {
		String url = buildUrl(this.nearbySearchUrl + "location=" + lat + "," + lon + "&radius=" + radius, params);
		List<String> placeIds = search(url);

		List<Place> placeDetails = new ArrayList<Place>();
		for (String id : placeIds) {
			Place details = getDetails(id);
			placeDetails.add(details);
		}
		postToDB(placeDetails);
		return placeDetails;
	}

	/**
	 * Search for places based off normal text input
	 * 
	 * @param query
	 *            A String describing the search request e.g. restaurants in
	 *            Sydney
	 * @param params
	 *            A HashMap containing extra parameters for a more specific
	 *            search
	 * @return List of Strings containing place details in a XML format
	 */
	public List<Place> getTextSearch(String query, HashMap<String, String> params) {
		String url = buildUrl((this.textSearchUrl + "query=" + query).replaceAll(" ", "+"), params);
		List<String> placeIds = search(url);

		List<Place> placeDetails = new ArrayList<Place>();
		for (String id : placeIds) {
			placeDetails.add(getDetails(id));
		}
		postToDB(placeDetails);
		return placeDetails;
	}

	/**
	 * Obtain a single places details by using their unique place id assigned by
	 * google
	 * 
	 * @param place_id
	 *            Unique id assigned to a place by google
	 * @return String containing a places details in a XML format
	 */
	public Place getDetails(String place_id) {
		String url = detailsUrl + place_id + "&key=" + key;
		Document doc = sendApiRequest(url);

		String formatted_address = null;
		String name = null;
		float rating = 0;
		String urlGoogle = null;
		String vicinity = null;
		String website = null;
		float lat = 0;
		float lon = 0;
		
		if(doc.getElementsByTagName("formatted_address").getLength() != 0)
			formatted_address = doc.getElementsByTagName("formatted_address").item(0).getTextContent();
		if(doc.getElementsByTagName("name").getLength() != 0)
			name = doc.getElementsByTagName("name").item(0).getTextContent();
		if(doc.getElementsByTagName("rating").getLength() != 0)
			rating = Float.parseFloat(doc.getElementsByTagName("rating").item(0).getTextContent());
		if(doc.getElementsByTagName("url").getLength() != 0)
			urlGoogle = doc.getElementsByTagName("url").item(0).getTextContent();
		if(doc.getElementsByTagName("vicinity").getLength() != 0)
			vicinity = doc.getElementsByTagName("vicinity").item(0).getTextContent();
		if(doc.getElementsByTagName("website").getLength() != 0)
			website = doc.getElementsByTagName("website").item(0).getTextContent();
		if(doc.getElementsByTagName("lat").getLength() != 0){
			lat = Float.parseFloat(doc.getElementsByTagName("lat").item(0).getTextContent());
			lon = Float.parseFloat(doc.getElementsByTagName("lng").item(0).getTextContent());
		}
		
		List<String> types = new ArrayList<String>();
		if(doc.getElementsByTagName("type").getLength() != 0){
			NodeList nList = doc.getElementsByTagName("type");
			for(int i = 0; i < nList.getLength(); i++){
				if(nList.item(i).getParentNode().getNodeName().equals("result")){
					types.add(nList.item(i).getTextContent());
				}
			}
		}
		
		return new Place(formatted_address, lat, lon, name, place_id, rating,
				types, urlGoogle, vicinity, website);
	}

	/**
	 * Method to obtain place ids from a given URL (Max place ids for a search
	 * is 60)
	 * 
	 * @param url
	 *            Formated URL which is compatible for Google Places API
	 * @return A list of Strings containing place ids
	 */
	private List<String> search(String url) {
		Document doc = null;
		List<String> placeIds = new ArrayList<String>();
		doc = sendApiRequest(url);

		NodeList nList = doc.getElementsByTagName("place_id");
		for (int i = 0; i < nList.getLength(); i++) {
			placeIds.add(nList.item(i).getTextContent());
		}

		/*
		 * A total of 20 results are given at a time hence the need to query for
		 * the other pages (if any) using the given next_page_token
		 * next_page_token is not valid for a small period of time hence
		 * multiple query attempts are made until token is valid
		 */

		boolean hasTkn = false;
		String curTkn = null;
		while (doc.getElementsByTagName("next_page_token").getLength() != 0 || hasTkn == true) {
			if (hasTkn == false) {
				curTkn = doc.getElementsByTagName("next_page_token").item(0).getTextContent();
			}
			hasTkn = true;

			String tknUrl = url + "&pagetoken=" + curTkn;
			doc = sendApiRequest(tknUrl);

			nList = doc.getElementsByTagName("place_id");
			for (int i = 0; i < nList.getLength(); i++) {
				placeIds.add(nList.item(i).getTextContent());
			}

			if (!doc.getElementsByTagName("status").item(0).getTextContent().equals("INVALID_REQUEST")) {
				hasTkn = false;
			}
		}
		return placeIds;
	}

	/*
	 * Formats parameters into a URL formatted String and merges it with the
	 * given url
	 */
	private String buildUrl(String tempUrl, HashMap<String, String> params) {
		String url = tempUrl;
		if (params != null) {
			url += "&";
		}

		StringBuilder sb = new StringBuilder();
		for (HashMap.Entry<String, String> e : params.entrySet()) {
			if (sb.length() > 0) {
				sb.append('&');
			}
			try {
				sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=')
						.append(URLEncoder.encode(e.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return (url + sb + "&key=" + this.key).replaceAll("%2C", ",");
	}

	/*
	 * Submits API request and returns it in the format of a XML document
	 */
	private Document sendApiRequest(String url) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private boolean postToDB(List<Place> placeDetails) {
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			
			for(Place place : placeDetails){
				
				ps = con.prepareStatement("INSERT INTO places (place_id,lon,lat,name,rating,website,formatted_address,url,vicinity) VALUES(?,?,?,?,?,?,?,?,?)"
						+ "RETURNING place_id,lon,lat,name,rating,website,formatted_address,url,vicinity;"); 
				System.out.println(place.getLon()+"               "+place.getLat());
				ps.setString(1,place.getPlace_id()); 
	            ps.setFloat(2,place.getLon()); 
	            ps.setFloat(3,place.getLat()); 
	            ps.setString(4,place.getName()); 
	            ps.setFloat(5,place.getRating()); 
	            ps.setString(6,place.getWebsite());
	            ps.setString(7,place.getFormatted_address()); 
	            ps.setString(8,place.getUrl()); 
	            ps.setString(9,place.getVicinity()); 
	          	ps.executeQuery();
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