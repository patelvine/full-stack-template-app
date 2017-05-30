package objects;
/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class location {
	private String location;

	/**
	 * @param location - location of stores
	 */
	public location(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "location [location=" + location + "]";
	}

}
