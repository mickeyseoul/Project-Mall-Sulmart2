
package qna.model;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class QcommentBean {

	private int comnum;
	private String pnum;
	private String writer;
	@NotEmpty(message = "내용을 입력해주세요")
	private String content;
	private Date reg_date;

	public int getComnum() {
		return comnum;
	}
	public void setComnum(int comnum) {
		this.comnum = comnum;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}



/*		num number not null primary key,			
		writer varchar2(50) not null,		
		content varchar2(300),
		reg_date date default sysdate	
*/