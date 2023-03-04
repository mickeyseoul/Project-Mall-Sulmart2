package alcohol.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class AlcoholBean {
	private String image;
	private String contentImage;
	private MultipartFile upload;
	private MultipartFile upload2;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContentImage() {
		return contentImage;
	}
	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
		this.image = upload.getOriginalFilename();
	}
	public MultipartFile getUpload2() {
		return upload2;
	}
	public void setUpload2(MultipartFile upload) {
		this.upload2 = upload;
		this.contentImage = upload2.getOriginalFilename();
	}	
	
	private String num;	
	@NotEmpty(message = "상품명 누락")
	private String name;	
	private String product;
	private String code;
	@NotEmpty(message = "카테고리 누락")
	private String category;//선택한 카테고리
	private String brand;	
	private String country;	
	private String price;	
	private String point;	
	private String qty; //수량		
	private String stock; //재고수량		
	private String spec; //베스트셀러,스테디셀러		
	private String heart;		
	private String content; //설명			
	private String input_date;	
	private String exp_date; //유통기한		
	private String delivery;
	
	private int orderqty;  //새로만음 주문수량 위해
	private boolean flag; //새로만듬 유통기한 체크위해
	private String memid; //새로만듬 판매자id
	private int appr; //새로만듬 판매자 승인여부
	
	private Integer readcount;
	
	
	public Integer getReadcount() {
		return readcount;
	}
	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
	
	
	public int getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(int orderqty) {
		this.orderqty = orderqty;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public int getAppr() {
		return appr;
	}
	public void setAppr(int appr) {
		this.appr = appr;
	}
	
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getHeart() {
		return heart;
	}
	public void setHeart(String heart) {
		this.heart = heart;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	@Override
	public String toString() {
		return "MyProduct [num=" + num + ", name=" + name + ", code=" + code + ", category=" + category + ", brand="
				+ brand + ", country=" + country + ", price=" + price + ", point=" + point + ", qty=" + qty + ", stock="
				+ stock + ", spec=" + spec + ", heart=" + heart + ", content=" + content + ", image=" + image
				+ ", input_date=" + input_date + ", exp_date=" + exp_date + ", delivery=" + delivery + ", contentimage="
				+ contentImage + "]";
	}
	
	
	
	

}
