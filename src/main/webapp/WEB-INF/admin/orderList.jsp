<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- orderList.jsp<br> -->
<%@ include file="main_top.jsp"%>

<br>
<hr>
    
<center>
<h2><b>주문 관리</b></h2>

<hr>


<form action="orderList.ad" method="post">
	<select name="whatColumn">
		<option value="id">아이디
		<option value="orderid">주문번호
		<option value="month" <c:if test="${ whatColumn eq 'month' }">selected</c:if>>월별
	</select>
	<input type="text" name="keyword" value="${ keyword }">
	<input type="submit" value="검색" class="btn btn-primary btn-sm">
</form>

<table class="table table-hover" style="width: 85%">
	<tr align="center" class="table-info">
		<th>주문번호</th>
		<th>구입날짜</th>
		<th>아이디</th>
		<th>이미지</th>
		<th>상품명</th>
		<th>단가</th>
		<th>상품갯수</th>
		<th>결제금액</th>
		<th>주문상태</th>
	</tr>
	
	<c:forEach var="list" items="${ lists }">
	<tr align="center">
		<td>${ list.num }</td>
		<td>
		<fmt:parseDate var="orderdate" pattern="yyyy-MM-dd" value="${ list.orderdate }"/>
		<fmt:formatDate pattern="yyyy-MM-dd" value="${ orderdate }"/>
		<%-- ${ list.orderdate } --%>
		</td>
		<td>${ list.id }</td>
		<td><img src="<%= request.getContextPath()%>/resources/${ list.image }" width="50" height="50"></td>
		<td>${ list.name }</td>
		<td><fmt:formatNumber pattern="###,###" value="${ list.price }"/>원</td>
		<td>${ list.qty }</td>
		<td><fmt:formatNumber pattern="###,###" value="${ list.priceAmount }"/>원</td>
		<td>
		<c:if test="${list.orderState == '주문취소'}">
			<font color="red">${ list.orderState}</font>
		</c:if>
		<c:if test="${list.orderState == '배송완료'}">
			${ list.orderState}
		</c:if>
		<c:if test="${list.orderState == '배송준비'}">
		<button type="button" id="button1" class="btn btn-outline-danger btn-sm" onclick="location.href='orderState.ad?orderid=${list.num}'">${ list.orderState }</button>
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<br>

${ pageInfo.pagingHtml }


</center>

<%@ include file="../mall/main_bottom.jsp" %>