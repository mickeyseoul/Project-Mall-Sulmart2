package alcohol.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;



@Controller
public class AlcoholInsertController {
	private final String command = "/insert.al";
	private String getPage = "/alcoholInsertForm";
	private final String gotoPage = "redirect:/list.al";

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private AlcoholDao alcoholDao;

	@RequestMapping(value=command, method = RequestMethod.GET)
	public String insert(HttpSession session) {
		
		if(session.getAttribute("loginInfo") == null){ 
			
			session.setAttribute("destination", "redirect:/insert.al");
			
			return "redirect:/loginForm.mem";
		}
		else { 
			return getPage;
			
		}
		
	}

	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView insert(
			@ModelAttribute("alcohol") @Valid AlcoholBean alcohol,
			BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		MultipartFile multi = alcohol.getUpload();
		alcoholDao.productInsert(alcohol); // DB 

		String uploadPath = servletContext.getRealPath("/resources"); 

		File file = new File(uploadPath + "/" + multi.getOriginalFilename());
		
		try {
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mav.setViewName(gotoPage);
		return mav;
	}
	
}
