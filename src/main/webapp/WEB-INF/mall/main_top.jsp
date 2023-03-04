<%@page import="member.model.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap</title>

<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</head>

<!-- main_top.jsp -->
<style>
table {
	margin: 0px auto;
}
</style>

<c:if test="${ loginInfo.id eq null }">
	<nav class="navbar fixed-top bg-light">
		<div class="container-fluid">
			<!-- <a class="navbar-brand" href="#">Fixed top</a> -->
			<table width="90%">
				<tr>
					<td align="left"><a class="container-fluid" href="start.jsp">
							<img
							src="<%=request.getContextPath() %>/resources/images/logo2.png"
							width="150" height="80">
					</a></td>
					<!-- 검색창 -->
					<td align="center" width="37%" valign="bottom">
						<form class="d-flex" role="search" action="mallSearchView.mall">
							<!-- <select name="whatColumn">
								<option value="">전체
								<option value="category">카테고리
								<option value="name">상품명
								<option value="brand">브랜드
							</select>  -->
							<input class="form-control me-2" type="search"
								placeholder="Search" aria-label="Search" name="keyword">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form>
					</td>
					<td align="right" width="30%"><a href="registerForm.mem">회원가입</a>
						|&nbsp; <a href="login.mem">로그인</a>
					</td>
				</tr>
			</table>
		</div>
	</nav><br>
</c:if>


<c:if test="${ loginInfo.id eq 'admin' }">
	<a href="main.ad">관리자 페이지</a>
</c:if>

<c:if test="${ loginInfo.id ne null }">
	<nav class="navbar fixed-top bg-light">
		<div class="container-fluid">
			<!-- <a class="navbar-brand" href="#">Fixed top</a> -->
			<table width="90%">
				<tr>
					<td align="left"><a class="container-fluid" href="start.jsp">
							<img
							src="<%=request.getContextPath() %>/resources/images/logo2.png"
							width="150" height="80">
					</a></td>

					<td align="center" width="37%" valign="bottom">
						<form class="d-flex" role="search" action="mallSearchView.mall">
							<!-- <select name="whatColumn">
								<option value="">전체
								<option value="category">카테고리			
								<option value="name">상품명		
								<option value="brand">브랜드	
							</select> -->
							<input class="form-control me-2" type="search"
								placeholder="Search" aria-label="Search" name="keyword">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form>
					</td>
								
					<td align="right" width="30%">
						<c:if test="${ loginInfo.id eq 'admin' }">
							<a href="main.ad">관리자 페이지</a>&nbsp;|&nbsp;
						</c:if>
						${ loginInfo.id }님 &nbsp;|&nbsp; <a href="logout.jsp">로그아웃</a>
					</td>
				</tr>
			</table>
		</div>
	</nav><br>
</c:if>

<table width="90%">
	<tr height="80"></tr>
	<tr>
		<td align="center" colspan="2" valign="bottom" height="30"><a
		href="mallAlcoholView.mall">주류 상품</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="mallSnackView.mall">안주 상품</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <a href="">베스트</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;  --> <a
		href="myPage.mall">마이페이지</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; <a
		href="add.mall">장바구니</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; <a
		href="list.no">게시판</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; 
		<!-- <a href="">상품후기</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; --> 
		<a
		href="list.qna">QNA</a></td>
	</tr>
	<!-- <tr>
		<td align="center" colspan="2">
		</td>
	</tr> -->
</table>