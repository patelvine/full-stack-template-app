package objects;

import com.google.gson.JsonObject;

public class TrafficIncident {
	private Coordinates coord;
	private String cogestion = "No congestion Infomation";
	private String description;
	private String detour = "No detour Infomation";
	private String endDate = "No endDate Infomation";
	private int incidentId = 0;
	private String lane = "No lane Infomation";
	private String lastModified = "No lastModified Infomation";;
	private boolean roadClosed = false;
	private int severity = 0;
	private String startDate = "No startDate Infomation";;
	private int type = 0;
	private boolean verified = false;
	private JsonObject json;

	public TrafficIncident(Coordinates coord, int incidentId, int severity, int type) {
		this.coord = coord;
		this.incidentId = incidentId;
		this.severity = severity;
		this.type = type;
	}

	public TrafficIncident(Coordinates coord, int incidentId) {
		this.coord = coord;
		this.incidentId = incidentId;
	}

	public void setCongestion(String congestion) {
		if (congestion == null) {
			this.cogestion = "null";
		} else {
			this.cogestion = congestion;
		}

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDetour(String detour) {
		if (detour == null) {
			this.detour = "null";
		} else {
			this.detour = detour;
		}
	}

	public void setEndDate(String date) {
		this.endDate = date;
	}

	public void setLane(String lane) {
		if (lane == null) {
			this.lane = "null";
		} else {
			this.lane = lane;
		}
	}

	public void setLastModified(String lastDate) {
		this.lastModified = lastDate;
	}

	public void setroadClosed(boolean b) {
		this.roadClosed = b;
	}

	public void setSeverity(int s) {
		this.severity = s;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setType(int t) {
		this.type = t;
	}

	public void setVerified(boolean b) {
		this.verified = b;
	}

	public void setJson(JsonObject jo) {
		this.json = jo;
	}

	public Coordinates getCoords() {
		return this.coord;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDetour() {
		return this.detour;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public int getIncidentId() {
		return this.incidentId;
	}

	public String getLane() {
		return this.lane;
	}

	public String getLastModified() {
		return this.lastModified;
	}

	public boolean getRoadClosed() {
		return this.roadClosed;
	}

	public String getSeverity() {
		return this.getSeverity();
	}

	public String getStartDate() {
		return this.startDate;
	}

	public int type() {
		return this.type;
	}

	public boolean getVerified() {
		return this.verified;
	}

	public JsonObject getJson() {
		return this.json;
	}

	public String getSeverityMeaning() {
		switch (this.severity) {
		case 1:
			return "LowImpact";
		case 2:
			return "Minor";
		case 3:
			return "Moderate";
		case 4:
			return "Serious";
		default:
			return "No info";
		}
	}

	public String getTypeMeaning() {
		switch (this.type) {
		case 1:
			return "Accident";
		case 2:
			return "Congestion";
		case 3:
			return "DisabledVehicle";
		case 4:
			return "MassTransit";
		case 5:
			return "Miscellaneous";
		case 6:
			return "Other News";
		case 7:
			return "Planned Event";
		case 8:
			return "Road Hazard";
		case 9:
			return "Construction";
		case 10:
			return "Alert";
		case 11:
			return "Weather";
		default:
			return "No info";
		}
	}

	public String toString() {
		return "At " + this.coord.toString() + "-" + this.cogestion + "-" + this.description + "-" + this.detour + "-"
				+ this.endDate + "-" + this.incidentId + "-" + this.lane + "-" + this.lastModified + "-"
				+ this.roadClosed + "-" + this.getSeverityMeaning() + "-" + this.startDate + "-" + this.getTypeMeaning()
				+ "-" + this.verified;
	}
}
