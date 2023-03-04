<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

alcoholList.jsp <br>

<center>	
	<form>
		<h2>상품 리스트 화면</h2>
		<br>
		<h2>ProductList.jsp</h2>

		<select name="whatColumn">
			<option value="">전체검색
			<option value="name">상품명
			<option value="contents">상품설명
		</select> 
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>

	<table border="1">
		<tr>
			<td colspan="6" align="right">
				<!-- <input type="button" value="추가하기" onclick="location.href='insertProduct.prd'"> -->
				<input type="button" value="추가하기" onclick="insert()">
			</td>
		</tr>
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>상품설명</th>
			<th>가격</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<c:forEach var="a" items="${lists }">
			<tr>
				<td>${a.num }</td>
				<td><a href="list.al?num=3"></a></td>
				<td>${a.name}</td>
				<td>${a.contents }</td>
				<td>${a.price }</td>
				<td><a>삭제</a></td>
				<td>
					<input type="button" value="수정">
				</td>
			</tr>
		</c:forEach>
	</table>
		${pageInfo.pagingHtml}
</center>