 package mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholDao;
import member.model.MemberBean;
import member.model.MemberDao;
import order.model.OrderBean;
import order.model.OrderDao;
import utility.Paging;

@Controller
public class OrderMallController {

	//주문내역클릭하면 order.mall ->여기컨트롤러 ->shopList.jsp로 이동
	private final String command="/order.mall";
	private final String getPage="/shopList";
	private final String gotoPage="redirect:/login.mem";
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(command)
	public String ordermall(//@RequestParam(value="orderid",required = false) String orderid,
							@RequestParam(value="pageNumber", required = false) String pageNumber,
							 HttpServletRequest request,
							HttpSession session, Model model) {
		
		//로그인안하면 하도록
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/order.mall");
			return gotoPage;
		}
		else {			
			MemberBean mb =  (MemberBean) session.getAttribute("loginInfo");
			String memid = mb.getId();
			
			System.out.println("pageNumber:"+pageNumber);
			int totalCount = orderDao.getOrderTotalCount();
			
			String url = request.getContextPath()+ command; //위에 슬래쉬있으니까 안씀. 
			System.out.println("url:"+url);		
			
			Paging pageInfo = new Paging(pageNumber,null,totalCount,url,null,null,null);
		
			List<OrderBean> lists = orderDao.selectOrderMid(pageInfo,memid);
		
			for(OrderBean detail : lists) {
				detail.getMemid();
				detail.getOrderdate();
				detail.getOrderid();
				detail.getOrderState();
			
		//	System.out.println("주문내역-Memid;"+detail.getMemid());
		//	System.out.println("Orderdate;"+detail.getOrderdate());
		//	System.out.println("Orderid;"+detail.getOrderid());
		//	System.out.println("OrderState;"+detail.getOrderState());
			}
			MemberBean mbean = memberDao.getMember(memid);
			int mpoint = mbean.getMpoint();
			System.out.println("mpoint:"+mbean.getMpoint());
			
			model.addAttribute("lists", lists);
			model.addAttribute("pageInfo",pageInfo);
			model.addAttribute("totalCount",totalCount);
			model.addAttribute("mpoint", mpoint);
			return getPage;
		}
	}
}
