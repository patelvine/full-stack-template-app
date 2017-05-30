package maaxAPI;

public class Result {
	private ProductAction action;
	private float[] data;
	
	public Result(ProductAction action, float[] data) {
		super();
		this.action = action;
		this.data = data;
	}

	public ProductAction getAction() {
		return action;
	}

	public void setAction(ProductAction action) {
		this.action = action;
	}

	public float[] getData() {
		return data;
	}

	public void setData(float[] data) {
		this.data = data;
	}
}
