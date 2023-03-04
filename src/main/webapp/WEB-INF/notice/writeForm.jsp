<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>

<!-- writeForm.jsp -->
<br>
<script src="https://code.jquery.com/jquery-1.6.1.min.js"
	integrity="sha256-x4Q3aWDzFj3HYLwBnnLl/teCA3RaVRDGmZKjnR2P53Y="
	crossorigin="anonymous"></script>
<script src="//cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
<script type="text/javascript">
	/* function write() {
	 if (confirm("등록하시겠습니까 ?") == true) {
	 alert("문의글이 등록되었습니다.");
	 location.href = "write.no";
	 } else {
	 return;
	 }
	 }  */
	function back() {
		if (confirm("목록으로 돌아가시겠습니까?") == true) {
			location.href = "list.no";
		} else {
			return;
		}
	}

	$(document).ready(function() {
		CKEDITOR.replace('content');
		CKEDITOR.config.height = 500;
		var content = CKEDITOR.instances.content.getData();

		create();

		$("#refreshBtn").click(function(e) {
			e.preventDefault();
			create();
		});

		$("#confirmBtn").click(function(e) {
			e.preventDefault();
			$("#frm").submit();
		});

		function create() {
			$("#captcha img").attr("src", "/captcha?" + Math.random());
		}
	});
</script>
<style type="text/css">
.err {
	color: red;
	font-weight: bold;
}

#captcha {
	width: 230px;
	height: 70px;
	border: 1px #DCDCDC;
	text-align: center;
	padding: 5px;
}

table {
	border-top: 1px solid #DCDCDC;
	border-collapse: collapse;
}

th, td {
	border-bottom: 1px solid #DCDCDC;
	padding: 10px;
}
</style>

<center>
	<hr>
	<h2>NOTICE</h2>
	<hr>
	<form:form commandName="notice" action="write.no" method="post"
		enctype="multipart/form-data">
		<table class="table table-hover" style="width: 1000px;">
			<tr>
				<td>카테고리</td>
				<td><select name="cate">
						<option value="공지사항">공지사항</option>
						<option value="이벤트">이벤트</option>
				</select></td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td><select name="cateopen">
						<option value="전체공개">전체공개
						<option value="비공개">비공개
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
						placeholder="내용을 작성해주세요.">${notice.content }</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="upload" value="" multiple></td>
			</tr>
		</table>
		<br>
		<br>
		<input type="button" value="이전" onclick="back()"
			class="btn btn-secondary">
		<input type="submit" value="등록" class="btn btn-primary">
		<!-- 	<input type="button" value="등록" onclick="write()"> -->
	</form:form>
</center>
<%@ include file="../mall/main_bottom.jsp"%>