package objects;

import java.util.ArrayList;

public class Forecast {
	private City city;
	private ArrayList<Weather> forecast = new ArrayList<Weather>();

	public Forecast(City city, ArrayList<Weather> forecast) {
		this.city = city;
		this.forecast = forecast;
	}

	public City getCity() {
		return city;
	}

	public ArrayList<Weather> getForecast() {
		return forecast;
	}

}
