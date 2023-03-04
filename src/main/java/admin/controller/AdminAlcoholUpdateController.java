package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import alcohol.model.AlCateBean;
import alcohol.model.AlCateDao;
import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;

@Controller
public class AdminAlcoholUpdateController {
	
	private final String command = "/updateAlcohol.ad";
	private String getPage = "/updateAlcoholForm";
	private String gotoPage = "redirect:/alcoholList.ad";
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private AlCateDao alCateDao;
	
	@Autowired
	ServletContext application;
	
	//1 폼 채워넣기 작업
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String update(@RequestParam("num") String num, Model model) {
		System.out.println("AdminAlcoholUpdateController");
		//System.out.println("num "+num);
		
		AlcoholBean alcohol = alcoholDao.getAlcoholByNum(num);
		
		//카테고리 가져오기
		List<AlCateBean> lists = new ArrayList<AlCateBean>();
		lists = alCateDao.getAllAlCate();
		
		model.addAttribute("alcohol", alcohol);
		model.addAttribute("lists", lists);
		
		return getPage;
	}
	
	//2 수정 작업
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String update(AlcoholBean alcohol,
						@RequestParam("originImg") String originImg,
						@RequestParam("originImg2") String originImg2) {
		
		//1 기존 이미지 삭제
		String path = application.getRealPath("/resources");
		//System.out.println(path);
		File delFile = new File(path+"/"+originImg);
		File delFile2 = new File(path+"/"+originImg2);
		delFile.delete();
		delFile2.delete();
		
		//2 새로운 이미지 등록
		MultipartFile multi = alcohol.getUpload();
		MultipartFile multi2 = alcohol.getUpload2();
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
	
		
		alcoholDao.updateAlcohol(alcohol);
		
		return gotoPage;
	}

}





















