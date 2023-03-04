<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- sellerInsertForm.jsp<br> -->
<%@ include file="main_top.jsp"%>

<hr>
    
<center>
<h2><b>판매 상품 등록</b></h2>

<hr>


	<!-- 상품 등록 -->
	<form:form commandName="alcohol" action="sellerInsert.ad" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="memid" value="${ loginInfo.id }">
		<table id="ta" style="width: 38%;" class="table table-sm">
		<tr class="table-info"><td colspan="2" align="center" style="font-weight: bold;">판매 상품 등록</font></td></tr>
			<tr>
				<td>카테고리</td>
				<td><select name="category">
						<option value="">선택
							<c:forEach var="list" items="${ lists3 }">
								<option value="${ list.category }">${ list.category }
							</c:forEach>
				</select>
				<form:errors cssClass="err" path="category"></form:errors>
				</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input name="name" value="미니모짜렐라 120g">
				<form:errors cssClass="err" path="name"></form:errors>
				</td>
			</tr>
			<tr>
				<td>브랜드</td>
				<td><input name="brand" value="탈리푸드"></td>
			</tr>
			<tr>
				<td>원산지</td>
				<td><input name="country" value="대한민국"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input name="price" value="5300">원</td>
			</tr>
			<tr>
				<td>적립포인트</td>
				<td><input name="point" value="53">point</td>
			</tr>
			<tr>
				<td>재고수량</td>
				<td><input name="stock" value="200">개</td>
			</tr>
			<tr>
				<!-- <td>스펙</td>
				<td><select name="spec">
						<option value="">선택
						<option value="BEST">BEST
						<option value="STEADY">STEADY
				</select></td> -->
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea name="content" rows="10" cols="50">포크로 콕 찍어 즐기는 프레시 치즈</textarea></td>
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
				<td>입고일자</td>
				<td><input type="text" name="input_date" value="2022-09-22"></td>
			</tr>
			<tr>
				<td>유통기한</td>
				<td><input type="text" name="exp_date" value="2023-02-10"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록" class="btn btn-primary btn-sm"></td>
			</tr>
		</table>
		<br>
	</form:form>
	
</center>

<%@ include file="../mall/main_bottom.jsp" %>