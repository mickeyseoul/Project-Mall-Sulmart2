package qna.controller;

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

import qna.model.QnaBean;
import qna.model.QnaDao;
import utility.Paging;

@Controller
public class QnaListController {

	private final String command = "/list.qna";
	private String getPage = "/list";

	@Autowired
	private QnaDao qnaDao;

	@RequestMapping(command)
	public String list(Model model, HttpServletRequest request,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber) {

		System.out.println("QnaListController : "+whatColumn+", "+keyword+", "+pageNumber);

		Map<String,String> map = new HashMap<String,String>(); 
		map.put("whatColumn", whatColumn); 
		map.put("keyword", "%"+keyword+"%");

		int totalCount = qnaDao.getTotalCount(map);
		//System.out.println("request.getRequestURI() "+request.getRequestURI());
		String url = request.getContextPath() + command; 
		Paging pageInfo = new Paging(pageNumber, null, totalCount, url, whatColumn, keyword, null);

		List<QnaBean> lists = new ArrayList<QnaBean>(); 
		lists = qnaDao.getAllData(pageInfo,map);

		model.addAttribute("lists", lists);
		model.addAttribute("pageInfo", pageInfo);

		return getPage;
	}
}
