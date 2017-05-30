package maaxAPI;

public class FeedItem {
	private String type;
	private String contents;
	
	public FeedItem(String type, String contents) {
		super();
		this.type = type;
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
