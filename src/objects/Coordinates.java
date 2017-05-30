package objects;

public class Coordinates {
	private final double latitude;
	private final double longitude;
	private String type;

	public Coordinates(double lat, double lon, String type) {
		this.latitude = lat;
		this.longitude = lon;
		this.type = type;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public String getType() {
		return this.type;
	}

	public String toString() {
		return this.type + " : " + this.latitude + "," + this.longitude;
	}

	/**
	 * Convert Degree to Radiant
	 * 
	 * @param deg
	 * @return
	 */
	public double degToRad(double deg) {
		return deg * (Math.PI / 180);
	}

	/**
	 * Convert Radiant to Degree
	 * 
	 * @param rad
	 * @return
	 */
	public double radToDeg(double rad) {
		return (180 * rad) / Math.PI;
	}

	/**
	 * Calculate the bounding box of a coordinate according to distance
	 * 
	 * @param distance
	 * @return
	 */
	public BoundingBox getBoudingBox(double distance) { // http://janmatuschek.de/LatitudeLongitudeBoundingCoordinates
		double R = 6371; // Earth Radius
		double r = distance / R; // angular Radius
		double latInRad = degToRad(this.latitude);
		double minLatRad = latInRad - r;
		double maxLatRad = latInRad + r;

		double latT = Math.asin(Math.sin(latInRad) / Math.cos(r));
		// double thetaLon = Math.acos( (Math.cos(r)-Math.sin(latT) *
		// Math.sin(latInRad))/ (Math.cos(latT) - Math.cos(latInRad)) );
		double thetaLon1 = Math.asin(Math.sin(r) / Math.cos(latInRad));
		double lonInRad = degToRad(this.longitude);
		double minLonRad = lonInRad - thetaLon1;
		double maxLonRad = lonInRad + thetaLon1;
		System.out.println(lonInRad + ">><<" + thetaLon1);

		return new BoundingBox(radToDeg(minLatRad), radToDeg(maxLatRad), radToDeg(minLonRad), radToDeg(maxLonRad));
	}

	public static void main(String[] args) {
		Coordinates c = new Coordinates(41.908779, 12.492457, "point");
		System.out.println(c.getBoudingBox(1).toString());
	}

}
