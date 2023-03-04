<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
table {
	text-align: center;
	align: center;
	margin: auto;
}
</style>

<br>
<br>
<center>
<h1><span>회원 상세보기</span></h1>
<table border="1" width="500" height="400">

	<tr>
		<td align=center>ID</td>
		<td>&nbsp;${member.id}</td>
	</tr>
	<tr>
		<td align=center>이름</td>
		<td>&nbsp;${member.name}</td>
	</tr>
	<tr>
		<td align=center>이메일</td>
		<td>&nbsp;${member.email}</td>
	</tr>
	
	
	<tr>
		<td align=center>성인인증</td>
		<td>&nbsp;${member.rrn1}-*******</td>
	</tr>
	<tr>
		<td align=center>핸드폰</td>
		<td>&nbsp;${member.hp1}-${member.hp2}-${member.hp3}</td>
	</tr>
	<tr>
		<td align=center>배송지</td>
		<td>&nbsp;${member.zipcode1}<br>
		${member.zipcode2} ${member.zipcode3}</td>
	</tr>

	<tr>
		<td colspan="2" align="center"> 
			<a href="list.mem?pageNumber=${pageNumber}">회원목록</a>
		</td>
	</tr>
</table>
</center>
	