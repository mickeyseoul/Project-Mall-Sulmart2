package mall.model;

public class HeartListBean {
	private String name;
	private Integer price;
	private String contentimage;
	private Integer prod_num;
	private Integer mem_num;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getContentimage() {
		return contentimage;
	}

	public void setContentimage(String contentimage) {
		this.contentimage = contentimage;
	}

	public Integer getProd_num() {
		return prod_num;
	}

	public void setProd_num(Integer prod_num) {
		this.prod_num = prod_num;
	}

	public Integer getMem_num() {
		return mem_num;
	}

	public void setMem_num(Integer mem_num) {
		this.mem_num = mem_num;
	}

	@Override
	public String toString() {
		return "HeartListBean [name=" + name + ", price=" + price + ", contentimage=" + contentimage + ", prod_num="
				+ prod_num + ", mem_num=" + mem_num + "]";
	}

}
