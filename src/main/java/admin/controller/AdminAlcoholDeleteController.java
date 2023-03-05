package admin.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;

@Controller
public class AdminAlcoholDeleteController {
	
	private final String command = "/deleteAlcohol.ad";
	private String gotoPage = "redirect:/alcoholList.ad";
	
	@Autowired
	private AlcoholDao alcoholDao;
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping(command)
	public String delete(@RequestParam("num") String num) {
		
		AlcoholBean snack = alcoholDao.getAlcoholByNum(num);
		String path = application.getRealPath("/resources/images/alcohol");
		File delFile = new File(path+"/"+snack.getImage());
		File delFile2 = new File(path+"/"+snack.getContentImage());
		delFile.delete();
		delFile2.delete();
		
		alcoholDao.deleteAlcohol(num);
		return gotoPage;
	}

}
