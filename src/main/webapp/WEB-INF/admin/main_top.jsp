<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- admin-main_top<br> -->
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

<style>
table {
	margin: 0px auto;
}
</style>

<br>

<nav class="navbar fixed-top bg-light">
	<div class="container-fluid">
		<!-- <a class="navbar-brand" href="#">Fixed top</a> -->
		<table width="90%">
			<tr>
				<td align="left"><a class="container-fluid" href="main.ad">
					<img src="<%=request.getContextPath() %>/resources/images/logo2.png"
					width="150" height="80">
					</a>
				</td>

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
					<a href="start.jsp">사용자 페이지</a> &nbsp;|&nbsp; 
					${ loginInfo.id }님 &nbsp;|&nbsp; <a href="logout.jsp">로그아웃</a>
				</td>
			</tr>
		</table>
	</div>
</nav>

<c:if test="${ loginInfo.id eq 'admin' }">
<table width="90%">
	<tr height="80"></tr>
	<tr>
		<td align="center" colspan="2" valign="bottom" height="30">
			<a href="cateList.ad">카테고리 관리</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="alcoholList.ad">주류 상품 관리</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="snackList.ad">안주 상품 관리</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="list.mem">회원 관리</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <!-- 판매자 리스트 추가 -->
			<a href="orderList.ad?whatColumn=1&keyword=1">주문 관리</a>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
</c:if>

<c:if test="${ loginInfo.seller eq 1 }">
<table width="90%">
	<tr height="80"></tr>
	<tr>
		<td align="center" colspan="2" valign="bottom" height="30">
			<a href="sellerInsert.ad">판매 상품 등록</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<!-- <a href="sellerApprove.ad">판매 상품 등록 승인</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; -->
			<a href="sellerList.ad">나의 판매 상품</a>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
</c:if>