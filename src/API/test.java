package API;

import java.util.HashMap;

import scrapper.ZScapper;

public class test {

	public static void main(String[] args) {
//		GooglePlacesApi api = new GooglePlacesApi();
//		HashMap<String, String> params = new HashMap<String, String>();
//		//params.put("location","-33.8670522,151.1957362");
//		//params.put("radius","500");
//		params.put("type","restaurant");
//		
//		api.getTextSearch("Restaurants in Wellington", params);
		
		ZScapper z = new ZScapper();
		z.getZFuelPrices();
	}

	
	
}
