package maaxAPI;

public class ProductAction {
	private String productName;
	private float listPrice;
	private float discount;
	private String timeStarts;
	private String timeEnds;
	private String[] locations;
	private String image;
	
	public ProductAction(String productName, float listPrice, float discount, String timeStarts, String timeEnds,
			String[] locations, String image) {
		super();
		this.productName = productName;
		this.listPrice = listPrice;
		this.discount = discount;
		this.timeStarts = timeStarts;
		this.timeEnds = timeEnds;
		this.locations = locations;
		this.image = image;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getListPrice() {
		return listPrice;
	}

	public void setListPrice(float listPrice) {
		this.listPrice = listPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getTimeStarts() {
		return timeStarts;
	}

	public void setTimeStarts(String timeStarts) {
		this.timeStarts = timeStarts;
	}

	public String getTimeEnds() {
		return timeEnds;
	}

	public void setTimeEnds(String timeEnds) {
		this.timeEnds = timeEnds;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
