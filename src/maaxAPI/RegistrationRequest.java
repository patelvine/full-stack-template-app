package maaxAPI;

public class RegistrationRequest {
	private String name;
	private String email;
	private String phoneNumber;
	private float locations;
	private String contactName;
	private String industry;
	private float employees;
	private String contactTime;
	private String locationPrimary;
	private String positionOfApplicant;
	
	public RegistrationRequest(String name, String email, String phoneNumber, float locations, String contactName,
			String industry, float employees, String contactTime, String locationPrimary, String positionOfApplicant) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.locations = locations;
		this.contactName = contactName;
		this.industry = industry;
		this.employees = employees;
		this.contactTime = contactTime;
		this.locationPrimary = locationPrimary;
		this.positionOfApplicant = positionOfApplicant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getLocations() {
		return locations;
	}

	public void setLocations(float locations) {
		this.locations = locations;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public float getEmployees() {
		return employees;
	}

	public void setEmployees(float employees) {
		this.employees = employees;
	}

	public String getContactTime() {
		return contactTime;
	}

	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}

	public String getLocationPrimary() {
		return locationPrimary;
	}

	public void setLocationPrimary(String locationPrimary) {
		this.locationPrimary = locationPrimary;
	}

	public String getPositionOfApplicant() {
		return positionOfApplicant;
	}

	public void setPositionOfApplicant(String positionOfApplicant) {
		this.positionOfApplicant = positionOfApplicant;
	}
}