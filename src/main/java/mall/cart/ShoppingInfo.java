package mall.cart;

public class ShoppingInfo {
	private int num;
	private String name;
    private int price;
    private int point;
    private int qty;
    private String image;
    private int delivery;
    private int pointAmount;
    private int priceAmount; 
    //¹ÚÀÌ¶û
    private String id;
    private String orderdate;
    private String orderState;
    
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
	public ShoppingInfo() {
		super();
	}
	public ShoppingInfo(int num, String name, int price, int point, int qty, String image, int delivery,
			int pointAmount, int priceAmount) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.point = point;
		this.qty = qty;
		this.image = image;
		this.delivery = delivery;
		this.pointAmount = pointAmount;
		this.priceAmount = priceAmount;

	}
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
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
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public int getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
	public int getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(int priceAmount) {
		this.priceAmount = priceAmount;
	}

    
	

    
}
