package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {

	public final String command="/login.mem";
	public String getPage="/memberLoginForm";
	public String gotoPage="redirect:/main.mall";
	public String gotoPage2="redirect:/main.ad";

	@Autowired
	MemberDao memberDao;

	@RequestMapping(value=command,method=RequestMethod.GET)
	public String login() {
		return getPage;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String login(MemberBean member,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();

		MemberBean login = memberDao.getMember(member.getId());
		
		//System.out.println("MemberLoginController");
		//System.out.println(login.getId());

		if(login != null) {//회원가입이 되어있으니 로그인 진행
			
			if(member.getPassword().equals(login.getPassword())) { //비번 일치하면
				String destination = (String)session.getAttribute("destination");
				session.setAttribute("loginInfo", login);
				
				if(login.getId().equals("admin") || Integer.valueOf(login.getSeller())==1) {
					return gotoPage2;
				}
				
				return gotoPage;
				
			}else { //비번 일치하지 않으면
				writer.println("<script type='text/javascript'>");
				writer.println("alert('비밀번호가 일치하지 않습니다.');");
				writer.println("</script>");
				writer.flush();

				return getPage;
			}
			
		}else {//존재하지 않은 회원입니다 null
			System.out.println("議댁옱�븯吏� �븡�� �쉶�썝�엯�땲�떎.");

			writer.println("<script type='text/javascript'>");
			writer.println("alert('존재하지 않는 회원입니다.');");
			writer.println("</script>");
			writer.flush();

			return getPage;
			
		}
		
		
	}
	
}
