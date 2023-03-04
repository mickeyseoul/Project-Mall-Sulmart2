package admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlCateBean;
import alcohol.model.AlCateDao;
import alcohol.model.SnCateBean;
import alcohol.model.SnCateDao;

@Controller
public class AdminCateListController {
	
	private final String command = "/cateList.ad";
	private String getPage = "/cateList";
	
	@Autowired
	private AlCateDao alCateDao;
	
	@Autowired
	private SnCateDao snCateDao;
	
	@RequestMapping(command)
	public String list(Model model) {
		
		List<AlCateBean> allists = new ArrayList<AlCateBean>();
		allists = alCateDao.getAllAlCate();
		
		model.addAttribute("allists" ,allists);
		
		List<SnCateBean> snlists = new ArrayList<SnCateBean>();
		snlists = snCateDao.getAllSnCate();
		
		model.addAttribute("snlists" ,snlists);
		
		
		return getPage;
	}

}
