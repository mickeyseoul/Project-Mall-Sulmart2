package alcohol.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mall.cart.ShoppingInfo;
import alcohol.model.ReviewBean;
import utility.Paging;

@Component("alcoholDao")
public class AlcoholDao {
	
	private String namespace = "alcohol.model.Alcohol";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insertAlcohol(AlcoholBean alcohol) {
		sqlSessionTemplate.insert(namespace+".InsertAlcohol", alcohol);
	}
	
	public int getTotalCount1(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount1",map);
		return totalCount;
	}

	public List<AlcoholBean> getAllAlcohol(Map<String, String> map, Paging pageInfo) {
		//페이징
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllAlcohol",map,rowBounds);
		return lists;
	}

	public AlcoholBean getAlcoholByNum(String num) {
		AlcoholBean alcohol = sqlSessionTemplate.selectOne(namespace+".GetAlcoholByNum", num);
		return alcohol;
	}

	public void updateAlcohol(AlcoholBean alcohol) {
		sqlSessionTemplate.update(namespace+".UpdateAlcohol", alcohol);
		
	}

	public void deleteAlcohol(String num) {
		sqlSessionTemplate.delete(namespace+".DeleteAlcohol", num);
		
	}
	
	//메인페이지
	public List<AlcoholBean> getNewAlcohol() {
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetNewAlcohol");
		return lists;
	}
	
	/* 0919 추가 */
	public List<AlcoholBean> getNewSnack() {
			List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
			lists = sqlSessionTemplate.selectList(namespace+".GetNewSnack");
			return lists;
	}
	
	public int getTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		return totalCount;
	}
	
	public List<AlcoholBean> getAllProduct(Map<String, String> map, Paging pageInfo) {
		//페이징
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
				
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllProduct",map,rowBounds);
		return lists;
	}
	
	public int getAllSearchProduct(String keyword) {
		int num = sqlSessionTemplate.selectOne(namespace+".GetAllSearchProduct",keyword);
		return num;
	}
	
	
	
	//관리자 안주
	public void insertSnack(AlcoholBean alcohol) {
		sqlSessionTemplate.insert(namespace+".InsertSnack", alcohol);
	}
	
	public int getTotalCount2(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount2",map);
		return totalCount;
	}

	public List<AlcoholBean> getAllSnack(Map<String, String> map, Paging pageInfo) {
		//페이징
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllSnack",map,rowBounds);
		return lists;
	}

	public AlcoholBean getSnackByNum(String num) {
		AlcoholBean alcohol = sqlSessionTemplate.selectOne(namespace+".GetSnackByNum", num);
		return alcohol;
	}

	public void updateSnack(AlcoholBean alcohol) {
		sqlSessionTemplate.update(namespace+".UpdateSnack", alcohol);
		
	}

	public void deleteSnack(String num) {
		sqlSessionTemplate.delete(namespace+".DeleteSnack", num);
		
	}
	
	//0926 판매자 추가
	public void updateSnackAppr(String num) {
		sqlSessionTemplate.update(namespace+".UpdateSnackAppr", num);
		
	}
	
	public int getTotalCount2D(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount2D",map);
		return totalCount;
	}
	
	public List<AlcoholBean> getAllSnackD() {	
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllSnackD");
		return lists;
	}
	
	
	
	
	
	
	//혜인
	/*
	 * public AlcoholBean getAlcoholByNum(String num){ AlcoholBean alcohol =
	 * sqlSessionTemplate.selectOne(namespace+".GetAlcoholByNum", num); return
	 * alcohol; }
	 */
	
	public void updateStock(int num, int qty) {
		AlcoholBean alcohol = new AlcoholBean();
		alcohol.setNum(String.valueOf(num));
		alcohol.setStock(String.valueOf(qty));
		
		System.out.println("refund num alcoldao :"+alcohol.getNum());
		System.out.println("refund stock alcoldao :"+alcohol.getStock());
		sqlSessionTemplate.update(namespace+".UpdateStock", alcohol);
	}

	public List<ShoppingInfo> getOrderDetail(String orderid) {
		List<ShoppingInfo> lists = sqlSessionTemplate.selectList(namespace+".GetOrderDetail", orderid);
		return lists;
	}
	
	public void updateStock2(Integer num, int qty) {
		AlcoholBean alcohol = new AlcoholBean();
		alcohol.setNum(String.valueOf(num));
		alcohol.setStock(String.valueOf(qty));
		
		System.out.println("refund num alcoldao :"+alcohol.getNum());
		System.out.println("refund stock alcoldao :"+alcohol.getStock());
		sqlSessionTemplate.update(namespace+".UpdateStock2", alcohol);		
	}

	/*
	 * public void deleteAlcohol(String num) {
	 * sqlSessionTemplate.delete(namespace+".DeleteAlcohol", num); }
	 */

	
	//세은
	/*
	 * public int getTotalCount(Map<String, String> map) { int result =
	 * sqlSessionTemplate.selectOne(namespace + ".GetTotalCount", map); return
	 * result; }
	 */

	public List<AlcoholBean> getAlcoholList(Paging pageInfo, Map<String, String> map) {
		List<AlcoholBean> lists = new ArrayList<AlcoholBean>();

		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());

		lists = sqlSessionTemplate.selectList(namespace + ".GetAlcoholList", map, rowBounds);

		return lists;

	}

	public void productInsert(AlcoholBean alcohol) {
		sqlSessionTemplate.insert(namespace + ".alcoholInsert", alcohol);
	}

	public AlcoholBean getAlcohol(String num) {
		AlcoholBean alcohol = sqlSessionTemplate.selectOne(namespace + ".GetAlcohol",
				Integer.parseInt(num));
		return alcohol;
	}
	
//

	public List<ReviewBean> getReview1(String ref) {
		List<ReviewBean> list = new ArrayList<ReviewBean>();

		list = sqlSessionTemplate.selectList(namespace + ".GetReview");
		System.out.println("------------ review dao--------");
		System.out.println(ref);
		for (ReviewBean review : list) {
			System.out.println(review);
		}
		return list;
	}
	
	public List<ReviewBean> getReview(String num) {
		List<ReviewBean> list = sqlSessionTemplate.selectList(namespace + ".GetReview", Integer.parseInt(num));
		return list;
	}
	
	public void replyInsert(ReviewBean review) {
		sqlSessionTemplate.insert(namespace + ".replyInsert", review);
	}
	
	public int updateRecomm(int rid) {
		return sqlSessionTemplate.insert(namespace + ".updateRecomm", rid);
	}
	
	public int selectRecomm(int rid) {
		return sqlSessionTemplate.selectOne(namespace + ".selectRecomm", rid);
	}
	
	public int checkBuyFlag(String memberID, int productID) {
		// productID : �긽�뭹肄붾뱶
		System.out.println("productId : " + productID);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberID", memberID);
		map.put("productID", Integer.toString(productID));
		return sqlSessionTemplate.selectOne(namespace + ".SelectCheckbuyFlag", map);
	}
	
	public int checkDuplecateReview(String memberID, int productID) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberID", memberID);
		map.put("productID", Integer.toString(productID));
		
		return sqlSessionTemplate.selectOne(namespace + ".CheckDuplecateReview", map);
	}


//
	
	public int updateHeart(int memberId, int productId) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		
		return sqlSessionTemplate.insert(namespace + ".insertHeart", map);
	}
	
	public int checkHeart(int memberID, int productID) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberID", memberID);
		map.put("productID", productID);
		return sqlSessionTemplate.selectOne(namespace + ".checkHeart", map);
	}
	
	

	//2023-04-30 찜하기
	public void heartInsert(HeartBean bean) {
		sqlSessionTemplate.insert(namespace + ".HeartInsert", bean);
	
	}

	public HeartBean recordExist(int num) {
		//System.out.println("회원번호"+num);
		HeartBean bean = sqlSessionTemplate.selectOne(namespace + ".RecordExist", String.valueOf(num));
		//System.out.println(result); //mybatis String으로 넘겨 줘야 함, int 에러
		return bean;
		
	}

	public void heartUpdate(HeartBean bean) {
		sqlSessionTemplate.update(namespace+".HeartUpdate", bean);
		
	}


	
	
//




}
