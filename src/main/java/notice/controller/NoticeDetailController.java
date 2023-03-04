package notice.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeDetailController {
	private final String command = "detail.no";
	private String getPage = "/detailForm";

	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping(value = command)
	public ModelAndView detail(@RequestParam("num") String num,
			@RequestParam(value="pageNumber",required = false) String pageNumber) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ModelAndView mav = new ModelAndView();
		noticeDao.updateReadcount(num);
		NoticeBean notice = noticeDao.getData(num);
		
		mav.addObject("notice",notice);
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("num",notice.getNum());
		mav.setViewName(getPage);
		return mav;
	}
}


