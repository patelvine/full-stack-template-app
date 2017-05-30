package objects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Weather {
	private String main;
	private String deceiption;
	private float temp;
	private float pressure;// in hPa
	private float humidity;// in percentage
	private float windspeed;
	private float windDeg;
	private float clouds;// in percentage
	private float rain;// in mm for past 3 hour
	private float snow;// in mm for past 3 hour

	private int UTCdate;

	public Weather(String main, String deceiption, float temp, float pressure, float humidity, float windspeed,
			float windDeg, float clouds, float rain, float snow, int uTCdate) {
		this.main = main;
		this.deceiption = deceiption;
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windspeed = windspeed;
		this.windDeg = windDeg;
		this.clouds = clouds;
		this.rain = rain;
		this.snow = snow;
		UTCdate = uTCdate;
	}

	private String getDate() {
		long unixSeconds = (long) this.UTCdate;
		// *1000 is to convert seconds to milliseconds
		Date date = new Date(unixSeconds * 1000L);
		// the format of your date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		// give a timezone reference for formating (see comment at the bottom
		sdf.setTimeZone(TimeZone.getDefault());
		String result = sdf.format(date);

		return result;
	}

	public String getMain() {
		return main;
	}

	public String getDeceiption() {
		return deceiption;
	}

	public float getTemp() {
		return temp;
	}

	public float getPressure() {
		return pressure;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getWindspeed() {
		return windspeed;
	}

	public float getWindDeg() {
		return windDeg;
	}

	public float getClouds() {
		return clouds;
	}

	public float getRain() {
		return rain;
	}

	public float getSnow() {
		return snow;
	}

	public int getUTCdate() {
		return UTCdate;
	}

	@Override
	public String toString() {
		return "Weather [main=" + main + ", deceiption=" + deceiption + ", temp=" + temp + ", pressure=" + pressure
				+ ", humidity=" + humidity + ", windspeed=" + windspeed + ", windDeg=" + windDeg + ", clouds=" + clouds
				+ ", rain=" + rain + ", snow=" + snow + ", date=" + getDate() + "]";
	}
}
