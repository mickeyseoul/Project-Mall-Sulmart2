package member.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberFindPwController {

	public final String command = "findpw.mem";
	public String getPage = "/memberFindPw";
	public String gotoPage = "redirect:/login.mem";

	@Autowired
	MemberDao memberDao;

	@RequestMapping(value = command, method = RequestMethod.GET)
	public String findId() {
		return getPage;
	}

	@RequestMapping(value = command, method = RequestMethod.POST)
	public String findId(
			Model model,
			HttpServletResponse response,MemberBean member,HttpSession session) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		String pw = memberDao.getMemberByPw(member);
		System.out.println("pw"+pw);
		//占쎈뎨占쎈뿪�돇�뤆�룊�삕 �뜝�럩�꼪�뜝�럩逾� �뜝�럥�닡�뜝�럩�뭵
		if(pw == null) {

				writer.println("<script type='text/javascript'>");
				writer.println("alert('회원님의 정보가 일치하지 않습니다.');");
				writer.println("</script>");
				writer.flush();

				return getPage;
		}else {
			model.addAttribute("loginPw", member.getPassword());
			
			writer.println("<script type='text/javascript'>");
			writer.println("alert('회원님의 비밀번호는 " + pw + " 입니다.');");
			writer.println("location.href='login.mem';");
			writer.println("</script>");
			writer.flush();
			
			return gotoPage;
		}

	}
}
