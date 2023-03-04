package mall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;

@Controller
public class OdListController {
	private final String command="/odlist.mall";
	private final String getPage="/orderList";
	
	@Autowired
	AlcoholDao alcoholDao;
	
	@RequestMapping(command)
	public String cartList(HttpSession session, 
							Model model) {
				
		MyCartList mycart = (MyCartList) session.getAttribute("mycart");
		Map<Integer,Integer> map = mycart.getAllOrderLists();
		
		Set<Integer> keylist = map.keySet();
		System.out.println("keylist:"+keylist);
		
		List<ShoppingInfo> shopLists = new ArrayList<ShoppingInfo>();
		int totalPriceAmount = 0;
		int totalPointAmount = 0;
		int finalPrice= 0 ;
		int deliveryCost =0;
		
		for(Integer num : keylist) {
			AlcoholBean alcohol = alcoholDao.getAlcoholByNum(String.valueOf(num)); //bean에서 int인데 스트링으로 넘기기
			
			ShoppingInfo shopInfo = new ShoppingInfo();
			shopInfo.setNum(num);
			shopInfo.setName(alcohol.getName());
			shopInfo.setPrice(Integer.valueOf(alcohol.getPrice()));
			shopInfo.setPoint(Integer.valueOf(alcohol.getPoint()));
			shopInfo.setQty(map.get(num));
			shopInfo.setImage(alcohol.getImage());
			shopInfo.setDelivery(Integer.valueOf(alcohol.getDelivery()));  
			
			int pointAmount = Integer.valueOf(alcohol.getPoint())*map.get(num);
			shopInfo.setPointAmount(pointAmount);
			
			int priceAmount = Integer.valueOf(alcohol.getPrice())*map.get(num);
			shopInfo.setPriceAmount(priceAmount);
			 
			totalPriceAmount += priceAmount;
			totalPointAmount += pointAmount;
			
			deliveryCost = Integer.valueOf(alcohol.getDelivery()); 
			if(totalPriceAmount >= 30000 ) {
				deliveryCost = 0;
			}else {
				deliveryCost = 3000;
			}
			
			finalPrice = totalPriceAmount+deliveryCost; //포인트로 빼고싶으면! -totalPointAmount
			shopLists.add(shopInfo);
		}
		
		model.addAttribute("shopLists", shopLists);
		model.addAttribute("totalPriceAmount", totalPriceAmount);
		model.addAttribute("totalPointAmount", totalPointAmount);
		model.addAttribute("finalPrice", finalPrice);
		model.addAttribute("deliveryCost", deliveryCost);
		
		return getPage;
		
}
}
