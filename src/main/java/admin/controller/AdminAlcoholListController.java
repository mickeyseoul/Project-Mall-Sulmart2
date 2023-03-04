package admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlCateBean;
import alcohol.model.AlCateDao;
import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import utility.Paging;

@Controller
public class AdminAlcoholListController {
	
	private final String command = "/alcoholList.ad";
	private String getPage = "/alcoholList";
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private AlCateDao alCateDao;
	
	@RequestMapping(command)
	public String list(Model model, HttpServletRequest request,
					@RequestParam(value="pageNumber", required = false) String pageNumber,
					@RequestParam(value="whatColumn", required = false) String whatColumn,
					@RequestParam(value="keyword", required = false) String keyword) {
		
		//검색어
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		//System.out.println("whatColumn "+whatColumn);
		//System.out.println("keyword "+keyword);
		
		//페이징
		int totalCount = alcoholDao.getTotalCount1(map);
		String url = request.getContextPath()+"/"+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalCount,url,whatColumn,keyword,null);
		
		//주류 리스트 가져오기
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = alcoholDao.getAllAlcohol(map,pageInfo);
		
		//주류 카테고리 가져오기
		List<AlCateBean> lists2 = new ArrayList<AlCateBean>();
		lists2 = alCateDao.getAllAlCate();
		
		model.addAttribute("lists", lists);
		model.addAttribute("lists2", lists2);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("totalCount", totalCount);
		
		return getPage;
	}

}
