package notice.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeUpdateController {
	private final String command ="update.no";
	private String getPage = "/updateForm";
	private String gotoPage = "redirect:/detail.no";

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value=command,method=RequestMethod.GET)
	public String update(Model model, @RequestParam("num") String num,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		System.out.println("NoticeUpdateController_GET");
		NoticeBean notice = noticeDao.getData(num);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("notice", notice);
		return getPage;
	}

	@RequestMapping(value=command,method=RequestMethod.POST) 
	public ModelAndView update(
			@ModelAttribute("notice") @Valid NoticeBean notice, BindingResult result,
			@RequestParam(value="pageNumber",required=false) String pageNumber,
			@RequestParam(value= "originalImg",required=false) String originalImg,
			HttpServletResponse response) throws IOException {

		System.out.println("NoticeUpdateController_POST");
		ModelAndView mav= new ModelAndView();
		mav.addObject("pageNumber",pageNumber);
		if(result.hasErrors()) { 
			mav.setViewName(getPage);
			return mav;
		}
		
		//원래 이미지 삭제
		String originalPath = servletContext.getRealPath("/resources/notice/")+originalImg;
		File file1 = new File(originalPath);
		file1.delete();

		//새로운 이미지 등록
		MultipartFile multi = notice.getUpload();
		
		String newPath = servletContext.getRealPath("/resources/notice")+"/"+notice.getImage(); //업로드 위치 설정
		File file2 = new File(newPath); //파일로 만들기
		try {
			multi.transferTo(file2); //파일 업로드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("NoticeUpdateController 1");
		noticeDao.updateData(notice);
		System.out.println("NoticeUpdateController 4");

		mav.addObject("pageNumber",pageNumber);
		mav.addObject("num",notice.getNum());
		mav.setViewName(gotoPage);
		return mav;

		
		
	}
}
