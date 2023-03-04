<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>
<br>
<hr>
<center>
	<h2><b>1:1 문의</b></h2>
</center> 
<hr>

<%@ include file="../mall/myPage_top.jsp"%>

	<table align="center" class="table table-hover" style="width: 72%">
		<tr class="table-light">
			<td colspan="5"><b>${loginInfo.name }(${loginInfo.id })</b> 님의
				문의내역</td>
		</tr>
		<%-- 		<tr class="table-light">
			<td colspan="5"><b> 문의 내역이 <font color="blue">${pageInfo.totalCount}</font>건
					있습니다.
			</b></td>
		</tr> --%>
		<tr align="center" class="table-info">
			<th width="15%">문의날짜</th>
			<th width="15%">카테고리</th>
			<th>제목</th>
			<th width="7%">조회수</th>
			<th width="15%">문의상태</th>
		</tr>

		<c:if test="${ fn:length(lists) eq 0 }">
			<td colspan="5">1:1 문의 내역이 없습니다.</td>
		</c:if>
		
		<c:if test="${ fn:length(lists) > 0 }">
			<c:forEach var="list" items="${ lists }">
				<c:if test="${loginInfo.id eq list.writer}">
					<tr align="center" class="table-light">
						<!-- &nbsp -->
						<td><fmt:parseDate var="reg_date" value="${ list.reg_date }"
								pattern="yyyy-MM-dd" /> <fmt:formatDate var="formatDate"
								value="${ reg_date }" pattern="yyyy-MM-dd" /> ${formatDate}</td>
						<td>[${ list.cate }]</td>
						<td id="sub" style="text-align: left !important;">
							<!-- 관리자 아이디로 로그인 또는 작성자 본인의 비공개 --> <c:if
								test="${fn:contains(list.cateopen, '비공개')}">
								<img src="resources/images/00_secret.png" align="absmiddle">
							</c:if> <a
							href="myDetail.qna?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
							<!-- 파일 업로드 된 글 --> <c:if test="${list.image != null}">
								<img src="resources/images/00_attach_file.png" align="absmiddle">
							</c:if>
						</td>
						<td>${list.readcount}</td>
						<td>${list.reply}</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>
	<table style="width: 72%">
	
	<tr align="center">
	<td>
	
		<input type="button" value="문의하기" class="btn btn-primary btn-sm"
			onclick="window.location='myWrite.qna'">
	</td>
	</tr>
	<tr align="center">
		<td class="btn-group" role="group"
			aria-label="Basic mixed styles example">
			<button type="button" class="btn btn-warning">${pageInfo.pagingHtml }</button>
		</td>
	</tr>
	</table>


<br><br>


<%@ include file="../mall/main_bottom.jsp"%>



