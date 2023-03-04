<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../common/common.jsp" %>
    <%@ include file="main_top.jsp" %>
    <!-- 장바구니 주문하기 또는 즉시구매하기 누르면 여기로 옴. orderList.jsp -->
   
     <div class="container"> 
 <hr>
 <br> 
<center>
<h2>결제내역서</h2>
</center>
<hr>

<form name="f" method="post" action="calculate.mall" >
		
<table align="center"  class="table table-hover" >
	<tr>
		<td colspan="9"  >
			<b>${loginInfo.name }(${loginInfo.id })</b>님의 결제내역서
		</td> <!--loginInfo 로그인한사람의 정보 -->
	</tr>

	<tr align="center" class="active" >
		
		<td><b>상품 번호</b></td>
		<td><b>상품 이미지</b></td>
		<td><b>상품명</b></td>
		<td><b>수량</b></td>
		<td><b>상품금액</b></td>
		<td><b>포인트</b></td>
		<td><b>합계</b></td>		
	</tr>
	
<c:forEach var="cart" items="${shopLists }"  > 
	<tr align="center">
		<td>${cart.num}</td>
		<td>
			<img height=50 width=50 
			src="<%=request.getContextPath()%>/resources/${cart.image}">  <!-- 그러면 쇼핑인포에서 목록을 더 늘려야됨?- -->
		</td>
		<td align="left">
			${cart.name}<br>
		</td>
		<td>
    		${cart.qty}개 
		</td>
		<td>
			<fmt:formatNumber pattern="#,###" value="${cart.price}"/>원
		</td>
   		<td> [${cart.pointAmount }]point</td>
		<td> <strong>
				<fmt:formatNumber pattern="#,###" value="${cart.priceAmount}"/>원
			</strong></td>  
</tr>
</c:forEach>
</table>
<hr>	

<br><br>

<table align="center" class="table table-hover">
	<tr>
		<td  align="right">	
		<br>
			상품금액 : <font color="#00C6ED">
			<fmt:formatNumber pattern="#,###" value="${totalPriceAmount}"/>원 </font><br>
			<img src="./resources/images/장바구니더하기이미지.PNG" height="35"><br>
			배송비 : <font color="#00C6ED">
			<fmt:formatNumber pattern="#,###" value="${deliveryCost}"/>원<br></font>
			<img src="./resources/images/장바구니결과이미지.PNG" height="30"><br>
			합계 : <font color="#00C6ED"><b>
			<fmt:formatNumber pattern="#,###" value="${finalPrice}"/>원</b></font>
		<br><br>
		</td>
	</tr>	
	<tr>	
		<td align="right">	
			적립예정 포인트 : [${totalPointAmount}]point
		</td>
	</tr>		

	<tr>	
		<td align="right">		
			<input type="submit" class="btn btn-info btn-lg" value="결제하기">
		</td>
	</tr>			
</table>

 </form>
<br><br>
    
 </div>