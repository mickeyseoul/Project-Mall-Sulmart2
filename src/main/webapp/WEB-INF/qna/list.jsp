<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>

<!-- list.jsp <br> -->
<style>
#f1 {
	display: inline-block;
	text-align: center;
}
</style>

<center>
	<br>
	<hr>
	<h2>QNA</h2>
	<%-- <font color="#696969" style="margin: 20px;"> <small>글목록(전체
			글: ${pageInfo.totalCount})</small></font> --%>
	<hr>
	
	<div align="left" style="margin-left: 130;">
	<font color="#696969" style="margin: 20px;"> <small>글목록(전체
			글: ${pageInfo.totalCount})</small></font>
	</div>
			
	<table class="table table-hover" style="width: 80%;">

		<tr align="center" >
			<th width="10%">문의날짜</th>
			<th width="15%">카테고리</th>
			<th>제목</th>
			<th>작성자</th>
			<th width="5%">조회수</th>
			<th width="10%">문의상태</th>
		</tr>

		<c:if test="${ fn:length(lists) eq 0 }">
			<td colspan="6">작성된 게시글이 없습니다.</td>
		</c:if>
		<c:if test="${ fn:length(lists) > 0 }">
			<c:forEach var="list" items="${ lists }">
				<tr style="text-align: center;">
					<td><fmt:parseDate var="reg_date" value="${ list.reg_date }"
							pattern="yyyy-MM-dd" /> <fmt:formatDate var="formatDate"
							value="${ reg_date }" pattern="yyyy-MM-dd" /> ${formatDate}</td>
					<td>[${ list.cate }]</td>
					<td id="sub" style="text-align: left !important;">
						<!-- 관리자 아이디로 로그인 또는 작성자 본인의 비공개 --> <c:choose>
							<c:when test="${fn:contains(list.cateopen, '비공개')}">
								<img src="resources/images/00_secret.png" align="absmiddle">
								<c:choose>
									<c:when test="${fn:contains(loginInfo.id, 'admin')||fn:contains(loginInfo.id, 'seller')}">
										<a
											href="detail.qna?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
									</c:when>
									<c:when test="${loginInfo.id eq list.writer}">
										<a href="detail.qna?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
									</c:when>
									<c:otherwise>
										<a onclick="alert('관리자 또는 작성자 본인만 조회 가능합니다.')">
											${list.subject}</a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a
									href="detail.qna?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
							</c:otherwise>
						</c:choose> <!-- 파일 업로드 된 글 --> <c:if test="${list.image != null}">
							<img src="resources/images/00_attach_file.png" align="absmiddle">
						</c:if>
					</td>

					<td>${list.writer}</td>
					<td>${ list.readcount }</td>
					<td>${ list.reply }</td>
				</tr>
			</c:forEach>
		</c:if>

	</table>
	<br>
	<div id="f1">
		<form action="list.qna" method="get" class="d-flex">
			<select name="whatColumn">
				<option value="">전체글</option>
				<option value="subject">글제목</option>
				<option value="writer">작성자</option>
			</select> <input type="text" name="keyword"> <input type="submit"
				value="검색" class="btn btn-outline-success"> <input
				type="button" value="문의하기" class="btn btn-primary btn-sm"
				onclick="window.location='write.qna'">
		</form>
	</div>
</center>

<center>${pageInfo.pagingHtml }</center>

<%@ include file="../mall/main_bottom.jsp"%>