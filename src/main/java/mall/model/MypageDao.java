package mall.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alcohol.model.HeartBean;

@Component("MypageDao")
public class MypageDao {
	String namespace = "mall.model.Mypage";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public HeartBean getMyHeart(String mem_num) {
		//List<HeartListBean> heartList = new ArrayList<HeartListBean>();
		HeartBean bean = sqlSessionTemplate.selectOne(namespace + ".GetMyHeart", mem_num);

		return bean;
	}

	public List<ReviewStatusBean> selectReviewStatus(String memberID) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberID", memberID);
		List<ReviewStatusBean> rsbean = sqlSessionTemplate.selectList(namespace + ".reviewStatus", map);

		return rsbean;
	}

	public List<PossibleReviewBean> selectPossibleReview(String memberID) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberID", memberID);
		List<PossibleReviewBean> prbean = sqlSessionTemplate.selectList(namespace + ".possibleReview", map);

		return prbean;
	}
	
	public List<CompleteReviewBean> selectCompleteReview(String memberID) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberID", memberID);
		List<CompleteReviewBean> crbean = sqlSessionTemplate.selectList(namespace + ".completeReview", map);

		return crbean;
	}
	
	public int heartProdDelete(HeartBean heart) {
		//System.out.println("삭제 메서드 실행 ----");
		//System.out.println(heart.getProd_num());
		return sqlSessionTemplate.delete(namespace + ".HeartProdDelete", heart);
	}
}
