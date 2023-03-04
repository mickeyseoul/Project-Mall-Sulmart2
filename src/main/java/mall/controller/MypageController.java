package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	private final String command = "/myPage.mall";
	private String getPage = "/myPage";
	//private String gotoPage ="redirect:/order.mall";
	//private String gotoPage ="/shopList";
	
	@RequestMapping(command)
	public String mypage(HttpServletResponse response,
						HttpSession session
						)throws IOException {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			//로그인안하면 하도록
			if(session.getAttribute("loginInfo") == null) {
				writer.println("<script> alert('로그인 후 이용가능합니다');  history.go(-1);  </script>");
				writer.flush(); //alert을 내보내라는 뜻 
				session.setAttribute("destination", "redirect:/order.mall");
				return getPage;
		
	}
			return getPage; //여기서 order.mall로 가면 리스트 또 되는거여서 오류남..
		
	
}
}
