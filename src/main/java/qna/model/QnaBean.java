package qna.model;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class QnaBean {
	private String num;
	private String writer; //작성자, members의 num과 연결
	private String cateuser; //구매자/판매자
	private String cateopen; //비공개/전체공개
	private String cate; //회원/정보관리,배송,반품/환불/교환/AS,영수증/증빙서류,상품/이벤트,키타
	@NotEmpty(message = "제목을 입력하세요.")
	private String subject;
	private String image;
	private String checkimage; //자동 등록 방지 이미지
	@NotEmpty(message = "내용을 입력하세요.")
	private String content;
	private Timestamp reg_date;
	private int readcount;
	private String ref; // =num
	private String re_step; //최신순, 낮은 번호가 최신
	private String re_level; //원글0, 답글1, 답답글2
	private String reply;
	
	
	private MultipartFile upload;
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) { // upload=a.jpg X
		System.out.println("setUpload(MultipartFile upload)");
		this.upload = upload;
		//System.out.println("setUpload upload:" + upload);
	//	System.out.println("upload.getName():"+upload.getName());
	//	System.out.println("upload.getOriginalFilename():"+upload.getOriginalFilename());
		setImage(upload.getOriginalFilename()); 
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCateuser() {
		return cateuser;
	}
	public void setCateuser(String cateuser) {
		this.cateuser = cateuser;
	}
	public String getCateopen() {
		return cateopen;
	}
	public void setCateopen(String cateopen) {
		this.cateopen = cateopen;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCheckimage() {
		return checkimage;
	}
	public void setCheckimage(String checkimage) {
		this.checkimage = checkimage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getRe_step() {
		return re_step;
	}
	public void setRe_step(String re_step) {
		this.re_step = re_step;
	}
	public String getRe_level() {
		return re_level;
	}
	public void setRe_level(String re_level) {
		this.re_level = re_level;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}

}
