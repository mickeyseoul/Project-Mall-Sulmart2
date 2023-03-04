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
import alcohol.model.SnCateBean;
import alcohol.model.SnCateDao;
import utility.Paging;

@Controller
public class MallSnackViewController {

	private final String command = "/mallSnackView.mall";
	private String getPage = "/mallSnackView";

	@Autowired
	private AlcoholDao alcoholDao;

	@Autowired
	private SnCateDao snCateDao;

	@RequestMapping(command)
	public String view(
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword,
			Model model, HttpServletRequest request) {

		//검색어
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		System.out.println("whatColumn "+whatColumn);
		System.out.println("keyword "+keyword);

		//페이징
		int totalCount = alcoholDao.getTotalCount2(map);
		String url = request.getContextPath()+"/"+command;

		Paging pageInfo = new Paging(pageNumber,"8",totalCount,url,whatColumn,keyword,null);

		//카테고리
		//건식만
		List<SnCateBean> cate1 = new ArrayList<SnCateBean>();
		cate1 = snCateDao.getSnCate1();
		//습식만
		List<SnCateBean> cate2 = new ArrayList<SnCateBean>();
		cate2 = snCateDao.getSnCate2();
		//cate2만 중복된 것 없이 가져오기
		List<SnCateBean> cate3 = new ArrayList<SnCateBean>();
		cate3 = snCateDao.getSnCate3();
		/*
		System.out.println("cate3.size() "+cate3.size());
		for(SnCateBean x : cate3) {
			System.out.println("x.cate2 "+x.getCate2());
		}
		*/
		String str = "";
		for(int i=0;i<cate3.size()-1;i++) {
			for(int j=i+1;j<cate3.size();j++) {
				if(cate3.get(i).getCate2().equals(cate3.get(j).getCate2())){
					//System.out.println("cate3.get(i) "+cate3.get(i).getCate2());
					//System.out.println("cate3.get(j) "+cate3.get(j).getCate2());
					cate3.remove(j);
					//System.out.println("cate3.size() "+cate3.size());
					j = 0;
					break;
				}
			}//
			//str += cate3.get(i).getCate2();
		}
		//System.out.println("cate3.size() "+cate3.size());
		//System.out.println("str "+str);

		//리스트
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = alcoholDao.getAllSnack(map,pageInfo);

		model.addAttribute("lists", lists);
		model.addAttribute("pageInfo", pageInfo);
		//model.addAttribute("cate1", cate1);
		//model.addAttribute("cate2", cate2);
		model.addAttribute("cate3", cate3);
		model.addAttribute("keyword", keyword);

		return getPage;
	}

}
