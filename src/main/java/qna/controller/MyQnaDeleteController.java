package qna.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import qna.model.QnaBean;
import qna.model.QnaDao;

@Controller
public class MyQnaDeleteController {
	private final String command="myDelete.qna";
	private String getPage="redirect:/myqna.qna";
	
	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(command)
	public ModelAndView deleteProc(Model model, @RequestParam("num") String num,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();

		QnaBean qna = qnaDao.getData(num);
		
			String deletePath = servletContext.getRealPath("/resources/qna");
			File delFile = new File(deletePath+"/"+qna.getImage()); 
			delFile.delete();
		 
		int cnt = qnaDao.deleteData(num);
		
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("num",qna.getNum());
		mav.setViewName(getPage);
		return mav;

	}
}
