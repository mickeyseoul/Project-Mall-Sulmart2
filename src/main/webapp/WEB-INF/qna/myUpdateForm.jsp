<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>
<!-- updateForm.jsp -->
<br>
<script src="//cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
<script type="text/javascript">
	function back(num, pageNumber) {
		if (confirm("수정을 취소하시겠습니까?") == true) {
			location.href = "myDetail.qna?num=" + num + "&pageNumber=" + pageNumber;
		} else {
			return;
		}
	}
</script>
<style type="text/css">
.err {
	color: red;
	font-weight: bold;
}
</style>

<%
String[] cateList = { "회원/정보관리", "배송", "반품/환불/교환/AS", "영수증/증빙서류", "상품/이벤트", "기타" };
%>
<c:set var="cateList" value="<%=cateList%>"></c:set>

<center>
	<hr>
	<h2>(QNA 수정) ${qna.subject} </h2>
	<hr>
	<form:form commandName="qna" action="myUpdate.qna" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="num" value="${qna.num}">
		<input type="hidden" name="pageNumber" value="${pageNumber}">
		<table class="table table-hover" style="width: 1000px;">
			<tr>
				<td>카테고리</td>
				<td><select name="cate">
						<c:forEach var="i" begin="0" end="${fn:length(cateList)-1 }">
							<option value="${cateList[i]}"
								<c:if test="${cateList[i] eq qna.cate }">selected</c:if>>${cateList[i]}
						</c:forEach>
				</select> <form:errors cssClass="err" path="cate" /></td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td><select name="cateopen">
						<option value="전체공개"
							<c:if test="${fn:contains(qna.cateopen,'전체공개')}">selected</c:if>>전체공개

						
						<option value="비공개"
							<c:if test="${fn:contains(qna.cateopen,'비공개')}">selected</c:if>>비공개
						
				</select></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="hidden" name="writer" value="${loginInfo.id }">
					${loginInfo.id }</td>
			</tr>
			<tr>
				<td>* 제목</td>
				<td><input type="text" name="subject" value="${qna.subject }">
					<form:errors cssClass="err" path="subject" /></td>
			</tr>
			<tr>
				<td>* 본문</td>
				<td><textarea name="content" rows="10" cols="50"
						placeholder="내용을 작성해주세요.">${qna.content }</textarea> 
						<form:errors cssClass="err" path="content" /></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><c:if test="${qna.image != null}">
						<img height=auto width=300
							src="<%=request.getContextPath()%>/resources/qna/${qna.image}">
					</c:if> <c:if test="${qna.image == null}">
						<small> 첨부된 파일이 없습니다.</small>
					</c:if><br> <br> <input type="file" name="upload"
					value="${qna.image}"></td>
			</tr>
		</table>

		<input type="button" value="이전" class="btn btn-secondary"
			onclick="back('${qna.num}','${pageInfo.pageNumber }')">
		<input type="submit" value="수정완료" class="btn btn-primary ">
	</form:form>
</center>
<%@ include file="../mall/main_bottom.jsp"%>