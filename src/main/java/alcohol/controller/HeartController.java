package alcohol.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import alcohol.model.HeartBean;
import member.model.MemberBean;

@Controller
public class HeartController {
	
	@Autowired
	AlcoholDao alcoholDao;
	
	
	@Autowired
	HttpSession session;
	
	
	//2023-04-30 찜하기
	@RequestMapping("/heart.al")
	@ResponseBody
	public int heart(String num) {
		MemberBean member = (MemberBean)session.getAttribute("loginInfo");
		//System.out.println("회원번호"+member.getNum());
		//System.out.println("상품번호"+num);
		
		HeartBean bean = alcoholDao.recordExist(member.getNum());
		
		if(bean != null) { //해당 아이디의 찜 레코드가 이미 존재한다면
			//System.out.println("레코드 존재");
			if(bean.getProd_num().indexOf(num)==-1) { //해당 상품이 찜 목록에 없다면
				bean.setProd_num(bean.getProd_num()+" "+num);
				alcoholDao.heartUpdate(bean);
				
			}else { //해당 상품이 이미 찜 목록에 있다면
				//System.out.println("해당 상품 이미 존재");
				
			}
			
			
		}else { //해당 아이디의 찜 레코드가 없다면
			//System.out.println("레코드 없음");
			bean = new HeartBean();
			bean.setMem_num(String.valueOf(member.getNum()));
			bean.setProd_num(num);
			
			alcoholDao.heartInsert(bean);

		}
		
		return 1;
	}

}
