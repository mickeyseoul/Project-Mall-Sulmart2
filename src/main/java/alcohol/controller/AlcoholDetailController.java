package alcohol.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import alcohol.model.AlcoholBean;
import alcohol.model.AlcoholDao;
import alcohol.model.ReviewBean;
import member.model.MemberBean;

@Controller
public class AlcoholDetailController {

	private final String command = "/detail.al";
	private String getPage = "/alcoholDetail";

	@Autowired
	AlcoholDao alcoholDao;

	@RequestMapping(value = command)
	public ModelAndView detail(@RequestParam("num") String num,
			@RequestParam(value = "pageNumber", required = false) String pageNumber, HttpSession session,
			ReviewBean review) {

		AlcoholBean ab = alcoholDao.getAlcohol(num);
		System.out.println("ab.getStock() "+ab.getStock());

		List<ReviewBean> list = new ArrayList<ReviewBean>();
		list = alcoholDao.getReview(num); //

		ModelAndView mav = new ModelAndView();

		mav.addObject("ab", ab);
		mav.addObject("rb", list);
		mav.addObject("pageNumber", pageNumber);
		
		mav.addObject("stock", ab.getStock());//

		mav.setViewName(getPage);
		return mav;
	}

	@RequestMapping(value = "/reWrite.al", method = RequestMethod.POST)
	public String reviewWrite(@RequestParam("num") String num, ReviewBean review, RedirectAttributes rttr,
			HttpSession session, Model model) throws IOException {
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		
		// 由щ럭�옉�꽦 沅뚰븳
		// alcohol�뤃�뜑 - alert.jsp �깮�꽦 �썑 alert �븳踰덉뿉 泥섎━
		if (member == null) {
			String msg = "로그인하지 않은 사용자는 리뷰를 작성 할 수 없습니다.";
			String url = "/ex/detail.al?num=" + num;

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			return "/alert"; 
		}

		int checkByFlag = alcoholDao.checkBuyFlag(member.getId(), Integer.parseInt(num));
		if (checkByFlag <= 0) {
			String msg = "주문한 사용자만 리뷰를 작성 할 수 있습니다.";
			String url = "/ex/detail.al?num=" + num;

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			return "/alert";
		}
		
		
		System.out.println("memberId : [" + member.getId() + "], productId : [" + Integer.parseInt(num) + "]");
		int checkReview = alcoholDao.checkDuplecateReview(member.getId(), Integer.parseInt(num));

		if (checkReview >= 1) {
			String msg = "";
			String url = "/ex/detail.al?num=" + num;

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			return "/alert"; 
		}

		// ****************** File Upload ******************
//			System.out.println(file);
//			String fileRealName = file.getOriginalFilename(); // �뙆�씪紐낆쓣 �뼸�뼱�궪 �닔 �엳�뒗 硫붿꽌�뱶!
//			long size = file.getSize(); // �뙆�씪 �궗�씠利�
//
//			System.out.println("�뙆�씪紐� : " + fileRealName);
//			System.out.println("�슜�웾�겕湲�(byte) : " + size);
//
//			// �꽌踰꾩뿉 ���옣�븷 �뙆�씪�씠由� fileextension�쑝濡� .jsp�씠�윴�떇�쓽 �솗�옣�옄 紐낆쓣 援ы븿
//			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
//			String uploadFolder = "resources/images";
//
//			UUID uuid = UUID.randomUUID();
//			System.out.println(uuid.toString());
//			String[] uuids = uuid.toString().split("-");
//
//			String uniqueName = uuids[0];
//			System.out.println("�깮�꽦�맂 怨좎쑀臾몄옄�뿴" + uniqueName);
//			System.out.println("�솗�옣�옄紐�" + fileExtension);
//
//			// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid �쟻�슜 �쟾
//
//			File saveFile = new File(uploadFolder + "/" + uniqueName + fileExtension); // �쟻�슜 �썑
//			try {
//				file.transferTo(saveFile); // �떎�젣 �뙆�씪 ���옣硫붿꽌�뱶(filewriter �옉�뾽�쓣 �넀�돺寃� �븳諛⑹뿉 泥섎━�빐以��떎.)
//				review.setImagecontent(uniqueName);
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		// ****************** File Upload �걹 ******************

		Date sqlDate = new Date(new java.util.Date().getTime());

		review.setReg_date(sqlDate);
		review.setWriter(member.getId());

		alcoholDao.replyInsert(review);

		rttr.addAttribute("num", review.getNum());
		rttr.addAttribute("pageNumber", 1);

		return "redirect:/detail.al";

	}

	@RequestMapping(value = "/recomm.al", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, String> recommUpdate(@RequestParam("id") String id) {
		HashMap<String, String> msg = new HashMap<String, String>();

		int row = alcoholDao.updateRecomm(Integer.parseInt(id));
		int newRecomm = alcoholDao.selectRecomm(Integer.parseInt(id));

		msg.put("message", Integer.toBinaryString(row));
		msg.put("newRecomm", Integer.toString(newRecomm));

		return msg;
	}

	@RequestMapping(value = "/heart.al", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, String> heartUpdate(@RequestParam("id") String id, HttpServletRequest request) {
		HashMap<String, String> msg = new HashMap<String, String>();

		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");

		if (member != null) {
			int checkHeart = alcoholDao.checkHeart(member.getNum(), Integer.parseInt(id));
			System.out.println("-------------- 濡쒓렇�씤�씠 �릺�뿀�쓣 寃쎌슦 ------------");
			System.out.println(checkHeart);
			if (checkHeart <= 0) {
				System.out.println("-------------- 李쒖씠  �븞�릺�뼱 �엳�쓣 寃쎌슦 ------------");
				int row = alcoholDao.updateHeart(member.getNum(), Integer.parseInt(id));
				msg.put("row", Integer.toString(row));
				msg.put("message", "데이터베이스 오류입니다.");
			} else {
				System.out.println("-------------- 李쒗븳 �긽�깭�씪 寃쎌슦 ------------");
				msg.put("message", "상품을 찜했습니다.");

			}
		} else {
			msg.put("message", "이미 찜한 상품입니다.");

		}
		return msg;
	}

}
