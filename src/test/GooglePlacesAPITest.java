package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import API.GooglePlacesApi;
import objects.Place;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GooglePlacesAPITest {
	private GooglePlacesApi api;
	HashMap<String, String> params;
	List<Place> list;
	
	public GooglePlacesAPITest(){
		this.api = new GooglePlacesApi();
		this.params = new HashMap<String, String>();
		this.list = new ArrayList<Place>();
	}

	@Test // Pass - Successful Nearby Search API request
	public void NSRequestPass() {
		params = new HashMap<String, String>();
		params.put("type","restaurant");
		this.list = api.getNearbySearch("-33.8670522", "151.1957362","400", params);
		assertTrue(this.list.size() > 0);
	}
	
	@Test // Fail - false API request (invalid Longitude)
	public void NSRequestFail() {
		params = new HashMap<String, String>();
		params.put("type","restaurant");
		this.list = api.getNearbySearch("-33.8670522", "251.1957362","400", params);
		assertTrue(this.list.size() == 0);
	}
	
	@Test // Pass - Successful Text Search API request
	public void trueTsRequest() {
		params = new HashMap<String, String>();
		params.put("type","restaurant");
		this.list = api.getTextSearch("Bars in Wellington New Zealand", params);
		assertTrue(this.list.size() > 0);
	}
}