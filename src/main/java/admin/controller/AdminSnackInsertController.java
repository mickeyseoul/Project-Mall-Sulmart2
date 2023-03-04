package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import alcohol.model.AlCateBean;
import alcohol.model.AlCateDao;
import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import alcohol.model.SnCateBean;
import alcohol.model.SnCateDao;
import utility.Paging;

@Controller
public class AdminSnackInsertController {
	
	private final String command = "/insertSnack.ad";
	private String gotoPage = "redirect:/snackList.ad";
	private String getPage = "/snackList";
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	private AlCateDao alCateDao;
	
	@Autowired
	private SnCateDao snCateDao;
	
	@RequestMapping(command)
	public String insert(@RequestParam("num") String num, Model model){
		
		alcoholDao.updateSnackAppr(num);
		
		return gotoPage;
		
		/*
		if(result.hasErrors()) {
			
			/*
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html;charset=UTF-8"); //내보내는 데이터의 한글처리

			pw.println("<script>");
			pw.println("alert('카테고리를 선택해 주세요');");
			pw.println("</script>");
			pw.flush(); // 안쓰면 안뜸
			*/ //쓰면 리턴 페이지가 안나옴
			
			//검색어
		/*	Map<String, String> map = new HashMap<String, String>();
			map.put("whatColumn", whatColumn);
			map.put("keyword", "%"+keyword+"%");
			//System.out.println("whatColumn "+whatColumn);
			//System.out.println("keyword "+keyword);
			
			//페이징
			int totalCount = alcoholDao.getTotalCount2(map);
			String url = request.getContextPath()+"/"+command;
			
			Paging pageInfo = new Paging(pageNumber,"5",totalCount,url,whatColumn,keyword,null);
			
			//주류 리스트 가져오기
			List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
			lists = alcoholDao.getAllSnack(map,pageInfo);
			
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
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("totalCount", totalCount);
			
			return getPage;
			
		}
		
		System.out.println("AdminAlcoholInsertController");
		System.out.println(alcohol.getUpload());
		System.out.println(alcohol.getImage());
		
		//파일 올리기
		String path = application.getRealPath("/resources");
		System.out.println(path);
		
		MultipartFile multi = alcohol.getUpload(); //파일 정보
		MultipartFile multi2 = alcohol.getUpload2(); //파일 정보
		
		File file = new File(path+"/"+multi.getOriginalFilename());
		File file2 = new File(path+"/"+multi2.getOriginalFilename());
		
		try {
			multi.transferTo(file);
			multi2.transferTo(file2);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		alcoholDao.insertSnack(alcohol);
		*/
	}

}
