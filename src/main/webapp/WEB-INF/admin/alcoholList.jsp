<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- alcoholList.jsp<br> -->
<%@ include file="main_top.jsp"%>

<style>
	#ta {
		float: left;
		margin-left: 10;
		/* margin-top: 37; */
	}
	#ta2{
		margin-top: -37;
	}
	.err {
		font-size: 9px;
		font-weight: bold;
		color: red;
	}
</style>

<br>
<hr>
    
<center>
<h2><b>주류 상품 관리</b></h2>

<hr>

	<!-- 상품 등록 -->
	<form:form commandName="alcohol" action="insertAlcohol.ad" method="post"
		enctype="multipart/form-data">
		<table id="ta" style="width: 38%;" class="table table-sm">
		<tr class="table-info"><td colspan="2" align="center" style="font-weight: bold;">주류 상품 등록</font></td></tr>
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
			<!-- <tr>
				<td>스펙</td>
				<td><select name="spec">
						<option value="">선택
						<option value="BEST">BEST
						<option value="STEADY">STEADY
				</select></td>
			</tr> -->
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
				<td colspan="2" align="center"><input type="submit" value="등록" class="btn btn-primary btn-sm"></td>
			</tr>
		</table>
		<br>
	</form:form>


	<!-- 상품 리스트 -->
	<table style="width: 57%;" class="table table-sm" id="ta2">
	<tr class="table-info"><td colspan="8" align="center" style="font-weight: bold;">주류 상품 리스트 (총 ${ totalCount } 개)</font></td></tr>
	<c:if test="${ fn:length(lists) eq 0}">
		<tr>
			<td colspan="6" align="center">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>
	
		<c:forEach var="alcohol" items="${ lists }" varStatus="i">
			<c:if test="${ i.count ne 1 }">
			<tr>
				<td colspan="8"></td>
			</tr>
		</c:if>	
			<tr style="font-size: 10pt; font-weight: bold;">
				<td rowspan="4"><img
					src="<%= request.getContextPath() %>/resources/${ alcohol.image }"
					width="100" height="100"></td>
				<td>상품코드</td>
				<td colspan="3">상품명</td>
				<td colspan="2">재고
				</td>
				<td rowspan="4" style="font-size: 11pt; font-weight: bold;">
					<a href="updateAlcohol.ad?num=${ alcohol.num }">수정</a>
					<a href="deleteAlcohol.ad?num=${ alcohol.num }">삭제</a>
				</td>
			</tr>
			<tr>
				<td>${ alcohol.code }</td>
				<td colspan="3">${ alcohol.name }</td>
				<td colspan="3">${ alcohol.stock }
				</td>
			</tr>
			<tr style="font-size: 9pt; font-weight: bold;">
				<td>카테고리</td>
				<td>브랜드</td>
				<td>원산지</td>
				<td>가격</td>
				<td colspan="2">포인트</td>
				<!-- <td>스펙</td> -->
			</tr>
			<tr>
				<td>${ alcohol.category }</td>
				<td>${ alcohol.brand }</td>
				<td>${ alcohol.country }</td>
				<td><fmt:formatNumber pattern="###,###" value="${ alcohol.price }"/>원</td>
				<td>${ alcohol.point }</td>
				<td>${ alcohol.spec }</td>
			</tr>
			
		</c:forEach>
		<tr>
			<td colspan="8" height="20" align="center">${ pageInfo.pagingHtml }</td>
		</tr>
	</table>
</center>

<%@ include file="../mall/main_bottom.jsp" %>
