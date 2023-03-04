package alcohol.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component("alCateDao")
public class AlCateDao {
	
	private final String namespace = "alcohol.model.Alcohol";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<AlCateBean> getAllAlCate() {
		List<AlCateBean> lists = new ArrayList<AlCateBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllAlCate");
		return lists;
	}

	public void insertAlCate(String cate) {
		sqlSessionTemplate.insert(namespace+".InsertAlCate", cate);
	}

	public void deleteAlCate(String num) {
		sqlSessionTemplate.delete(namespace+".DeleteAlCate", num);
		
	}
	
	


}
