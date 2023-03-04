package member.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberDeleteController {
	public final String command = "delete.mem";
	public String getPage = "redirect:/login.mem";
	
	@Autowired
	private MemberDao memberDao;
	
	//@Autowired
	//ServletContext servletContext;
	
	@RequestMapping(command)
	public String delete(
			@RequestParam("num")String num,
			@RequestParam(value="pageNumber",required=false)String pageNumber,
			Model model) {
		
		System.out.println("pageNumber"+pageNumber);
		MemberBean mb = memberDao.selectMemberByNum(num);
		
	//	String deletePath = servletContext.getRealPath("/resources");
		
		int cnt = memberDao.memberDelete(num);
		
		System.out.println("delete컨트롤러:::"+pageNumber);
		//return getPage+"?pageNumber="+pageNumber;
		
		
		return getPage;
		
	}

}
