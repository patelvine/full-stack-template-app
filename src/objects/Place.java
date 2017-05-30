package objects;

import java.util.List;

public class Place {
	private String formatted_address;
	private float lat;
	private float lon;
	private String name;
	private String place_id;
	private float rating;
	private List<String> types;
	private String url;
	private String vicinity;
	private String website;

	public Place(String formatted_address, float lat, float lon, String name, String place_id, float rating,
			List<String> types, String url, String vicinity, String website) {
		this.formatted_address = formatted_address;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.place_id = place_id;
		this.rating = rating;
		this.types = types;
		this.url = url;
		this.vicinity = vicinity;
		this.website = website;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}

	public String getName() {
		return name;
	}

	public String getPlace_id() {
		return place_id;
	}

	public float getRating() {
		return rating;
	}

	public List<String> getTypes() {
		return types;
	}

	public String getUrl() {
		return url;
	}

	public String getVicinity() {
		return vicinity;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return "Place [formatted_address=" + formatted_address + ", lat=" + lat + ", lon=" + lon + ", name=" + name
				+ ", place_id=" + place_id + ", rating=" + rating + ", types=" + types + ", url=" + url + ", vicinity="
				+ vicinity + ", website=" + website + "]";
	}
}