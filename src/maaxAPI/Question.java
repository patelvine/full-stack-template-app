package maaxAPI;

public class Question {
	private String surveyID;
	private String index;
	private String body;
	private String type;
	private String[] options;
	
	public Question(String surveyID, String index, String body, String type, String[] options) {
		super();
		this.surveyID = surveyID;
		this.index = index;
		this.body = body;
		this.type = type;
		this.options = options;
	}

	public String getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(String surveyID) {
		this.surveyID = surveyID;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}
}