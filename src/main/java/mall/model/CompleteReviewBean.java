package mall.model;

// 마이페이지 작성 완료한 후기
public class CompleteReviewBean {
	private int num;
	private String name;
	private int qty;
	private String image;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CompleteReviewDTO [num=" + num + ", name=" + name + ", qty=" + qty + ", image=" + image + "]";
	}
}
