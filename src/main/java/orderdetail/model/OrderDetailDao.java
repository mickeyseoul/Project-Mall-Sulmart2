package orderdetail.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("OrderDetailDao")
public class OrderDetailDao {
	
	private final String namespace="orderdetail.model.OrderDetail";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insertData(OrderDetailBean orderdetail) {
		sqlSessionTemplate.insert(namespace+".InsertData" , orderdetail);
	}
	
	public List<OrderDetailBean> selectOrderDetail(int orderid) {
		List<OrderDetailBean> detail = sqlSessionTemplate.selectList(namespace+".SelectOrderDetail", orderid);
		return detail;
	}

	


}
