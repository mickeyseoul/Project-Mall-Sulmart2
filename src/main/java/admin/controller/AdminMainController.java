package admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import alcohol.model.SnCateBean;
import mall.cart.ShoppingInfo;
import member.model.MemberBean;
import order.model.OrderBean;
import order.model.OrderDao;
import utility.Paging;

@Controller
public class AdminMainController {

	private final String command = "/main.ad";
	private String getPage = "/main";

	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private OrderDao orderDao;

	@RequestMapping(command)
	public String main(Model model, HttpServletRequest request, HttpSession session,
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword) {


		MemberBean login = (MemberBean)session.getAttribute("loginInfo");
		String memid = login.getId();
		
		if(memid.equals("admin")) { //관리자
			
			//승인 대기 리스트
			List<AlcoholBean> apprLists = new ArrayList<AlcoholBean>();
			apprLists = alcoholDao.getAllSnackD();
			
			//등록 상품 리스트
			int totalCount = alcoholDao.getTotalCount(null);
			int totalAlcohol = alcoholDao.getTotalCount1(null);
			int totalSnack = alcoholDao.getTotalCount2(null);
			
			model.addAttribute("apprLists", apprLists);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalAlcohol", totalAlcohol);
			model.addAttribute("totalSnack", totalSnack);
			
			
			
			//이번달 주문 조회
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
			Date currentTime = new Date();
			String date = format.format(currentTime);
			String month = date.substring(3, 5);
			String year = date.substring(0, 3);
			String yearMonth = date.substring(0, 5);
			//System.out.println("month"+ month);
			//System.out.println("yearMonth"+ yearMonth);
			String search = yearMonth+"%";
			
			List<OrderBean> orderLists = new ArrayList<OrderBean>();
			orderLists = orderDao.getAllOrders(search);
			
			//지난달 주문 조회
			int lastMonth;
			if(Integer.valueOf(month) == 1) {
				lastMonth = 12;
			}else {
				lastMonth = Integer.valueOf(month)-1;
			}
			String search2 = year+"%"+lastMonth+"___";
			//System.out.println("search2 "+search2); //22/%8___
			
			List<OrderBean> lastLists = new ArrayList<OrderBean>();
			lastLists = orderDao.getAllOrders(search2);
			
			model.addAttribute("month", month);
			model.addAttribute("orderLists", orderLists);
			model.addAttribute("lastMonth", lastMonth);
			model.addAttribute("lastLists", lastLists);
			
			
			//총매출
			List<ShoppingInfo> lists3 = new ArrayList<ShoppingInfo>();
			lists3 = orderDao.getAllOrderByMonth(search);
			
			int totalSaleAmount = 0;
			for(ShoppingInfo x : lists3) {
				totalSaleAmount += x.getPriceAmount();
			}
			
			List<ShoppingInfo> lists4 = new ArrayList<ShoppingInfo>();
			lists4 = orderDao.getAllOrderByMonth(search2);
			
			int lastTotalSaleAmount = 0;
			for(ShoppingInfo x : lists4) {
				lastTotalSaleAmount += x.getPriceAmount();
				//System.out.println("x.getPriceAmount() "+x.getPriceAmount());
			}
			
			model.addAttribute("totalSaleAmount", totalSaleAmount);
			model.addAttribute("lastTotalSaleAmount", lastTotalSaleAmount);

			
		}else{ //판매자

			Map<String, String> map = new HashMap<String, String>();
			map.put("whatColumn", "memid");
			map.put("keyword", "%"+memid+"%");

			//승인 대기 리스트
			List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
			lists = alcoholDao.getAllSnackD();

			List<AlcoholBean> myDlists = new ArrayList<AlcoholBean>();
			for(AlcoholBean x : lists) {

				if(x.getMemid().equals(memid)) {
					myDlists.add(x);
				}
			}

			//등록 상품 리스트
			int totalCountA = alcoholDao.getTotalCount2(map);

			model.addAttribute("myDlists", myDlists);
			model.addAttribute("totalCountA", totalCountA);
		}

		return getPage;

	}

}
