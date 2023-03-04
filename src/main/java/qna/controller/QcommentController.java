/*


package qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import qna.model.QcommentBean;
import qna.model.QcommentDao;

@Controller
public class QcommentController {

	private final String command = "/insertComment.qna";
	private String getPage = "redirect:/detail.qna";

	@Autowired
	QcommentDao qcoDao;

	@ModelAttribute("id")
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView insert(
			@ModelAttribute("qco") @Valid QcommentBean qco, BindingResult result,
			@RequestParam(value="pageNumber",required=false) String pageNumber,
			HttpServletResponse response) throws IOException {
		System.out.println("QcommentController");
		System.out.println("pageNumber : "+pageNumber);

		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			System.out.println("hasErrors : "+result.hasErrors());
			System.out.println("getErrorCount : "+result.getErrorCount());
			System.out.println("getAllErrors : "+result.getAllErrors());
			mav.setViewName(getPage);
			return mav;
		}

		//qco.setReg_date(new Timestamp(System.currentTimeMillis())); 

		System.out.println("Commentinsert 1");
		qcoDao.insertComment(qco);
		System.out.println("Commentinsert 4");

		mav.addObject("pageNumber",pageNumber);
		mav.addObject("comnum",qco.getComnum());
		//mav.setViewName(getPage+"?pnum="+pnum);
		return mav;
	}

	@RequestMapping("/getCommentList")
	public List<QcommentBean> getCommentList(@RequestParam("pnum") String pnum) {
		System.out.println("pnum : "+pnum);
		QcommentBean qco = new QcommentBean();
		QcommentDao qdao = new QcommentDao();
		qco.setPnum(pnum);
		return qdao.getCommentList(qco);
	}

}












/*

package qna.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import qna.model.BoardService;

@Controller
public class QnaReplyController {
    @Autowired
    BoardService boardService;
    
    //AJAX 호출 (댓글 등록)
    @RequestMapping(value="/board/reply/save", method=RequestMethod.POST)
    @ResponseBody
    public Object boardReplySave(@RequestParam Map<String, Object> paramMap) {
 
        //리턴값
        Map<String, Object> retVal = new HashMap<String, Object>();
 
        //패스워드 암호화
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
        paramMap.put("reply_password", password);
 
        //정보입력
        int result = boardService.regReply(paramMap);
 
        if(result>0){
            retVal.put("code", "OK");
            retVal.put("reply_id", paramMap.get("reply_id"));
            retVal.put("parent_id", paramMap.get("parent_id"));
            retVal.put("message", "등록에 성공 하였습니다.");
        }else{
            retVal.put("code", "FAIL");
            retVal.put("message", "등록에 실패 하였습니다.");
        }
 
        return retVal;
 
    }
    //AJAX 호출 (댓글 삭제)
    @RequestMapping(value="/board/reply/del", method=RequestMethod.POST)
    @ResponseBody
    public Object boardReplyDel(@RequestParam Map<String, Object> paramMap) {
 
        //리턴값
        Map<String, Object> retVal = new HashMap<String, Object>();
 
        //패스워드 암호화
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
        paramMap.put("reply_password", password);
 
        //정보입력
        int result = boardService.delReply(paramMap);
 
        if(result>0){
            retVal.put("code", "OK");
        }else{
            retVal.put("code", "FAIL");
            retVal.put("message", "삭제에 실패했습니다. 패스워드를 확인해주세요.");
        }
        return retVal;
    }
}

*/