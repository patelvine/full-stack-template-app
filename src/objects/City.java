package objects;

public class City {
	private String city;
	private String countryCode;
	private int id;
	private float lat;
	private float lon;

	public City(String city, String countryCode, int id, float lat, float lon) {
		this.city = city;
		this.countryCode = countryCode;
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public int getId() {
		return id;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}

	@Override
	public String toString() {
		return "City [city=" + city + ", countryCode=" + countryCode + ", id=" + id + ", lat=" + lat + ", lon=" + lon
				+ "]";
	}

}
