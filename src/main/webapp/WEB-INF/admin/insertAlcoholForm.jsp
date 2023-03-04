<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
insertAlcoholForm.jsp<br>

<!-- 상품 등록 -->
	<form:form commandName="alcohol" action="insertAlcohol.ad" method="post"
		enctype="multipart/form-data">
		<table border="1">
		<caption>주류 상품 등록</caption>
			<tr>
				<td>카테고리</td>
				<td><select name="category">
						<option value="">선택
							<c:forEach var="list" items="${ lists2 }">
								<option value="${ list.cate }">${ list.cate }
							</c:forEach>
				</select>
				<form:errors cssClass="err" path="category"></form:errors>
				</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input name="name" value="애플리즈 헤베 9도 330ml">
				<form:errors cssClass="err" path="name"></form:errors>
				</td>
			</tr>
			<tr>
				<td>브랜드</td>
				<td><input name="brand" value="한국애플리즈"></td>
			</tr>
			<tr>
				<td>원산지</td>
				<td><input name="country" value="미국"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input name="price" value="5700">원</td>
			</tr>
			<tr>
				<td>적립포인트</td>
				<td><input name="point" value="57">point</td>
			</tr>
			<tr>
				<td>재고수량</td>
				<td><input name="stock" value="200">개</td>
			</tr>
			<tr>
				<td>스펙</td>
				<td><select name="spec">
						<option value="">선택
						<option value="BEST">BEST
						<option value="STEADY">STEADY
				</select></td>
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea name="content" rows="10" cols="50">애플리즈 헤베 9도 330ml</textarea></td>
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
				<td><input type="text" name="exp_date" value="2024-03-10"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록"></td>
			</tr>
		</table>
		<br>
	</form:form>