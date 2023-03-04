package admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlCateDao;
import alcohol.model.SnCateBean;
import alcohol.model.SnCateDao;

@Controller
public class AdminCateInsertController {
	
	private final String command = "/insertAlCate.ad";
	private final String command2 = "/insertSnCate.ad";
	private String gotoPage = "redirect:/cateList.ad";
	
	@Autowired
	private AlCateDao alCateDao;
	
	@Autowired
	private SnCateDao snCateDao;
	
	@RequestMapping(command)
	public String insert(@RequestParam("cate") String cate) {
		
		alCateDao.insertAlCate(cate);
		
		return gotoPage;
	}
	
	@RequestMapping(command2)
	public String insert2(SnCateBean snCateBean) {
		snCateDao.insertSnCate(snCateBean);
		return gotoPage;
	}

}
