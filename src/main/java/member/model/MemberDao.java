package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import utility.Paging;

@Component("MemberDao")
public class MemberDao {
	
	String namespace="member.model.Member";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int getTotalCount(Map<String, String> map) {
		int result = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		
		System.out.println("GetTotalCount"+result);
		return result;
	}

	public List<MemberBean> getMemberList(Paging pageInfo, Map<String, String> map) {
		List<MemberBean> lists=new ArrayList<MemberBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		
		lists=sqlSessionTemplate.selectList(namespace+".GetMemberList",map,rowBounds);
		
		return lists;
	}

	public int memberInsert(MemberBean member) {
		int cnt = sqlSessionTemplate.insert(namespace+".RegisterMember",member);
	
		System.out.println("Dao cnt:"+cnt);
		return cnt;
	}

	public int Idcheck(String inputid) {
		int result = -1;
		result = sqlSessionTemplate.selectOne(namespace+".IdCheck",inputid);
		return result;
	}
	
	public int Pwcheck(String inputpw) {
		int result = -1;
		result = sqlSessionTemplate.selectOne(namespace+".Pwcheck",inputpw);
		return result;
	}

	public MemberBean getMember(String id) {
		MemberBean mbean = null;
		mbean = sqlSessionTemplate.selectOne(namespace+".GetMember", id);
		return mbean;
	}
	public MemberBean updatePw(String password) {
		MemberBean mbean = null;
		mbean = sqlSessionTemplate.selectOne(namespace+".UpdateGo", password);
		return mbean;
	}

	public MemberBean selectMemberByNum(String id) {
		MemberBean mb = sqlSessionTemplate.selectOne(namespace+".GetMemberDetail",id);
		return mb;
	}

	public void updateMember(MemberBean mb) {
		int cnt = sqlSessionTemplate.update(namespace+".UpdateMember",mb);
		System.out.println("�뾽�뜲�씠�듃 Dao"+cnt);
	}

	public int memberDelete(String num) {
		int cnt = sqlSessionTemplate.delete(namespace+".DeleteMember",num);
		System.out.println("deleteDao cnt:::"+cnt);
		return cnt;
	}

	public String getMemberById(MemberBean member) {
		
		System.out.println("�븘�뵒李�1Dao:"+member.getName());
		System.out.println("컨트롤러mail:"+member.getEmail1());//null
		System.out.println("컨트롤러mail:"+member.getEmail2());//null
		String id = sqlSessionTemplate.selectOne(namespace+".GetMemberById", member);
		System.out.println("컨트롤러2Dao:"+id);// admin �굹�샂
		return id;
	}

	public String updategoMember(String pw) {
		String ugo = sqlSessionTemplate.selectOne(namespace+".UpdateGo", pw);
		System.out.println("Dao ugo>>"+ugo);
		return ugo;
	}

	public String getMemberByPw(MemberBean member) {
		String pw = sqlSessionTemplate.selectOne(namespace+".GetMemberByPw", member);
		return pw;
	}
	
	//혜인 멤버+포인트
	public void updateMpoint(String id, double mpoint) {
	MemberBean mb = new MemberBean();
	mb.setId(id);
	mb.setMpoint((int) mpoint); 
	sqlSessionTemplate.update(namespace+".UpdateMpoint",mb); 		
	}
	//멤버-포인트
	public void updateMpoint2(String id, double mpoint) {
		MemberBean mb = new MemberBean();
		mb.setId(id);
		mb.setMpoint((int) mpoint);
		sqlSessionTemplate.update(namespace+".UpdateMpoint2",mb);
	}
	
	
	
	//박이랑
	public int getSellerTotalCount(Map<String, String> map) {
		int result = sqlSessionTemplate.selectOne(namespace+".GetSellerTotalCount",map);
		return result;
	}

	public List<MemberBean> getAllSeller(Paging pageInfo, Map<String, String> map) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		
		List<MemberBean> lists=new ArrayList<MemberBean>();
		lists=sqlSessionTemplate.selectList(namespace+".GetAllSeller",map,rowBounds);
		return lists;
	}

	public int getSaleItems(String id) {
		
		int count =sqlSessionTemplate.selectOne(namespace+".GetSaleItems",id);
		return count;
	}
	
}
