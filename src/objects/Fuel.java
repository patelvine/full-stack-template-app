package objects;

/**
 * 
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 *
 */

public class Fuel {
	private String date;
	private float price;
	private String type;
	private String unit;

	/**
	 * 
	 * @param price
	 *            - Fuel price
	 * @param unit
	 *            - Fuel unit of measurement i.e L - Litre
	 * @param type
	 *            - Fuel type (91, Premiuem, Diesel etc)
	 */
	public Fuel(String date, float price, String type, String unit) {
		this.date = date;
		this.price = price;
		this.type = type;
		this.unit = unit;
	}

	public String getDate() {
		return this.date;
	}

	public float getPrice() {
		return this.price;
	}

	public String getType() {
		return this.type;
	}

	public String getUnit() {
		return this.unit;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Fuel fuelObj = (Fuel) obj;
		// wont need all these values. only date and type are relevant
		if (fuelObj.getDate().equals(this.date) && fuelObj.getPrice() == this.price
				&& fuelObj.getType().equals(this.type) && fuelObj.getUnit().equals(this.unit))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Fuel [date=" + date + ", price=" + price + ", type=" + type + ", unit=" + unit + "]";
	}
}