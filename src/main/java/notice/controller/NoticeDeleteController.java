package notice.controller;

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

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeDeleteController {
	private final String command="delete.no";
	private String getPage="redirect:/list.no";
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(command)
	public ModelAndView deleteProc(Model model, @RequestParam("num") String num,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();

		NoticeBean notice = noticeDao.getData(num);
		
		String deletePath = servletContext.getRealPath("/resources/notice");
		File delFile = new File(deletePath+"/"+notice.getImage()); 
		delFile.delete();
		
		int cnt = noticeDao.deleteData(num);
		response.setContentType("text/html; charset=UTF-8"); //한글처리
		PrintWriter writer = response.getWriter(); //연결다리

		
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("num",notice.getNum());
		mav.setViewName(getPage);
		return mav;

	}
}
