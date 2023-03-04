package mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import utility.Paging;

@Controller
public class RecommandController {
	
	private final String command = "/recommand.mall";
	private String getPage = "/recommand";
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String recommand1(@RequestParam("num") String num, Model model) {
		AlcoholBean product = alcoholDao.getAlcoholByNum(num);
		String cate = product.getCategory();
		
		System.out.println("여기 나옵니까");
		
		String recommand = "";
		if(cate.contains("증류수")) {
			recommand = "탕/찌개";
		}else if(cate.contains("리큐르")) {
			recommand = "육가공품";
		}else if(cate.contains("막걸리")) {
			recommand = "건어물";
		}else if(cate.contains("과실주")) {
			recommand = "유제품";
		}else if(cate.contains("건어물")) {
			recommand = "막걸리";
		}else if(cate.contains("견과류")) {
			recommand = "과실주";
		}else if(cate.contains("유제품")) {
			recommand = "과실주";
		}else if(cate.contains("육가공품")) {
			recommand = "리큐르";
		}else if(cate.contains("탕/찌개")) {
			recommand = "증류수";
		}
		
		System.out.println("recommand "+recommand);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", "category");
		map.put("keyword", "%"+recommand+"%");
		
		int totalcount = alcoholDao.getTotalCount(map);
		Paging pageInfo = new Paging(null,"4",totalcount,null,null,null,null);
		
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = alcoholDao.getAllProduct(map,pageInfo);
		
		model.addAttribute("product", product);
		model.addAttribute("lists", lists);
		
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String recommand() {
		return getPage;
	}

}
