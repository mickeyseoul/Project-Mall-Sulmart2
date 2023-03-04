package member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;
import utility.Paging;

@Controller
public class MemberSellerListController {
	
	public final String command="memberSellerList.mem";
	public String getPage = "/memberSellerList";

	@Autowired
	private MemberDao memberDao;

	@RequestMapping(command)
	public ModelAndView list(
			@RequestParam(value="whatColumn",required = false)String whatColumn,
			@RequestParam(value="keyword",required = false)String keyword,
			@RequestParam(value="pageNumber",required = false)String pageNumber,
			HttpServletRequest request, HttpSession session) {

		ModelAndView mav=new ModelAndView();
		
		//System.out.println(">>>whatColumn"+whatColumn);
		//System.out.println(">>>keyword"+keyword);
		//System.out.println(">>>pageNumber"+pageNumber);

		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword","%"+keyword+"%");

		int totalCount = memberDao.getSellerTotalCount(map);
		String url=request.getContextPath()+"/"+command;
		
		//System.out.println("totalCount"+totalCount);
		
		Paging pageInfo = new Paging(pageNumber,null, totalCount, url, whatColumn, keyword, null);
		
		
		List<MemberBean>lists = new ArrayList<MemberBean>();
		lists=memberDao.getAllSeller(pageInfo,map);
		
		System.out.println("lists.size() "+lists.size());
		
		
		/* 0929 */
		List<Integer>lists2 = new ArrayList<Integer>();
		for(MemberBean x : lists) {
			int count = memberDao.getSaleItems(x.getId());
			lists2.add(count);
		}
		
		System.out.println("lists2.size() "+lists2.size());
		
		
		for(int i=0;i<lists.size();i++) {
			lists.get(i).setSalecount(lists2.get(i));
		}
		

		
		mav.addObject("lists",lists);
		mav.addObject("pageInfo",pageInfo);
		mav.addObject("lists2",lists2);
		
		
		mav.setViewName(getPage);

		
		//System.out.println("mav"+mav);
		return mav;

	}


}
