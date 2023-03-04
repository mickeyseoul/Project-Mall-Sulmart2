<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>
<!-- updateForm.jsp -->
<br>
<script src="//cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		CKEDITOR.replace('content');
		CKEDITOR.config.height = 500;
		var content = CKEDITOR.instances.content.getData();
	});

	function back(num, pageNumber) {
		if (confirm("수정을 취소하시겠습니까?") == true) {
			location.href = "detail.no?num=" + num + "&pageNumber="
					+ pageNumber;
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

<center>
	<hr>
	<h2>(Notice 수정) ${notice.subject}</h2>
	<hr>
	<form:form commandName="no" action="update.no" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="num" value="${notice.num}">
		<input type="hidden" name="pageNumber" value="${pageNumber}">
		<hr>
		<table class="table table-hover" style="width: 1000px;">
			<tr>
				<td>카테고리</td>
				<td><select name="cate">
						<option value="공지사항"
							<c:if test="${fn:contains(notice.cate,'공지사항')}">selected</c:if>>공지사항
						
						<option value="이벤트"
							<c:if test="${fn:contains(notice.cate,'이벤트')}">selected</c:if>>이벤트
						
				</select> <form:errors cssClass="err" path="cate" /></td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td><select name="cateopen">
						<option value="전체공개"
							<c:if test="${fn:contains(notice.cateopen,'전체공개')}">selected</c:if>>전체공개
						
						<option value="비공개"
							<c:if test="${fn:contains(notice.cateopen,'비공개')}">selected</c:if>>비공개
						
				</select></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="hidden" name="writer" value="${loginInfo.id }">
					${loginInfo.id }</td>
				<!-- 로그인 정보로 변경, 수정불가 -->
			</tr>
			<tr>
				<td>* 제목</td>
				<td><input type="text" size="48" name="subject"
					value="${notice.subject }"> <form:errors cssClass="err"
						path="subject" /></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><textarea name="content" rows="10" cols="50"
						placeholder="내용을 작성해주세요.">${notice.content }</textarea> </td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><c:if test="${notice.image != null}">
						<img height=auto width=300
							src="<%=request.getContextPath()%>/resources/notice/${notice.image}">
					</c:if> <c:if test="${notice.image == null}">
						<small> 첨부된 파일이 없습니다.</small>
					</c:if><br> <br> <input type="file" name="upload"
					value="${notice.image}" multiple></td>
			</tr>
		</table>
		<br>
		<input type="button" value="이전" class="btn btn-secondary "
			onclick="back('${notice.num}','${pageInfo.pageNumber }')">
		<input type="submit" value="수정완료" class="btn btn-primary ">
	</form:form>
</center>
<%@ include file="../mall/main_bottom.jsp"%>