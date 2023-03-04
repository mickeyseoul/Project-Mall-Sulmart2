package admin.controller;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import order.model.OrderDao;

	/**
	 * @class ExampleSend
	 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
	 * 출처 : https://developer.coolsms.co.kr/JAVA_SDK_EXAMPLE_Message
	 */
	@Controller
	public class OrderSmsController {
		private final String command="/orderState.ad";
		private final String getPage="redirect:/main.ad"; ///orderList  redirect:/main.ad orderList.ad
		
		@Autowired
		private OrderDao orderDao;
		@Autowired
		private MemberDao memberDao;
		
		@RequestMapping(command)
		public String sms(@RequestParam(value="orderid", required = false) String orderid) {
			String api_key = "자신의 api입력";
		    String api_secret = "자신의 api_secret입력";
		    Message coolsms = new Message(api_key, api_secret);
		    
		    String memberid = orderDao.getMemberId(orderid); 
			MemberBean member = memberDao.getMember(memberid);
		    member.getHp1();
		    member.getHp2();
		    member.getHp3();
		    System.out.println(" member.getHp1();"+ 0+member.getHp1()+member.getHp2()+member.getHp3());
		    /*
			 * select memid from orders where orderid=2;
			 * 
			 * select hp1, hp2, hp3 from members where id like 'char';
			 */
		    // 4 params(to, from, type, text) are mandatory. must be filled
		    
		    orderDao.orderComplete(orderid);
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", 0+member.getHp1()+member.getHp2()+member.getHp3());	// 수신전화번호
		    params.put("from", 0+member.getHp1()+member.getHp2()+member.getHp3());	// 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		    params.put("type", "SMS");
		    params.put("text", "배송이 시작되었습니다!");
		    params.put("app_version", "test app 1.2"); // application name and version

		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
			return getPage;
		  }
		}

