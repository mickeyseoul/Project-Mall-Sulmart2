<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- snackList.jsp<br> -->
<%@ include file="main_top.jsp"%>

<!-- <style>
	#ta{
		float: left;
		margin-left: 10;
		margin-top: 37;
	}
	.err {
		font-size: 9px;
		font-weight: bold;
		color: red;
	}
</style> -->

<br>
<hr>
    
<center>
<h2><b>안주 상품 관리</b></h2>

<hr>


<!-- 등록 요청 리스트 -->

	<table id="ta" style="width: 80%;" class="table table-sm">
		<tr bgcolor="#EAEAEA"><td colspan="9" align="center" style="font-weight: bold;">승인 요청 리스트 (총 ${ fn:length(lists) } 개)</font></td></tr>
			<tr>
			<th>이미지</th>
			<th>판매자</th>
			<th>상품명</th>
			<th>카테고리</th>
			<th>브랜드</th>
			<th>원산지</th>
			<th>가격</th>
			<th>상품설명</th>
			<th>승인</th>
		</tr>
	
		<c:if test="${ fn:length(lists) eq 0}">
			<tr>
				<td colspan="9" align="center">등록된 상품이 없습니다.</td>
			</tr>
		</c:if>
	
		<c:forEach var="alcohol" items="${ lists }" varStatus="i">
			<c:if test="${ alcohol.appr eq 0 }">
				<tr>
					<td><img src="<%= request.getContextPath() %>/resources/${ alcohol.image }"
							width="50" height="50"></td>
					<td>${ alcohol.memid }</td>
					<td>${ alcohol.name }</td>
					<td>${ alcohol.category }</td>
					<td>${ alcohol.brand }</td>
					<td>${ alcohol.country }</td>
					<td>${ alcohol.price }</td>
					<td>${ alcohol.content }</td>
					<%-- <td><input type="button" value="승인" onclick="goAppr(${ alcohol.num })"></td> --%>
					<td align="left"><input type="button" value="승인" onclick="javascript:location.href='insertSnack.ad?num=${alcohol.num}'"
					 class="btn btn-primary btn-sm"></td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
				<td colspan="9" height="20" align="center">${ pageInfo.pagingHtml }</td>
		</tr>
	</table>



<!-- 상품 리스트 -->
<table style="width: 80%;" class="table table-sm">
	<tr class="table-info"><td colspan="8" align="center" style="font-weight: bold;">안주 상품 리스트 (총 ${ totalCountA } 개)</font></td></tr>
	
	<c:if test="${ fn:length(listsA) eq 0}">
		<tr>
			<td colspan="8" align="center">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>
	
	<c:forEach var="alcohol" items="${ listsA }" varStatus="i">
		<c:if test="${ alcohol.appr eq 1 }">
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
				<td>판매자
				<td>재고
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
				<td>${ alcohol.memid }</td>
				<td>${ alcohol.stock }</td>
			</tr>
			<tr style="font-size: 11pt; font-weight: bold;">
				<td>카테고리</td>
				<td>브랜드</td>
				<td>원산지</td>
				<td>가격</td>
				<td>포인트</td>
				<td><!-- 스펙 --></td>
			</tr>
			<tr>
				<td>${ alcohol.category }</td>
				<td>${ alcohol.brand }</td>
				<td>${ alcohol.country }</td>
				<td>${ alcohol.price }원</td>
				<td>${ alcohol.point }</td>
				<td><%-- ${ alcohol.spec } --%></td>
			</tr>
		</c:if>
	</c:forEach>
		<tr>
				<td colspan="8" height="20" align="center">${ pageInfoA.pagingHtml }</td>
		</tr>
</table>
</center>

<%@ include file="../mall/main_bottom.jsp" %>
