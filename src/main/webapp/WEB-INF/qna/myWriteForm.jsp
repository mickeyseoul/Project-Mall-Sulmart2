<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>
<!-- writeForm.jsp -->
<br>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.realperson.css">
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.realperson.js"></script>
<script type="text/javascript">
	function back(pageNumber) {
		if (confirm("목록으로 돌아가시겠습니까?") == true) {
			location.href = "myqna.qna?pageNumber=" + pageNumber;
		} else {
			return;
		}
	}
	$(document).ready(function() {
		/*
		 * 사용자로 부터 입력값을 받을 input 태그에 realperson() 메소드를 설정한다.
		 * 그러면 6자리(Default)의 랜덤하게 생성된 문자와 함깨 'Click to change' 문구가
		 * input 태그 앞에 생성된다.  
		 */
		$('#defaultReal').realperson();
	});
</script>
<style type="text/css">
.err {
	color: red;
	font-weight: bold;
}
</style>

<center>
	<hr>
	<h2>QNA</h2>
	<hr>
	<form:form commandName="qna" action="myWrite.qna" method="post"
		enctype="multipart/form-data">
		<table class="table table-hover" style="width: 1000px;">
			<tr>
				<td>카테고리</td>
				<td><select name="cate">
						<option value="회원/정보관리">회원/정보관리</option>
						<option value="배송">배송</option>
						<option value="반품/환불/교환/AS">반품/환불/교환/AS</option>
						<option value="영수증/증빙서류">영수증/증빙서류</option>
						<option value="상품/이벤트">상품/이벤트</option>
						<option value="기타">기타</option>
				</select> <form:errors cssClass="err" path="cate" /></td>
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
					value="${qna.subject }"> <form:errors cssClass="err"
						path="subject" /></td>
			</tr>
			<tr>
				<td>* 본문</td>
				<td><textarea name="content" rows="10" cols="50"
						placeholder="내용을 작성해주세요.">${qna.content }</textarea> <form:errors
						cssClass="err" path="content" /></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="upload" value=""></td>
		</table>
		<br>
		<br>
		<input type="button" value="이전"
			onclick="back('${pageInfo.pageNumber }')" class="btn btn-secondary">
		<input type="submit" value="등록" class="btn btn-primary ">
		<!-- 	<input type="button" value="등록" onclick="write()"> -->
	</form:form>
</center>
<%@ include file="../mall/main_bottom.jsp"%>