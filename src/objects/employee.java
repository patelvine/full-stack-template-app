package objects;

/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class employee {
	private String email;
	private String store;
	private String name;
	private String type;
	private String city;
	private String street;

	/**
	 * Each employee contain an email, store, name and type to defined user
	 * 
	 * @param email
	 *            - employee email
	 * @param store
	 *            - store name
	 * @param name
	 *            - employee name
	 * @param type
	 *            - user type
	 * @param city
	 *            - city
	 * @param street
	 *            - street
	 */
	public employee(String email, String store, String name, String type, String city, String street) {
		this.email = email;
		this.store = store;
		this.name = name;
		this.type = type;
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getEmail() {
		return email;
	}

	public String getStore() {
		return store;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "employee [email=" + email + ", store=" + store + ", name=" + name + ", User type=" + type + "Location= "
				+ city + " " + "street]";
	}

}
