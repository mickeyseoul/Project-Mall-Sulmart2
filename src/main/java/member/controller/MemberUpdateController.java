package member.controller;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller

public class MemberUpdateController {
	
	private final String command="update.mem";
	private String getPage="/memberUpdateForm";
	private String gotoPage="redirect:/main.mall";
	
	@Autowired
	private MemberDao memberDao;
	

		 @RequestMapping(value=command,method = RequestMethod.GET) public ModelAndView
		 updateForm(@RequestParam("id") String id,
		 @RequestParam(value="pageNumber", required = false) String pageNumber) {
		  
		 ModelAndView mav = new ModelAndView();
		 MemberBean mb = memberDao.selectMemberByNum(id);
		 mav.addObject("member",mb);
		 mav.addObject("pageNumber",pageNumber);
		 mav.setViewName(getPage);
		 
		 System.out.println("�뚢뫂�뱜嚥▲끇�쑎 mav"+mav);
		 
		 return mav;
		 	}
		 
		 
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String update(
			@ModelAttribute("member") @Valid MemberBean mb,
			BindingResult result,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			Model model) {
		System.out.println(">>mb:"+mb);
		
	      if(result.hasErrors()) {
	          System.out.println("123123");
	          for(ObjectError obj:result.getAllErrors()) {//占쎈퓠占쎌쑎占쎈퓠 占쏙옙占쎈립 占쎌젟癰귣떯占� 占쎈뼖野꺿뫁�뿳占쎈뮉 揶쏆빘猿쒎첎占� �뵳�딅뮞占쎈뱜占쎈퓠 占쎈뼖野꺿뫁議뉛옙�뿳占쎌벉 
	             System.out.println("筌롫뗄�뻻筌욑옙:"+obj.getDefaultMessage());
	             System.out.println("code:"+obj.getCode());
	             System.out.println("object name:"+obj.getObjectName());
	             System.out.println("-------------------------------------");
	          }
	          return getPage;
	       }

		memberDao.updateMember(mb);
		
		model.addAttribute("pageNumber",pageNumber);

		System.out.println("�뚢뫂�뱜嚥▲끇�쑎gotoPage"+gotoPage);
		
		return gotoPage;
	}
}
