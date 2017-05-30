package objects;

import com.google.gson.JsonObject;

public class Restaurant {
	private String name;
	private int id;
	private String url;
	private String menuURL;
	private JsonObject location;
	private JsonObject json;

	public Restaurant(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void setLocation(JsonObject location) {
		this.location = location;
	}

	public JsonObject getLocation() {
		return this.location;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}

	public void setMenuURL(String url) {
		this.menuURL = url;
	}

	public String getMenuURL() {
		return this.menuURL;
	}

	public void setJson(JsonObject jo) {
		this.json = jo;
	}

	public JsonObject getJson() {
		return this.json;
	}
}