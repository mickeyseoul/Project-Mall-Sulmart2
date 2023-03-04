package qna.model;

import java.sql.Date;

public class ReviewBean {
	private Integer num;
	private String title;
	private String content;
	private String writer;
	private String passwd;
	private String email;
	private Integer star;
	private Date reg_date;
	private Integer ref;
	private Integer re_step;
	private Integer re_level;
	private Integer readcount;
	private String ip;
//	private String imagecontent;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	public Integer getRe_step() {
		return re_step;
	}

	public void setRe_step(Integer re_step) {
		this.re_step = re_step;
	}

	public Integer getRe_level() {
		return re_level;
	}

	public void setRe_level(Integer re_level) {
		this.re_level = re_level;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

//	public String getImagecontent() {
//		return imagecontent;
//	}
//
//	public void setImagecontent(String imagecontent) {
//		this.imagecontent = imagecontent;
//	}

	@Override
	public String toString() {
		return "ReviewBean [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", passwd=" + passwd + ", email=" + email + ", star=" + star + ", reg_date=" + reg_date + ", ref="
				+ ref + ", re_step=" + re_step + ", re_level=" + re_level + ", readcount=" + readcount + ", ip=" + ip
				+ "]";
	}

}
