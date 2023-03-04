<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>

<!-- detailForm.jsp -->
<br>
<script type="text/javascript">
	function del(num, pageNumber) {
		var chk = confirm("삭제하시겠습니까?");
		if (chk) {
			location.href = "myDelete.qna?num=" + num + "&pageNumber="
					+ pageNumber;
		}
	}
</script>
<style> /* 모든 파일 스타일은 부트스트랩 적용하면서 삭제 */
.err {
	color: red;
	font-weight: bold;
}
</style>

<center>
	<hr>
	<h2 style="margin: 10px">${qna.subject}</h2>
	<hr>
	<br>
	<table class="table table-hover" style="width: 1000px;">
		<tr>
			<td width="100">카테고리</td>
			<td>${qna.cate}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${qna.writer}</td>

		</tr>
		<tr height="200" valign="top">
			<td><br>본문</td>
			<td><br>${qna.content}</td>
		</tr>
		<tr>
			<td valign="top">첨부파일</td>
			<td><c:if test="${qna.image != null}">
					<img height=auto width=800
						onclick="javascript:popupImage(this.src);"
						src="<%=request.getContextPath()%>/resources/qna/${qna.image}">
				</c:if> <c:if test="${qna.image == null}">
					<small> 첨부된 파일이 없습니다.</small>
				</c:if><br> <br></td>
		</tr>

		<tr>
				<c:if test="${loginInfo.id eq qna.writer}">
					<td align="center" colspan="2"><br> <br> <input
						type="button" value="이전" class="btn btn-secondary"
						onClick="document.location.href='myqna.qna?pageNumber=${pageNumber}'">
						<input type="button" value="글수정" class="btn btn-primary"
						onClick="location.href='myUpdate.qna?num=${qna.num}&pageNumber=${pageNumber}'">
						<input type="submit" value="글삭제" class="btn btn-primary"
						onClick="del('${qna.num}','${pageInfo.pageNumber }')"></td>
				</c:if>
		</tr>

	</table>
</center>
<%@ include file="../mall/main_bottom.jsp"%>