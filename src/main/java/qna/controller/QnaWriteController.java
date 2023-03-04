package qna.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import qna.model.QnaBean;
import qna.model.QnaDao;

@Controller
@SessionAttributes("id")
public class QnaWriteController {
	private final String command = "/write.qna";
	private String getPage = "/writeForm";
	private String gotoPage = "redirect:/list.qna";

	@Autowired
	private QnaDao qnaDao;

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value=command, method=RequestMethod.GET)
	public String write(HttpSession session,HttpServletResponse response) throws IOException {
		System.out.println("WriteController_GET");
		response.setContentType("text/html; charset=UTF-8"); //�븳湲�泥섎━
		PrintWriter writer = response.getWriter();
		// 濡쒓렇�씤�븯硫� loginInfo session�꽕�젙
		if(session.getAttribute("loginInfo") == null) { // 濡쒓렇�씤 �븞�뻽�쑝硫�
			session.setAttribute("destination", "redirect:/write.qna");
			writer.println("<script type='text/javascript'> alert('濡쒓렇�씤�씠 �븘�슂�븳 湲곕뒫�엯�땲�떎.');location.href='login.mem';</script>");
			writer.flush();
			writer.close();
			return null;
		}
		else { // 濡쒓렇�씤�뻽�쑝硫�
			return getPage;
		}
	}

	@ModelAttribute("id")
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView write(@ModelAttribute("qna") @Valid QnaBean qna, BindingResult result,
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

		MultipartFile multi = qna.getUpload();
		System.out.println("multi.getName():"+multi.getName());
		System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
		System.out.println("product.getImage():"+qna.getImage());

		qna.setReg_date(new Timestamp(System.currentTimeMillis())); 

		System.out.println("insert 1");
		qnaDao.insertData(qna);
		System.out.println("insert 4");
		
		/*
		 * UUID uuid = UUID.randomUUID(); String uuidName = uuid.toString()+"_";
		 */
		
		String uploadPath = servletContext.getRealPath("/resources/qna");
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
