package mall.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.model.HeartListBean;
import mall.model.MypageDao;
import member.model.MemberBean;

@Controller
public class MypageReviewController {

	private final String command = "/myPageReview.mall";
	private String getPage = "/myPageReview";

	@Autowired
	MypageDao myPageDao;

	@RequestMapping(command)
	public String mypage(HttpSession session, Model model) {
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		System.out.println(member);
		if (member == null) {

			return "redirect:/login.mem";
		}

		// 로그인 한 유저가 누른 찜 리스트 로드
		List<HeartListBean> list = myPageDao.selectHeartList(member.getNum());

		model.addAttribute("heartList", list);

		System.out.println(list);

		return getPage;
	}

	@ResponseBody
	@RequestMapping(value = "/prodDelete.mall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public HashMap<String, String> prodDelete(@RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr,
			HttpSession session, HeartListBean heart) {
		System.out.println("checkBoxArr : [" + checkBoxArr + "]");
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		HashMap<String, String> msg = new HashMap<String, String>();

		if (member != null) {
			if (checkBoxArr == null) {
				msg.put("msg", "삭제할 상품을 선택하세요.");
			} else {
				System.out.println("삭제 로직 실행");
				heart.setMem_num(member.getNum());
				// 로그인 했을 경우

				int row = 0;
				int prodNum = 0;

				for (String i : checkBoxArr) {
					prodNum = Integer.parseInt(i);
					heart.setProd_num(prodNum);
					myPageDao.heartProdDelete(heart); 
				}
				row = 1;

				if (row < 0) {
					// row가 0보다 작을 경우 오류
					msg.put("msg", "데이터베이스 오류입니다.");
				} else {
					// 정상 작업 ajax 응답
					msg.put("msg", "삭제가 완료되었습니다.");
					msg.put("status", "1");
				}
			}

		} else {
			// 로그인 하지 않았을 경우
			msg.put("msg", "로그인 하지 않은 사용자는 접근 할 수 없습니다.");
			msg.put("url", "/ex/login.mem");
		}

		return msg;
	}
}
