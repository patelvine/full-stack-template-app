package objects;
/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class product {
	private String product;

	/**
	 * @param product - product of stores
	 */
	public product(String product) {
		this.product = product;
	}

	public String getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "product [product=" + product + "]";
	}
	
}
