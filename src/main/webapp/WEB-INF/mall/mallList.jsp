<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../common/common.jsp" %>
    <%@ include file="main_top.jsp" %>
<!-- mallList.jsp<br> -->

<br>
<hr>
    
<center>
<h2><b>장바구니</b></h2>
</center>
<hr>

<div class="container">


<table align="center"  class="table table-hover">
	<tr>
		<td colspan="9"  >
			주문자 정보 :  <b>${loginInfo.name}(${loginInfo.id })</b> 님의 장바구니
		</td> <!--loginInfo 로그인한사람의 정보 -->
	</tr>

	<tr align="center" class="active" >
		
		<td ><b>상품번호</b></td>
		<td><b>상품이미지</b></td>
		<td><b>상품명</b></td>
		<td><b>수량</b></td>
		<td><b>상품금액</b></td>
		<td><b>포인트</b></td>
		<td><b>배송비</b></td>
		<td><b>합계</b></td>
		<td><b>삭제</b></td>
		
	</tr>
	<!-- List<ShioppingInfo> shopLists = new ArrayList<ShioppingInfo>(); -->
<c:forEach var="cart" items="${shopLists }"  > 

	<tr align="center">
		<td>${cart.num}</td>
		<td>
			<img height=60 width=60 
			src="<%=request.getContextPath()%>/resources/images/alcohol/${cart.image}">  <!-- 그러면 쇼핑인포에서 목록을 더 늘려야됨?- -->
		</td>
		<td align="left">
			<a href="detail.al?num=${cart.num}"> ${cart.name}</a><br>
		</td>
		<td>
			<form name="f" method="post" action="cartEdit.mall">
    			<input type="hidden" name="num" value="${cart.num}" >
    			<input name="oqty" size="2" value="${cart.qty}">개 
    			<!-- pb.getPqty() 재고수량자리에 주문수량 아까 넣었음.  -->
    		<input type="submit" value="변경" class="btn btn-outline-info btn-sm">
    		</form>		
		</td>
		<td>
			<fmt:formatNumber pattern="#,###" value="${cart.price}"/>원
		</td>
   		<td> [${cart.pointAmount }]point</td>
		<td> <fmt:formatNumber pattern="#,###" value="${cart.delivery }"/>원
		</td> 	 

		<td> <strong>
				<fmt:formatNumber pattern="#,###" value="${cart.priceAmount}"/>원
			</strong></td>  
		<td>
			<input type="button" value="삭제" class="btn btn-outline-info btn-sm"
			onclick="location.href='cartDelete.mall?num=${cart.num}'">
		</td>

	</tr>
</c:forEach>

</table>
<br><br>
<hr>	
<br><br>


<!-- 박이랑 추가 -->
	<c:if test="${ lists ne null }">
		<table class="table table-bordered border-primary" style="width: 80%">
			<tr align="center">
				<td colspan="3"><h4><b>상품 추천</b></h4></td>
			</tr>
			<tr align="center">
				<td colspan="3">
					<img src="<%=request.getContextPath()%>/resources/images/alcohol/${ product.image }" width="60" height="60"> <br> 
					<font style="font-weight: bold;">${ product.name }</font>에 어울리는 <br>
					<c:if test="${ product.product eq 1 }">
  						이런 안주는 어떠세요?<br><br>
					</c:if> 
					<c:if test="${ product.product eq 2 }">
  						이런 술은 어떠세요?
					</c:if>
				</td>
			</tr>
			<tr >
				<c:forEach var="recommand" items="${ lists }">
					<tr >
						<td align="center">
							<a href="detail.al?num=${ recommand.num }"> 
							<img src="<%=request.getContextPath()%>/resources/images/alcohol/${ recommand.image }"
								width="150" height="150" title="${ recommand.name }"><br>
							</a>
						</td>
						<td>${ recommand.name }<br> <fmt:formatNumber
								pattern="#,###" value="${ recommand.price }" />원
						</td>
						<td width="30%">
							<form action="add.mall" method="post">
								<input type="hidden" name="num" value="${ recommand.num }">
								<input type="text" name="orderqty" size="2" value="1">개
								<input type="submit" value="장바구니" class="btn btn-outline-warning btn-sm"
									onclick="javascript:location.href='add.mall'">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
	<!-- 여기까지 -->


	
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
			<input type="button" class="btn btn-light btn-lg" value="계속 쇼핑"  onclick="location.href='main.mall'">
 	 		<input type="button" class="btn btn-info btn-lg" value="주문하기" onclick="location.href='odlist.mall'">    
		</td>
	</tr>			
</table>

<table class="table table-hover">
	<tr>
		<td>
		<h6><b>이용안내</b></h6>
		- 선택하신 상품의 수량을 변경하시려면 수량변경 입력 후 [변경] 버튼을 누르시면 됩니다.<br>
		- [계속쇼핑] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.<br>
		- 3만원이상 주문시 배송비는 무료입니다.<br>
		- 구매시 포인트는 상품가격의 10%가 적립됩니다. <br>	
		</td>
	</tr>
</table>

	</div>

    <%@ include file="main_bottom.jsp" %>