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
import alcohol.model.SnCateBean;
import alcohol.model.SnCateDao;
import utility.Paging;

@Controller
public class AdminSnackListController {

	private final String command = "/snackList.ad";
	private String getPage = "/snackList";

	@Autowired
	private AlcoholDao alcoholDao;

	@Autowired
	private SnCateDao snCateDao;

	@RequestMapping(command)
	public String list(Model model, HttpServletRequest request,
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword) {

		/* 승인 대기 리스트 */
		//주류 리스트 가져오기
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = alcoholDao.getAllSnackD(); /* 변경 */

		//카테고리 작업
		List<SnCateBean> lists2 = new ArrayList<SnCateBean>();
		lists2 = snCateDao.getAllSnCate();

		List<AlcoholBean> lists3 = new ArrayList<AlcoholBean>();
		for(SnCateBean x : lists2) {
			AlcoholBean snCate = new AlcoholBean();
			String category = "";
			category += x.getCate1()+"-"+x.getCate2();
			snCate.setCategory(category);
			lists3.add(snCate);
		}


		model.addAttribute("lists", lists);
		model.addAttribute("lists3", lists3);	
		

		/* 안주 상품 리스트 */
		//검색어
		Map<String, String> mapA = new HashMap<String, String>();
		mapA.put("whatColumn", whatColumn);
		mapA.put("keyword", "%"+keyword+"%");
		//System.out.println("whatColumn "+whatColumn);
		//System.out.println("keyword "+keyword);

		//페이징
		int totalCountA = alcoholDao.getTotalCount2(mapA); /* 변경 appr=1 가져오기 */
		String urlA = request.getContextPath()+"/"+command;

		Paging pageInfoA = new Paging(pageNumber,"4",totalCountA,urlA,whatColumn,keyword,null);

		//주류 리스트 가져오기
		List<AlcoholBean> listsA = new ArrayList<AlcoholBean>();
		listsA = alcoholDao.getAllSnack(mapA,pageInfoA); /* 변경 */

		//카테고리 작업
		/*List<SnCateBean> lists2A = new ArrayList<SnCateBean>();
		lists2A = snCateDao.getAllSnCate();

		List<AlcoholBean> lists3A = new ArrayList<AlcoholBean>();
		for(SnCateBean x : lists2A) {
			AlcoholBean snCate = new AlcoholBean();
			String category = "";
			category += x.getCate1()+"-"+x.getCate2();
			snCate.setCategory(category);
			lists3.add(snCate);
		}*/

		//0925 유통기한 약2달 전 알림 추가
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String date = format.format(currentTime);
		//System.out.println("date "+ date);
		String year = date.substring(0, 4);
		int month = Integer.valueOf(date.substring(5, 7));
		//System.out.println("year "+year);
		//System.out.println("month "+month);

		for(AlcoholBean x : listsA) {

			String exp_date = x.getExp_date();
			System.out.println("exp_date "+exp_date);
			String expYear = exp_date.substring(0, 4);
			int expMonth = Integer.valueOf(exp_date.substring(5, 7));

			if(year.equals(expYear)) {

				if(month-expMonth == 0 || expMonth-month == 1 || expMonth-month == 2) {
					x.setFlag(true);
				}

			}
		}
		//


		model.addAttribute("listsA", listsA);
		//model.addAttribute("lists3A", lists3A);
		model.addAttribute("pageInfoA", pageInfoA);
		model.addAttribute("totalCountA", totalCountA);

		return getPage;
	}

}
