 package qna.controller;

import java.util.ArrayList;
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

import qna.model.QnaBean;
import qna.model.QnaDao;
import utility.Paging;

@Controller
public class MyQnaController {

	private final String command="/myqna.qna";
	private final String getPage="/myQnaList";
	
	private final String gotoPage="redirect:/login.mem";
	
	@Autowired
	private QnaDao qnaDao;
	
	@RequestMapping(command)
	public String list(@RequestParam(value="pageNumber", required = false) String pageNumber,
						HttpServletRequest request, HttpSession session, Model model) {
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/myqna.qna");
			return gotoPage;
		}
		else {			
			System.out.println("pageNumber:"+pageNumber);
			
			Map<String,String> map = new HashMap<String,String>(); 
			
			int totalCount = qnaDao.getTotalCount(map);
			
			String url = request.getContextPath() + command; 
			Paging pageInfo = new Paging(pageNumber, null, totalCount, url, null, null, null);

			List<QnaBean> lists = new ArrayList<QnaBean>(); 
			lists = qnaDao.getAllData(pageInfo,map);

			model.addAttribute("lists", lists);
			model.addAttribute("pageInfo", pageInfo);

			return getPage;
		}
	}
}
