<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!-- updateAlcoholForm.jsp<br> -->
<%@ include file="main_top.jsp"%>

<br>
<hr>
    
<center>
<h2><b>주류 상품 수정</b></h2>

<hr>


	<form action="updateAlcohol.ad" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="num" value="${ alcohol.num }">
		<input type="hidden" name="originImg" value="${ alcohol.image }">
		<input type="hidden" name="originImg2" value="${ alcohol.contentImage }">
		
		<table  class="table table-sm" style="width: 40%">
			<tr>
				<td>카테고리</td>
				<td><select name="category">
						<option value="">선택
							<c:forEach var="list" items="${ lists }">
								<option value="${ list.cate }" <c:if test="${ list.cate eq alcohol.category }">selected</c:if>>${ list.cate }
							</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input name="name" value="${ alcohol.name }"></td>
			</tr>
			<tr>
				<td>브랜드</td>
				<td><input name="brand" value="${ alcohol.brand }"></td>
			</tr>
			<tr>
				<td>원산지</td>
				<td><input name="country" value="${ alcohol.country }"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input name="price" value="${ alcohol.price }">원</td>
			</tr>
			<tr>
				<td>적립포인트</td>
				<td><input name="point" value="${ alcohol.point }">point</td>
			</tr>
			<tr>
				<td>재고수량</td>
				<td><input name="stock" value="${ alcohol.stock }">개</td>
			</tr>
			<tr>
				<td>스펙</td>
				<td><select name="spec">
						<option value="">선택
						<option value="BEST" <c:if test="${ alcohol.spec eq 'BEST' }">selected</c:if>>BEST
						<option value="STEADY" <c:if test="${ alcohol.spec eq 'STEADY' }">selected</c:if>>STEADY
				</select></td>
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea name="content" rows="10" cols="50">${ alcohol.content }</textarea></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><input type="file" name="upload"></td>
			</tr>
			<tr>
				<td>내용이미지</td>
				<td><input type="file" name="upload2"></td>
			</tr>
			<tr>
				<fmt:parseDate var="DateInput_date" value="${ alcohol.input_date }"
					pattern="yyy-MM-dd" />
				<fmt:formatDate var="input_date" pattern="yyyy-MM-dd"
					value="${ DateInput_date }" />
				<td>입고일자</td>
				<td><input type="text" name="input_date"
					value="${ input_date }"></td>
			</tr>
			<tr>
				<td>유통기한</td>
				<fmt:parseDate var="DateExp_date" value="${ alcohol.exp_date }"
					pattern="yyy-MM-dd" />
				<fmt:formatDate var="exp_date" pattern="yyyy-MM-dd"
					value="${ DateExp_date }" />
				<td><input type="text" name="exp_date" value="${ exp_date }"></td>
			</tr>
		</table>
		<br> <input type="submit" value="수정">
	</form>

</center>

<%@ include file="../mall/main_bottom.jsp" %>