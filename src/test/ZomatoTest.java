package test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import API.ZomatoApi;

public class ZomatoTest {

	private String testingUrl = "https://developers.zomato.com/api/v2.1/cities?q=auckland%2C%20nz";
	private ZomatoApi zApi;
	
	public ZomatoTest() throws ParseException {
		ZomatoApi zApi = new ZomatoApi();
	}

	@Test // Pass - description
	public void NameHere() throws ParseException {
		String url = zApi.buildSearchURL(-41.296797, 174.776093, 2000);
		zApi.setupConnection(url);
		assertTrue(zApi.getJsonObjectArrayList().size() > 0);
	}
}
