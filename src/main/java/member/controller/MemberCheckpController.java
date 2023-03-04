package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.model.MemberDao;

@Controller
public class MemberCheckpController {
	
	@Autowired
	MemberDao memberDao;
	
	private final String command="pwcheck.mem";
	
	@ResponseBody
	@RequestMapping(command)
	public String check(
			@RequestParam("inputpw") String inputpw) {
		
		int cnt=-1;
		cnt = memberDao.Pwcheck(inputpw);
		if(cnt ==0) {
			return "YES"; // 일치하면 수정ㄱㄱ
		}
		else {
			return "NO";
		}
		
	}
}
