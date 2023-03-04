<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!-- mallSearchView.jsp<br> -->
<%@ include file="main_top.jsp" %>

<br>
<hr>
<center>

	<h2><b>상품 검색 조회</b></h2>

<hr>
	<h4>'<font color="blue">${ keyword }</font>'에 대한 검색결과</h4>
	<span>총 ${ num } 건</span>
<br><br>

	<!-- 상품 -->
	<table class="table table-sm" style="width: 80%;">
		<c:if test="${ fn:length(lists) eq 0 }">
			<tr>
				<td align="center">등록된 상품이 없습니다.</td>
			</tr>
		</c:if>

		<tr>
			<c:forEach var="alcohol" items="${ lists }" varStatus="i">
				<td>
				<a href="detail.al?num=${ alcohol.num }">

					<img src="<%=request.getContextPath()%>/resources/images/alcohol/${alcohol.image}"
					width="250" height="250">
					
				</a>	
					<br> ${ alcohol.name }<br>
					<fmt:formatNumber pattern="#,###" value="${ alcohol.price }"/>원</td>

				<c:if test="${ i.count % 4 == 0 }">
		</tr>
		<tr>
			</c:if>
			</c:forEach>
		</tr>
	</table>

	${ pageInfo.pagingHtml }

</center>

<%@ include file="main_bottom.jsp" %>