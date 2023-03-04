<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!-- mallAlcoholView.jsp<br> -->
<%@ include file="main_top.jsp" %>

<!-- </td></tr>
<tr height="40"></tr>
<tr><td> -->

<br>
<hr>
<center>


		<!-- <h4>주류 상품 전체보기</h4> -->
		<c:if test="${ keyword eq null }">
			<h2><b>주류 전체상품</b></h2>
		</c:if>
		<!-- 'null'이라고 넘어옴 -->
		<c:if test="${ keyword eq 'null' }">
			<h2><b>주류 전체상품</b></h2>
		</c:if>
		<c:forEach var="category" items="${ catelists }">
			<c:if test="${ keyword eq category.cate }">
				<h2><b>${ category.cate }</b></h2>
			</c:if>
		</c:forEach>
		
<hr>
		
		<!-- 카테고리 -->
		<ul class="nav justify-content-center">
		  <li class="nav-item">
		  	<c:if test="${ keyword eq null || keyword eq 'null' }">
		    <a class="nav-link disabled" href="mallAlcoholView.mall">전체상품</a>
		    </c:if>
		    <c:if test="${ keyword ne null && keyword ne 'null' }">
		    <a class="nav-link" href="mallAlcoholView.mall">전체상품</a>
		    </c:if>
		  </li>
		  <c:forEach var="category" items="${ catelists }">
			  <li class="nav-item">
				  <c:if test="${ keyword eq category.cate }">
			    	<a class="nav-link disabled" href="mallAlcoholView.mall?&whatColumn=category&keyword=${ category.cate }">${ category.cate }</a>
			      </c:if>
			      <c:if test="${ keyword ne category.cate }">
				    <a class="nav-link" href="mallAlcoholView.mall?&whatColumn=category&keyword=${ category.cate }">${ category.cate }</a>
				  </c:if>
			  </li>
		  </c:forEach>
		</ul>

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
						width="250" height="250"><br> ${ alcohol.name }
					</a>
					<br>
					<fmt:formatNumber pattern="#,###" value="${ alcohol.price }"/>원
				</td>

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
