package mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import mall.cart.MyCartList;

@Controller
public class CartDeleteController {
	
	private final String command ="cartDelete.mall";
	private String gotoPage ="redirect:/list.mall";
	
	@Autowired
	AlcoholDao alcoholDao;
	
	//상세보기.jsp에서 cartDelete.mall , 상품번호, 페이지번호.
	@RequestMapping(command)
	public String delete(@RequestParam(value="num",required = true) int num,
						@RequestParam(value="pageNumber",required = false) String pageNumber,
						HttpSession session)  { //setNum, setOderqty

		System.out.println("cartDelete.mall num:"+num);
			

		 
			MyCartList mycart = (MyCartList) session.getAttribute("mycart");
			System.out.println("Edit mycart:"+mycart);
			
//			if(mycart == null) {
//				mycart = new MyCartList();
//			}
			AlcoholBean alcohol = alcoholDao.getAlcoholByNum(String.valueOf(num));
			
// ㅠㅠ			alcoholDao.deleteAlcohol(String.valueOf(num));
			mycart.removeProduct(num,alcohol);
			
			session.setAttribute("mycart", mycart);
			

			return gotoPage;
	}
}