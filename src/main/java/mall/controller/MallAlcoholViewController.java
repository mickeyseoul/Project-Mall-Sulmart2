package mall.controller;

import java.util.ArrayList;
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
public class MallAlcoholViewController {

	private final String command = "/mallAlcoholView.mall";
	private String getPage = "/mallAlcoholView";

	@Autowired
	private AlcoholDao alcoholDao;

	@Autowired
	private AlCateDao alCateDao;

	@RequestMapping(command)
	public String view(
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword,
			Model model, HttpServletRequest request) {

		//
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		System.out.println("whatColumn "+whatColumn);
		System.out.println("keyword "+keyword);

		//
		int totalCount = alcoholDao.getTotalCount1(map);
		String url = request.getContextPath()+"/"+command;
	
		Paging pageInfo = new Paging(pageNumber,"8",totalCount,url,whatColumn,keyword,null);

		//
		List<AlCateBean> catelists = new ArrayList<AlCateBean>();
		catelists = alCateDao.getAllAlCate();

		//
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = alcoholDao.getAllAlcohol(map,pageInfo);

		model.addAttribute("lists", lists);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("catelists", catelists);
		model.addAttribute("keyword", keyword);

		return getPage;
	}

}
