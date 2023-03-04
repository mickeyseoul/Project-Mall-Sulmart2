<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- insertForm.jsp<br> -->
<%@ include file="main_top.jsp"%>

<style>
	#ta{
		float: left;
		margin-left: 170;
	}
	
</style>

<br>
<hr>
    
<center>
<h2><b>카테고리 관리</b></h2>

<hr>


	<form action="insertAlCate.ad">
	<table id="ta" class="table table-hover" style="width: 30%;">
		<tr align="center" class="table-info">
			<td colspan="3" style="font-weight: bold;">주류 카테고리 등록
			</td>
		</tr>
		<tr align="center">
			<td colspan="3"><input type="text" name="cate">&nbsp;
			<input type="submit" value="등록" class="btn btn-primary btn-sm"></td>
		</tr>
		<tr height="30"></tr>
		<tr align="center">
			<th>번호</th>
			<th>카테고리</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="allist" items="${ allists }" varStatus="i">
			<tr align="center">
				<td>${ i.count }</td>
				<td>${ allist.cate }</td>
				<td><a href="deleteAlCate.ad?num=${ allist.num }">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	</form>



	<form action="insertSnCate.ad" method="post">
		<table class="table table-hover" style="width: 30%;">
			<tr align="center" class="table-info">
				<td colspan="4"style="font-weight: bold;">안주 카테고리 등록
				</td>
			</tr>
			<tr align="center">
				<td colspan="4">
				<select name="cate1">
					<option value="건식">건식
					<option value="습식">습식
				</select>
				<input type="text" name="cate2" value="">&nbsp;
				<input type="submit" value="등록" class="btn btn-primary btn-sm">
				</td>
			</tr>
			<tr height="30"></tr>
			<tr align="center">
				<th>번호</th>
				<th>카테고리1</th>
				<th>카테고리2</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="snlist" items="${ snlists }" varStatus="i">
			<tr align="center">
				<td>${ i.count }</td>
				<td>${ snlist.cate1 }</td>
				<td>${ snlist.cate2 }</td>
				<td><a href="deleteSnCate.ad?num=${ snlist.num }">삭제</a></td>
			</tr>
			</c:forEach>
	</table>

</center>

<%@ include file="../mall/main_bottom.jsp" %>