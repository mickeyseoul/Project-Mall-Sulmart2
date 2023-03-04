package alcohol.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("snCateDao")
public class SnCateDao {
	
	private String namespace = "alcohol.model.Alcohol";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<SnCateBean> getAllSnCate() {
		List<SnCateBean> lists = new ArrayList<SnCateBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllSnCate");
		return lists;
	}

	public void deleteSnCate(String num) {
		sqlSessionTemplate.delete(namespace+".DeleteSnCate", num);
		
	}

	public void insertSnCate(SnCateBean snCateBean) {
		sqlSessionTemplate.insert(namespace+".InsertSnCate", snCateBean);
		
	}
	
	//건식만
	public List<SnCateBean> getSnCate1() {
		List<SnCateBean> lists = new ArrayList<SnCateBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetSnCate1");
		return lists;
	}
	
	//습식만
	public List<SnCateBean> getSnCate2() {
		List<SnCateBean> lists = new ArrayList<SnCateBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetSnCate2");
		return lists;
	}
	
	//cate2만
	public List<SnCateBean> getSnCate3() {
		List<SnCateBean> lists = new ArrayList<SnCateBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetSnCate3");
		return lists;
	}
	
	

}
