package mall.cart;

import java.util.HashMap;
import java.util.Map;

import alcohol.model.AlcoholBean;

public class MyCartList { //장바구니
	
	private Map<Integer,Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
	}

	public void addOrder(int num, int oqty) {
		
		if(orderlists.containsKey(num)) {
			int orgqty=orderlists.get(num); 
			orderlists.put(num, orgqty+oqty);
		}else {
			orderlists.put(num, oqty);
		}
		
	}

	public Map<Integer, Integer> getAllOrderLists() {
		return orderlists;
	}

	public void setEdit(int num, int oqty) {
	
		if(orderlists.containsKey(num)) {
			orderlists.put(num, oqty);  
				//break;
			}
	}

	
	public void removeProduct(int num,AlcoholBean alcohol) {//장바구니의 상품 삭제
		//장바구니  7번 삭제하겠다. 
		//0 : 12번상품 1개주문 지금 담겨있다 =>5개주문
		//1 : 4번상품 3개주문 =>2개주문
		//2 : 7번 3개주문
		
		if(orderlists.containsKey(num)) {
			orderlists.remove(num); //한줄을 삭제해야되는데..
			
			//lists.removeElement(pb); //지금 보고있는 pb상품을 지워라
			}	
	}//removeProduct
	
	public void removeAllProduct() {//장바구니의 상품들 결제하고 나서...모든! 상품 삭제 
				orderlists.clear();
				//lists.removeAllElements();		

	}//removeAllProduct   


	
	
}//끝
	
