package objects;

/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class stores {
	private String store;
	private String type;
	private String email;
	private String name;
	private String city;
	private String street;

	/**
	 * Each store has store name,type,email and owner's name
	 * 
	 * @param store
	 *            - store name
	 * @param type
	 *            - user type
	 * @param email
	 *            - owner's email
	 * @param name
	 *            - owner's name
	 * @param city
	 *            - city
	 * @param street
	 *            - street
	 */
	public stores(String store, String type, String email, String name, String city, String street) {
		this.store = store;
		this.type = type;
		this.email = email;
		this.name = name;
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getStore() {
		return store;
	}

	public String getType() {
		return type;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "stores [store name=" + store + ", User type=" + type + ", email=" + email + ", name=" + name
				+ "Location= " + city + " " + "street]";
	}
}
