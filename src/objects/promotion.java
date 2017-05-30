package objects;
/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class promotion {
	private String name;
	private String price;
	private String time;
	private String location;
	private int score;
	/**
	 * @param name
	 * @param price
	 * @param time
	 * @param location
	 * @param score
	 */
	public promotion(String name, String price, String time, String location, int score) {
		this.name = name;
		this.price = price;
		this.time = time;
		this.location = location;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getTime() {
		return time;
	}
	public String getLocation() {
		return location;
	}
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return "promotion [name=" + name + ", price=" + price + ", time=" + time + ", location=" + location + ", score="
				+ score + "]";
	}
}
