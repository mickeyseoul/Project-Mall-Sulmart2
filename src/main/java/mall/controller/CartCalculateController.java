package mall.controller;

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
import member.model.MemberBean;
import member.model.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetailBean;
import orderdetail.model.OrderDetailDao;

@Controller
public class CartCalculateController {

	private final String command ="calculate.mall";
	private final String gotoPage ="redirect:/order.mall";  //redirect:/list.prd"; //결제완료후에 사용자메인페이지로? 일단주문내역으로 주문번호 가지고 가도록?
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@RequestMapping(command)
	public String calculate(HttpSession session, Model model) {
		
		MyCartList mycart = (MyCartList) session.getAttribute("mycart");
		
		Map<Integer,Integer> map = mycart.getAllOrderLists();
		
		Set<Integer> keylist = map.keySet();
		System.out.println("calculate keylist:"+keylist);
		
		MemberBean member= (MemberBean) session.getAttribute("loginInfo");
		System.out.println("calulate주문자:"+member.getId());
		orderDao.insertData(member.getId()); //회원아이디가 주문자, 몇개를 주문해도 이 주문자로!
		
		int orderid = orderDao.getMaxOrderid(); //최근 장바구니에 넣고 주문했으면 가장 큰 id가짐.
		System.out.println("가장 큰 orderid:"+orderid);
		
		for(Integer num : keylist) {
			OrderDetailBean orderdetail = new OrderDetailBean();
			orderdetail.setOrderid(orderid); //주문번호이다 odid는 시퀀스일뿐
			orderdetail.setNum(num);
			orderdetail.setQty(map.get(num));
			
			System.out.println("결제하기-orderid;"+orderdetail.getOrderid());
			System.out.println("num;"+orderdetail.getNum());
			System.out.println("Qty;"+orderdetail.getQty());
			
		orderDetailDao.insertData(orderdetail); //위에 집어넣은 값들 가지고 오더디테일에 넣을거임
		
		System.out.println("여기인가");
		
		alcoholDao.updateStock(num,map.get(num)); //재고수량감소
		
		
		System.out.println("!!!!!!");
		
		//가격에 10% 회원포인트 적립
		AlcoholBean alcohol = alcoholDao.getAlcoholByNum(String.valueOf(num));
		
		System.out.println("여기 "+alcohol.getName());
		
		double mpoint = orderdetail.getQty()*Double.valueOf(alcohol.getPrice())*0.01;
		memberDao.updateMpoint(member.getId(),mpoint);
		
		}//for
		
		session.removeAttribute("mycart");
		return gotoPage;
		
	}
	
}
