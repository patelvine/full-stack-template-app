package objects;

public class PlaceWoeid {
	private String placeName;
	private int woeid;

	public PlaceWoeid(String name, int woeid) {
		this.placeName = name;
		this.woeid = woeid;
	}

	public int getWoeid() {
		return this.woeid;
	}

	public String toString() {
		return "Place : " + this.placeName + " woeid:" + this.woeid;
	}

}
