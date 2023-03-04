package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import mall.cart.MyCartList;

@Controller
public class CartEditController {

	private final String command ="cartEdit.mall";
	private String gotoPage ="redirect:/list.mall";
	
	//상세보기.jsp에서 add.mall요청 , 상품번호, 페이지번호, 주문수량-없어서 bean에 만듦.
	@RequestMapping(command)
	public String add(	@RequestParam(value="num",required = true) int num,
						@RequestParam(value="oqty",required = true) int oqty,
						@RequestParam(value="pageNumber",required = false) String pageNumber,
						HttpSession session,
						HttpServletResponse response) throws IOException { //setNum, setOderqty

		System.out.println(num);
		System.out.println(oqty); //디테일에서 가져오기
		
		 response.setContentType("text/html; charset=UTF-8");//response 응답을 보내야 한다 //내보내는 데이터에 한글처리
		 PrintWriter writer = response.getWriter(); //writer연결다리를 타고 가서 출력
			MyCartList mycart = (MyCartList) session.getAttribute("mycart");
			System.out.println("Edit mycart:"+mycart);
			
//			if(mycart == null) {
//				mycart = new MyCartList();
//			}
	
			mycart.setEdit(num, oqty);
			
			session.setAttribute("mycart", mycart);
			 /*
			  writer.println("<script>alert('수량이 변경되었습니다.'); history.go(-1);  </script>"); 
			  writer.flush();
			이걸하면 수량변경됬다고 뜨지만 새로고침해야 합계 금액이 변동되는.. 이상한.. 
			 */				
			return gotoPage;
		
	}
	
}
