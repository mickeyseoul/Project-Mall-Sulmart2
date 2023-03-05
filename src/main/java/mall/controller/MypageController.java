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

		//로그인 안했으면 로그인 하도록
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(session.getAttribute("loginInfo") == null) {
			writer.println("<script> alert('로그인 후 이용가능합니다.');  history.go(-1);  </script>");
			writer.flush();
			session.setAttribute("destination", "redirect:/order.mall");

		}
		return getPage;


	}
}
