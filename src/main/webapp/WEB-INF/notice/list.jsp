<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../mall/main_top.jsp"%>
<!-- list.jsp <br> -->

<center>
	<br>
	<hr>
	<h2>NOTICE</h2>
	<%-- <font color="#696969" style="margin: 20px"> <small>글목록(전체 글:
			${pageInfo.totalCount})</small></font> --%>
	<hr>
	
<%-- 	<div align="left" style="margin-left: 130;">
	<font color="#696969" style="margin: 20px;"> <small>글목록(전체
			글: ${pageInfo.totalCount})</small></font>
	</div> --%>
	
	
	<table class="table table-hover" style="width: 80%;">
		<tr align="center">
			<th width="5%">번호</th>
			<th>제목</th>
			<th width="10%">날짜</th>
			<th>작성자</th>
			<th width="7%">조회수</th>
		</tr>

		<c:if test="${ fn:length(lists) eq 0 }">
			<td colspan="5" align="center">작성된 게시글이 없습니다.</td>
		</c:if>
		<c:if test="${fn:length(lists) > 0 }">
			<c:forEach var="list" items="${ lists }">
				<c:choose>
					<c:when test="${fn:contains(loginInfo.id, 'admin')}">
						<tr style="text-align: center;">
							<td>${list.num }</td>
							<td style="text-align: left !important;">
								<c:if test="${list.cate eq '이벤트'}">
									<img src="resources/images/00_event.png" align="absmiddle"
										height="20px" width="30px">
								</c:if> 
								<!-- 비공개 --><c:if test="${list.cateopen eq '비공개'}">
									<img src="resources/images/00_secret.png" align="absmiddle">
								</c:if> 
									<a href="detail.no?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
								<!-- 파일 업로드 --> <c:if test="${list.image != null}">
									<img src="resources/images/00_attach_file.png"
										align="absmiddle">
								</c:if></td>
							<td><fmt:parseDate var="reg_date" value="${ list.reg_date }"
									pattern="yyyy-MM-dd" /> <fmt:formatDate var="formatDate"
									value="${ reg_date }" pattern="yyyy-MM-dd" /> ${formatDate}</td>
							<td>${list.writer}</td>
							<td>${list.readcount }</td>
						</tr>
					</c:when>
					<c:when test="${not fn:contains(loginInfo.id, 'admin')}">
						<c:if test="${list.cateopen eq '전체공개'}">
							<tr style="text-align: center;">
								<td align="left">${list.num }</td>
								<td style="text-align: left !important;"><c:if
										test="${fn:contains(list.cate, '이벤트')}">
										<img src="resources/images/00_event.png" align="absmiddle"
											height="20px" width="30px">
									</c:if> <a
									href="detail.no?num=${list.num}&pageNumber=${pageInfo.pageNumber}">${list.subject}</a>
									<!-- 파일 업로드 --> <c:if test="${list.image != null}">
										<img src="resources/images/00_attach_file.png"
											align="absmiddle">
									</c:if></td>
								<td><fmt:parseDate var="reg_date"
										value="${ list.reg_date }" pattern="yyyy-MM-dd" /> <fmt:formatDate
										var="formatDate" value="${ reg_date }" pattern="yyyy-MM-dd" />
									${formatDate}</td>
								<td>${list.writer}</td>
								<td>${list.readcount }</td>
							</tr>
						</c:if>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:if>

	</table>
	<br>
	<c:if test="${loginInfo.id eq 'admin'}">
		<input type="button" value="공지글 작성" class="btn btn-primary"
			onclick="window.location='write.no'">
	</c:if>
</center>
<br>
<center>${pageInfo.pagingHtml }</center>
<%@ include file="../mall/main_bottom.jsp"%>


