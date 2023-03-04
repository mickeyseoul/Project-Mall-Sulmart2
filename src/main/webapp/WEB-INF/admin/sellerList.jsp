<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="main_top.jsp" %>
<!-- sellerList.jsp<br> -->

<hr>
    
<center>
<h2><b>나의 판매 상품</b></h2>

<hr>

<center>
	<!-- 승인 대기 -->
	<table style="width: 80%;" class="table table-sm">
		<tr bgcolor="#EAEAEA"><td colspan="9" align="center" style="font-weight: bold;">승인 대기 리스트 (총 ${ fn:length(myDlists) } 개)</font></td></tr>
		<tr>
			<th>이미지</th>
			<th>상품명</th>
			<th>카테고리</th>
			<th>브랜드</th>
			<th>원산지</th>
			<th>가격</th>
			<th>상품갯수</th>
			<th>상품설명</th>
			<th>승인여부</th>
		</tr>
	
		<c:if test="${ fn:length(myDlists) eq 0}">
			<tr>
				<td colspan="9" align="center">등록된 상품이 없습니다.</td>
			</tr>
		</c:if>
	
		<c:forEach var="alcohol" items="${ myDlists }" varStatus="i">
			<tr>
				<td><img src="<%= request.getContextPath() %>/resources/${ alcohol.image }"
						width="50" height="50"></td>
				<td>${ alcohol.name }</td>
				<td>${ alcohol.category }</td>
				<td>${ alcohol.brand }</td>
				<td>${ alcohol.country }</td>
				<td>${ alcohol.price }</td>
				<td>${ alcohol.stock }</td>
				<td>${ alcohol.content }</td>
				<td>
					<font color="red">승인대기</font>
				</td>
			</tr>
		</c:forEach>
		<tr>
				<td colspan="9" height="20" align="center">${ pageInfo.pagingHtml }</td>
		</tr>
	</table>




	<!-- 상품 리스트 -->
	<table style="width: 80%;" class="table table-sm">
	<tr class="table-info"><td colspan="9" align="center" style="font-weight: bold;"> ${ loginInfo.id }님의 판매 상품 리스트 (총 ${ totalCountA } 개)</font></td></tr>
	
	<c:if test="${ fn:length(listsA) eq 0}">
		<tr>
			<td colspan="8" align="center">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>
	
	<c:forEach var="alcohol" items="${ listsA }" varStatus="i">
		<c:if test="${ i.count ne 1 }">
			<tr>
				<td colspan="8"></td>
			</tr>
		</c:if>
			<tr style="font-size: 11pt; font-weight: bold;">
				<td>
					<c:if test="${ alcohol.flag eq true }">
						<font color="red" style="font-size: 7pt;">※유통기한 임박<br></font>	
						<font color="red" style="font-size: 7pt;">
						<fmt:parseDate var="exp_date" pattern="yyyy-MM-dd" value="${ alcohol.exp_date }"/>
						<fmt:formatDate value="${ exp_date }" pattern="yyyy-MM-dd"/>
						</font>					
					</c:if>
				</td>
				<td>상품코드</td>
				<td colspan="3">상품명</td>
				<td colspan="2">재고
				</td>
				<td rowspan="4" style="font-size: 11pt; font-weight: bold;">
					<a href="updateSnack.ad?num=${ alcohol.num }">수정</a>
					<a href="deleteSnack.ad?num=${ alcohol.num }">삭제</a>
				</td>
			</tr>
			<tr>
				<td rowspan="3">
					<img src="<%= request.getContextPath() %>/resources/${ alcohol.image }"
						width="100" height="100">
				</td>
				<td>${ alcohol.code }</td>
				<td colspan="3">${ alcohol.name }</td>
				<td colspan="3">${ alcohol.stock }
				</td>
			</tr>
			<tr style="font-size: 11pt; font-weight: bold;">
				<td>카테고리</td>
				<td>브랜드</td>
				<td>원산지</td>
				<td>가격</td>
				<td>포인트</td>
				<!-- <td>스펙</td> -->
			</tr>
			<tr>
				<td>${ alcohol.category }</td>
				<td>${ alcohol.brand }</td>
				<td>${ alcohol.country }</td>
				<td>${ alcohol.price }원</td>
				<td>${ alcohol.point }</td>
				<%-- <td>${ alcohol.spec }</td> --%>
			</tr>	
	</c:forEach>
		<tr>
				<td colspan="8" height="20" align="center">${ pageInfoA.pagingHtml }</td>
		</tr>
	</table>
</center>

<%@ include file="../mall/main_bottom.jsp" %>