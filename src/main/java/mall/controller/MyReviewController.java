package mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.model.CompleteReviewBean;
import mall.model.MypageDao;
import mall.model.PossibleReviewBean;
import mall.model.ReviewStatusBean;
import member.model.MemberBean;

@Controller
public class MyReviewController {

	private final String command = "/myReview.mall";
	private String getPage = "/myReview";

	@Autowired
	MypageDao myPageDao;

	@RequestMapping(command)
	public String mypage(HttpSession session, Model model) {
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		System.out.println(member);
		
		if (member == null) {

			return "redirect:/login.mem";
		}

		List<ReviewStatusBean> slist = myPageDao.selectReviewStatus(member.getId());
		List<PossibleReviewBean> plist = myPageDao.selectPossibleReview(member.getId()); 
		List<CompleteReviewBean> clist = myPageDao.selectCompleteReview(member.getId()); 

		for(ReviewStatusBean var : slist) {
			System.out.println(var);
		}

		model.addAttribute("rstatus", slist);
		model.addAttribute("pbreview", plist);
		model.addAttribute("cmreview", clist);

		return getPage;
	}

}
