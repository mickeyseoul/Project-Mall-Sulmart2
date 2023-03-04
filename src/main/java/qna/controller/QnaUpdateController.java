package qna.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
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

import qna.model.QnaBean;
import qna.model.QnaDao;

@Controller
public class QnaUpdateController {
	private final String command ="update.qna";
	private String getPage = "/updateForm";
	private String gotoPage = "redirect:/detail.qna";

	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value=command,method=RequestMethod.GET)
	public String update(Model model, @RequestParam("num") String num,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		System.out.println("QnaUpdateController_GET");
		QnaBean qna = qnaDao.getData(num);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("qna", qna);
		return getPage;
	}

	@RequestMapping(value=command,method=RequestMethod.POST) 
	public String update(
			@ModelAttribute("qna") @Valid QnaBean qna, BindingResult result,
			@RequestParam(value="pageNumber",required=false) String pageNumber,
			@RequestParam(value="originalImg", required = false) String originalImg,
			Model model) {

		System.out.println("QnaUpdateController_POST");
		if(result.hasErrors()) { 
			model.addAttribute("pageNumber",pageNumber);
			return getPage;
		}
		
		//원래 이미지 삭제
		String originalPath = servletContext.getRealPath("/resources/qna/")+originalImg;
		File file1 = new File(originalPath);
		file1.delete();

		//새로운 이미지 등록
		MultipartFile multi = qna.getUpload();
		
		String newPath = servletContext.getRealPath("/resources/qna")+"/"+qna.getImage(); //업로드 위치 설정
		File file2 = new File(newPath); //파일로 만들기
		try {
			multi.transferTo(file2); //파일 업로드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("QnaUpdateController 1");
		qnaDao.updateData(qna);
		System.out.println("QnaUpdateController 4");

		model.addAttribute("num",qna.getNum());
		model.addAttribute("pageNumber",pageNumber);
		return gotoPage;
	}
}
