package objects;

import java.util.Date;

import com.google.gson.JsonObject;

public class Event {
	private String city;
	private String type;
	private String name;
	private Date date;
	private String url;
	private boolean soldOut;
	private JsonObject json;

	public Event(String city, String name, Date date, String url) {
		this.name = name;
		this.date = date;
		this.url = url;
		this.city = city;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSoldOut(boolean soldout) {
		this.soldOut = soldout;
	}

	public void setJson(JsonObject jo) {
		this.json = jo;
	}

	public String getType() {
		return this.type;
	}

	public String getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public Date getDate() {
		return this.date;
	}

	public String getUrl() {
		return this.url;
	}

	public boolean getSoldOut() {
		return this.soldOut;
	}

	public JsonObject getJson() {
		return this.json;
	}

	public String toString() {
		return "City: " + this.city + " Event :" + name + " on" + this.date + " Soldout:" + this.soldOut + " URL: "
				+ this.url;
	}

}
