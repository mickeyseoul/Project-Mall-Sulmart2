package qna.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("QnaDao")
public class QnaDao {
	
	private String namespace = "qna.model.Qna";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<QnaBean> getAllData(Paging pageInfo, Map<String, String> map) {
		List<QnaBean> lists = new ArrayList<QnaBean>(); 
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		lists = sqlSessionTemplate.selectList(namespace+".getAllData", map, rowBounds);
		return lists;
	}

	public int getTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".getTotalCount", map);
		return totalCount;
	}

	public void updateReadcount(String num) {
		sqlSessionTemplate.update(namespace+".addReadcount",num);
	}
	
	public void insertData(QnaBean qna) {
		sqlSessionTemplate.insert(namespace+".insertData", qna);
	}

	public QnaBean getData(String num) {
		QnaBean qna = sqlSessionTemplate.selectOne(namespace+".getData", num);
		return qna;	
	}
	
	public void updateData(QnaBean qna) {
		sqlSessionTemplate.update(namespace+".updateData", qna);
	}

	public int deleteData(String num) {
		System.out.println("deleteData 2");
		int cnt = sqlSessionTemplate.delete(namespace+".deleteData",num);
		System.out.println("deleteData 3");
		return cnt;
	}
	
	public void reply(QnaBean qna) {
		sqlSessionTemplate.update(namespace+".reply", qna);
		
	}
} 