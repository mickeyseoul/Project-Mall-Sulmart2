package mall.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import mall.cart.MyCartList;
import member.model.MemberBean;
import member.model.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetailBean;
import orderdetail.model.OrderDetailDao;

@Controller
public class RefundViewController {
	
	private final String command ="refund.mall";
	private final String getPage="redirect:/order.mall";
	
	//주문과 비슷하지만 장바구니 테이블 데이터 삭제안하고 돈,포인트,재고 가산 ? 포인트 회수! 
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@RequestMapping(command)
	public String refund(@RequestParam(value="orderid",required = false) int orderid,
							HttpSession session, Model model) {
		
		MemberBean member= (MemberBean) session.getAttribute("loginInfo");
		System.out.println("refund 주문자:"+member.getId());

		System.out.println("refund 주문번호:"+orderid);
		//01.주문번호 같으면 주문취소 업데이트
		orderDao.orderCancle(orderid);

		//02.결제했던 재고수량 + 하고 포인트 - 해야돼...
		//orderDao.getOrder(orderid);			
		List<OrderDetailBean> details = orderDetailDao.selectOrderDetail(orderid); //위에 집어넣은 값들 가지고 오더디테일에 넣을거임	
		for(OrderDetailBean detail : details) {
			int anum =detail.getNum();
			detail.getOdid();
			detail.getOrderid();
			detail.getQty();
		
		System.out.println("detail.getNum();"+detail.getNum()+","+anum);
		System.out.println("detail.getOdid();"+detail.getOdid());
		System.out.println("detail.getOrderid();"+detail.getOrderid());
		System.out.println("detail.getQty();"+detail.getQty());
		
		alcoholDao.updateStock2(anum,detail.getQty());
		
		//가격에 10% 회원포인트 마이너스
		AlcoholBean alcohol = alcoholDao.getAlcoholByNum(String.valueOf(anum));
		double mpoint = detail.getQty()*Double.valueOf(alcohol.getPrice())*0.01;
		memberDao.updateMpoint2(member.getId(),mpoint);
		}
		return getPage;		

	}
	
}			
		
