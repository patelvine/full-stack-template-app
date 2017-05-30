package objects;

public class BoundingBox {

	private double minLat;
	private double maxLat;
	private double minLon;
	private double maxLon;

	public BoundingBox(double minLat, double maxLat, double minLon, double maxLon) {
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLon = minLon;
		this.maxLon = maxLon;
	}

	public double getMinLat() {
		return this.minLat;
	}

	public double getMaxLat() {
		return this.maxLat;
	}

	public double getMinLon() {
		return this.minLon;
	}

	public double getMaxLon() {
		return this.maxLon;
	}

	public String toString() {
		return "minLat: " + this.minLat + " maxLat: " + this.maxLat + " minLon: " + this.minLon + " maxLon: "
				+ this.maxLon;
	}
}
