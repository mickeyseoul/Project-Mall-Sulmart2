package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.model.MemberDao;

@Controller
public class MemberCheckController {
	
	@Autowired
	MemberDao memberDao;
	
	private final String command="idcheck.mem";
	
	@ResponseBody
	@RequestMapping(command)
	public String check(
			@RequestParam("inputid") String inputid) {
		
		int cnt=-1;
		cnt = memberDao.Idcheck(inputid);
		if(cnt ==0) {
			return "YES";
		}
		else {
			return "NO";
		}
		
	}
}
