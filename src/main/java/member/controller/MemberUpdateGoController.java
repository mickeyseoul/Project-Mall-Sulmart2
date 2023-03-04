package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberUpdateGoController {
	
	public final String command="updatego.mem";
	public String getPage="/memberUpdateGo";
	//public String gotoPage="redirect:/update.mem";
	public String gotoPage="/memberUpdateForm";

	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String updateGo() {
		
		System.out.println("gogo왔니");
		return getPage;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String login(MemberBean member,
			HttpSession session,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String ugo = memberDao.updategoMember(member.getPassword());
		System.out.println("Dao ugo"+ugo);
		
		if(ugo != null) {
			
			return gotoPage;
		}else {
			
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호를 확인해주세요.');");
			writer.println("</script>");
			writer.flush();
			
			return command;
		}
	}
	
}
