package alcohol.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import utility.Paging;

@Controller
public class AlcoholListController {
	private final String command = "/list.al";
	private String getPage = "/alcoholList";
	
	@Autowired
	AlcoholDao alcoholDao;
	
	@RequestMapping(command)
	public ModelAndView list(
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber,
			HttpServletRequest request) {
		
			ModelAndView mav = new ModelAndView();
		
			Map<String, String> map = new HashMap<String, String>();
			map.put("whatColumn", whatColumn);
			map.put("keyword", keyword);
			int totalCount = alcoholDao.getTotalCount(map);
			String url = request.getContextPath() + "/" + command;
			
			Paging pageInfo = new Paging(pageNumber,null, totalCount, url, whatColumn, keyword, null);
			List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
			lists = alcoholDao.getAlcoholList(pageInfo,map);
			
			mav.addObject("lists",lists);
			mav.addObject("pageInfo",pageInfo);
			mav.addObject(getPage);
			
			return mav;
		
	}
		
}
