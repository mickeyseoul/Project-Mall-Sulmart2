package member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberRegisterController {
	public final String command = "/registerForm.mem";
	public String getPage = "/memberRegisterForm";
	public String gotoPage = "redirect:/login.mem";
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String registerForm() {
			System.out.println("ddddd");
		return getPage;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String register(
							@ModelAttribute("member") @Valid MemberBean member,
							BindingResult result,HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter writer = response.getWriter();
		
		
		if(result.hasErrors()) {
			System.out.println("123123");
			for(ObjectError obj:result.getAllErrors()) {//�뜝�럥�뱺�뜝�럩�몠�뜝�럥�뱺 �뜝�룞�삕�뜝�럥由� �뜝�럩�젧�솻洹ｋ뼬�뜝占� �뜝�럥堉뽭뇦爰용쳛占쎈엿�뜝�럥裕� �뤆�룇鍮섊뙼�뭿泥롥뜝占� 占쎈뎨占쎈봾裕욃뜝�럥諭쒎뜝�럥�뱺 �뜝�럥堉뽭뇦爰용쳛鈺곕돍�삕占쎈엿�뜝�럩踰� 

			}
			return getPage;
		}
		
		memberDao.memberInsert(member);

		writer.println("<script type='text/javascript'>");
		writer.println("alert('환영합니다! 로그인후에 이용하세요.');");
		writer.println("location.href='login.mem';");
		writer.println("</script>");
		writer.flush();
		
		return gotoPage;
	}
}
