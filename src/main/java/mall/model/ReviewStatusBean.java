package mall.model;

public class ReviewStatusBean {
	private int possible;
	private int complete;

	public int getPossible() {
		return possible;
	}

	public void setPossible(int possible) {
		this.possible = possible;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "ReviewStatusDTO [possible=" + possible + ", complete=" + complete + "]";
	}

}
