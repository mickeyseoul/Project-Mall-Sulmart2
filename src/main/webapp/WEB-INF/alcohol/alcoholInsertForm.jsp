<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/common/common.jsp" %>
alcoholInsertForm.jsp<br>


<form:form commandName="alcohol" method="post" action="insert.al" enctype="multipart/form-data">
	<h1><span>상품 추가 화면</span></h1>
	<p>
		<label for="aname">*상품명</label>
		<input type="text" name="aname" id="aname" value="${alcohol.aname }">
		<form:errors cssClass="err" path="aname"/>
	</p>
	<p>
		<label for="aprice">*가격</label>
		<input type="text" name="aprice" id="aprice" value="${alcohol.aprice }">
		<form:errors cssClass="err" path="aprice"/>
	</p>
	<p>
		<label for="apoint">구매혜택</label>
		<input type="text" name="apoint" id="apoint" value="${alcohol.apoint }">
	</p>	
	<p>
		<label for="akind">배송비</label>
		<input type="text" name="akind" id="akind" value="${alcohol.akind }">
	</p>	
	<p>
		<label for="acode">*상품코드</label>
		<input type="text" name="acode" id="acode" value="${alcohol.acode}">
		<form:errors cssClass="err" path="acode"/>
	</p>	
	<p>
		<label for="abrand">브랜드</label>
		<input type="text" name="abrand" id="abrand" value="${alcohol.abrand }">
	</p>
		<p>
		<label for="acountry">제조국가</label>
		<input type="text" name="acountry" id="acountry" value="${alcohol.acountry }">
	</p>
	<p>
		<label for="upload">*그림 파일</label><!-- upload=a.jpg -->
		<input type="file" name="upload" id="upload" value="${alcohol.aimage }">
		<form:errors cssClass="err" path="aimage"/>
	</p>	
		<p>
		<input type="submit" value="추가하기" id="btnSubmit">		
	</p>
</form:form>