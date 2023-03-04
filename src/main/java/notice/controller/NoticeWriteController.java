package notice.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeWriteController {

	private final String command = "/write.no";
	private String getPage = "/writeForm";
	private String gotoPage = "redirect:/list.no";

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value=command, method=RequestMethod.GET)
	public String write(HttpSession session) {
		System.out.println("WriteController_GET");

		return getPage;
	}

	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView write(@ModelAttribute("notice") @Valid NoticeBean notice, BindingResult result,
			HttpServletRequest request) {
		System.out.println("WriteController_POST");
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			System.out.println("hasErrors : "+result.hasErrors());
			System.out.println("getErrorCount : "+result.getErrorCount());
			System.out.println("getAllErrors : "+result.getAllErrors());
			mav.setViewName(getPage);
			return mav;
		}
		
		MultipartFile multi = notice.getUpload();
		System.out.println("multi.getName():"+multi.getName());
		System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
		System.out.println("product.getImage():"+notice.getImage());
		
		notice.setReg_date(new Timestamp(System.currentTimeMillis())); 
		
		System.out.println("insert 1");
		noticeDao.insertData(notice);
		System.out.println("insert 4");
		
		String uploadPath = servletContext.getRealPath("/resources/notice");
		System.out.println("uploadPath:"+uploadPath);
		
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
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
