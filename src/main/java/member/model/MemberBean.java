package member.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberBean {
	
	private int num;
	
	@NotBlank(message="닉네임을 적어주세요")
	private String name;
	
	@NotBlank(message="아이디를 입력해주세요")
	@Pattern(regexp = "[a-zA-Z0-9]{5,9}", message = "아이디는 영문, 숫자만 가능하며 5 ~ 10자리까지 가능합니다")
	private String id;
	
	@NotBlank(message="비밀번호를 입력해주세요")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}", message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다")
	private String password;
	
	@NotBlank(message= "이메일을 입력해주세요")
	private String email1;
	private String email2;
	
	@NotEmpty(message= "생년월일을 입력해주세요")
	private String rrn1;
	private String rrn2;
	private String rrn3;
	
	@NotBlank(message= "핸드폰 번호를 입력해주세요")
	@Pattern(regexp ="^[0-9]+$",message="숫자로 입력하세요")
	private String hp1;
	private String hp2;
	private String hp3;
	
	@NotEmpty(message="배송지를 입력해주세요")
	private String zipcode1;
	private String zipcode2;
	private String zipcode3;
	private String zipcode4;
	
	private int mpoint;
	
	@NotNull(message="가입 유형을 선택해주세요")
	private String seller;
	
	/* 0929 */
	private int salecount;
	
	public int getSalecount() {
		return salecount;
	}
	public void setSalecount(int salecount) {
		this.salecount = salecount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRrn1() {
		return rrn1;
	}
	public void setRrn1(String rrn1) {
		this.rrn1 = rrn1;
	}
	public String getRrn2() {
		return rrn2;
	}
	public void setRrn2(String rrn2) {
		this.rrn2 = rrn2;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getZipcode1() {
		return zipcode1;
	}
	public void setZipcode1(String zipcode1) {
		this.zipcode1 = zipcode1;
	}
	public String getZipcode2() {
		return zipcode2;
	}
	public void setZipcode2(String zipcode2) {
		this.zipcode2 = zipcode2;
	}
	public String getZipcode3() {
		return zipcode3;
	}
	public void setZipcode3(String zipcode3) {
		this.zipcode3 = zipcode3;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getZipcode4() {
		return zipcode4;
	}
	public void setZipcode4(String zipcode4) {
		this.zipcode4 = zipcode4;
	}
	public String getRrn3() {
		return rrn3;
	}
	public void setRrn3(String rrn3) {
		this.rrn3 = rrn3;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	
	
	
}
