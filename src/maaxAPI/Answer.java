package maaxAPI;

public class Answer{
	private String questionId;
	private String result;
	
	public Answer(String questionId, String result) {
		super();
		this.questionId = questionId;
		this.result = result;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
