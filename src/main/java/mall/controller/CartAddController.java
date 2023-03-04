package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import mall.cart.MyCartList;

@Controller
public class CartAddController {

	private final String command ="add.mall";
	private String getPage ="/mallList";
	private String gotoPage ="redirect:/list.mall";
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String add(@RequestParam(value="pageNumber",required = false) String pageNumber,
						HttpServletResponse response,
						Model model,
						HttpSession session) throws IOException{

			response.setContentType("text/html; charset=UTF-8");//response 응답을 보내야 한다 //내보내는 데이터에 한글처리
			PrintWriter writer = response.getWriter(); //writer연결다리를 타고 가서 출력	  
		
			if(session.getAttribute("loginInfo") == null) { //로그인 안했으면 
				//session.setAttribute("destination", "redirect:/main.mall"); 
				writer.println("<script> alert('로그인 후 이용가능합니다');  history.go(-1);  </script>");
				writer.flush(); //alert을 내보내라는 뜻 
			return "redirect:/login.mem";
		} else {
				MyCartList mycart = (MyCartList) session.getAttribute("mycart");
				System.out.println("mycart:"+mycart);
				
				if(mycart == null) {
					mycart = new MyCartList();
				}
				
				session.setAttribute("mycart", mycart);

			return gotoPage;
		}
	}
	
	//상세보기.jsp에서 add.mall요청 , 상품번호, 페이지번호, 주문수량-없어서 bean에 만듦.
	@RequestMapping(command)
	public String add(AlcoholBean alcohol,
						@RequestParam(value="pageNumber",required = false) String pageNumber,
						HttpSession session) { //setNum, setOderqty
		System.out.println("alcohol.getNum : "+alcohol.getNum());
		System.out.println("alcohol.getOrderqty : "+alcohol.getOrderqty()); //디테일에서 가져오기
		System.out.println("cartAddCon/image:"+alcohol.getImage()); //파일명 초록.jpg
		
		int num = Integer.valueOf(alcohol.getNum());
		int oqty = alcohol.getOrderqty();
		
	
		//로그인안해도 상세보기 들어갈수있지만 주문은 로그인을 해야 들어갈수있게 만들고 싶다. 
		if(session.getAttribute("loginInfo") == null) { //로그인 안했으면 
			session.setAttribute("destination", "redirect:/detail.al?num="+alcohol.getNum()+"&pageNumber="+pageNumber); 
			//넘어오는 값이 있어야 해서 상세보기로 가기! 번호와 페이지넘버를 넘겨줘야 이전의 상세보기로 간다.
			return "redirect:/login.mem";
		}
		else { //로그인 했으면
			//*번상품 *개 담을 장바구니가 필요, + 하나의 장바구니에 여러상품 담아야한다.하나만들면 계속 가지고 다닐거다.
			MyCartList mycart = (MyCartList) session.getAttribute("mycart");
			System.out.println("mycart:"+mycart);
			
			if(mycart == null) {
				mycart = new MyCartList();
			}
	
			mycart.addOrder(num, oqty);			
			
			session.setAttribute("mycart", mycart);
			return gotoPage;
		}
		
		
	}
	
	
	
}
