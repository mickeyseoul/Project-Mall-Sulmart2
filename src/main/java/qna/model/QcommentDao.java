/*

package qna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("QcommentDao")
public class QcommentDao {
	private String namespace = "qna.model.Qna";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<QcommentBean> getAllComment(Paging pageInfo, Map<String, String> map) {
		List<QcommentBean> lists = new ArrayList<QcommentBean>(); 
		System.out.println("getAllComment 2");
		lists = sqlSessionTemplate.selectList(namespace+".getAllComment", map);
		System.out.println("getAllComment 3");
		return lists;
	}
	
	public void insertComment(QcommentBean qco) {
		System.out.println("insertComment 2");
		sqlSessionTemplate.insert(namespace+".insertComment", qco);
		System.out.println("insertComment 3");
	}
	
	public QcommentBean getComment(String num) {
		System.out.println("getComment 2");
		QcommentBean qco = sqlSessionTemplate.selectOne(namespace+".getComment", num);
		System.out.println("getComment 3");
		return qco;	
	}
	
	public void updateComment(QcommentBean qco) {
		System.out.println("updateComment 2");
		sqlSessionTemplate.update(namespace+".updateComment", qco);
		System.out.println("updateComment 3");
	}

	public int deleteComment(String num) {
		System.out.println("deleteComment 2");
		int cnt = sqlSessionTemplate.delete(namespace+".deleteComment",num);
		System.out.println("deleteComment 3");
		return cnt;
	}
	
	public List<QcommentBean> selectCommentList(QcommentBean qco){
		List<QcommentBean> lists = new ArrayList<QcommentBean>(); 
		lists = sqlSessionTemplate.selectOne(namespace+".getComment", qco);
		return lists;
		//return sqlSessionTemplate.selectOne(namespace+".getComment", qco);
	}

	public List<QcommentBean> getCommentList(QcommentBean qco) {
		// TODO Auto-generated method stub
		return null;
	}
}

*/