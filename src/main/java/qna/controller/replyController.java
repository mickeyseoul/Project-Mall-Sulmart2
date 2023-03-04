package qna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qna.model.QnaBean;
import qna.model.QnaDao;

@Controller
public class replyController {

	private final String command = "/reply.qna";

	@Autowired
	QnaDao qnaDao;

	@RequestMapping(value=command)
	public String reply(@ModelAttribute("qna") @Valid QnaBean qna, BindingResult result, RedirectAttributes rttr,
			@RequestParam(value="pageNumber",required=false) String pageNumber, Model model) {

		qnaDao.reply(qna);
		
		rttr.addAttribute("num", qna.getNum());
		rttr.addAttribute("pageNumber", pageNumber);
		return "redirect:/list.qna";
	}
}
 