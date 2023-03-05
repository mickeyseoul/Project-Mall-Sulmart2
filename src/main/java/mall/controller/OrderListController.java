package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;
import member.model.MemberBean;
import order.model.OrderBean;
import order.model.OrderDao;

@Controller
public class OrderListController {

	//�ٷΰ��� Ŭ���ϸ�  ->���� �������� ��Ʈ�ѷ� ->orderlist.jsp�� �̵�
	private final String command="/orderList.mall";
	private final String getPage="/orderList";
	private String gotoPage ="redirect:/odlist.mall";
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private AlcoholDao alcoholDao;
	
	@RequestMapping(command)
	public String add( //AlcoholBean alcohol,
						@RequestParam(value="num",required = true) int num,
							@RequestParam(value="orderqty",required = true) int orderqty,
								@RequestParam(value="pageNumber",required = false) String pageNumber,
								HttpSession session,
								HttpServletResponse response) throws IOException {//setNum, setOderqty
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		System.out.println("바로결제num : "+num);
		System.out.println("바로결제Orderqty : "+orderqty); //디테일에서 가져오기
		
		//로그인안해도 상세보기 들어갈수있지만 주문은 로그인을 해야 들어갈수있게 만들고 싶다. 
		if(session.getAttribute("loginInfo") == null) { //로그인 안했으면 
			session.setAttribute("destination", "redirect:/detail.al?num="+num+"&pageNumber="+pageNumber); 
			//넘어오는 값이 있어야 해서 상세보기로 가기! 번호와 페이지넘버를 넘겨줘야 이전의 상세보기로 간다.
			return "redirect:/login.mem";
		}
		else { //로그인 했으면
			
			AlcoholBean alcohol = alcoholDao.getAlcoholByNum(String.valueOf(num));
			//주문내역 주문디테일에 넣기전에 수량이 많으면 경고하기!
			if(orderqty>Integer.valueOf(alcohol.getStock())) {
				writer.println("<script> alert('주문수량이 재고보다 많습니다.(재고:"+alcohol.getStock()+"개)'); history.go(-1); </script>");
				writer.flush();
				return "redirect:/detail.al";
			}
			
			//*번상품 *개 담을 장바구니가 필요, + 하나의 장바구니에 여러상품 담아야한다.하나만들면 계속 가지고 다닐거다.
			MyCartList mycart = (MyCartList) session.getAttribute("mycart");
			System.out.println("mycart:"+mycart);
			
			if(mycart == null) {
				mycart = new MyCartList();
			}
	
			mycart.addOrder(num, orderqty);			
			
			session.setAttribute("mycart", mycart);
			
			return gotoPage;
		}
		
		
		
		
	}
	
}

