<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../common/common.jsp" %>
	<%@ include file="../admin/main_top.jsp"%>

<style>
	#tr{
		font-weight: bold;
	}
</style>
	
<script type="text/javascript">
	function insert(){
		location.href="registerForm.mem";
	} 
/* 	
	function update(num,pageNumber){
		location.href="update.mem?num="+num+"&pageNumber="+pageNumber;
	}
	 */
</script>

<br>
<hr>
    
<center>
<h2><b>회원 관리</b></h2>

<hr>



<form action="list.mem" method="get">
		   	<select name="whatColumn">
		   		<option value="">전체검색</option>
		   		<option value="name">닉네임</option>
		   		<option value="id">회원ID</option>
		   	</select>
		   	<input type="text" name="keyword">
		   	<input type="submit" value="검색" class="btn btn-primary btn-sm"><br>
</form>


<span><a href="list.mem">전체보기</a> | 
<a href="list.mem?whatColumn=seller&keyword=0">일반 회원</a> | 
<a href="memberSellerList.mem">판매자 회원</a></span><br><br>


<table class="table table-hover" style="width: 95%">
	   

   		<tr class="table-info">
   			<td><strong><!-- 회원 -->번호</strong></td>
   			<td><strong>구분</strong></td>
   			<td><strong>아이디</strong></td>
   			<td><strong>닉네임</strong></td>
   			<td><strong>이메일</strong></td>
   			<td><strong>생년월일</strong></td>
   			<td><strong>휴대폰 번호</strong></td>
   			<td><strong>주소</strong></td>
   			<td><strong>P</strong></td>
   			<td><strong>수정</strong></td>
   			<td><strong>삭제</strong></td>
   		</tr>
		<c:forEach var="m" items="${lists }">
	   		<tr>
	   			<td>${m.num }</td>
	   			<td>
	   				<c:set var="seller" value="0" /><c:if test="${m.seller eq '0'}"> <c:out value="회원" /></c:if>
					<c:set var="seller" value="1" /><c:if test="${m.seller eq '1'}"> <c:out value="판매자" /></c:if>
	   			</td>
	   			<td style="color:007bff;">${m.id}</td>
	   			<td>${m.name }</td>
	   			<td>${m.email1 }${m.email2 }</td>
	   			<td>${m.rrn1 }${m.rrn2 }${m.rrn3 }</td>
	   			<td>${m.hp1}-${m.hp2}-${m.hp3}</td>
	   			<td>${m.zipcode1 } ${m.zipcode2 } ${m.zipcode3 } ${m.zipcode4 }</td>
	   			<td>${m.mpoint}</td>
	   			<%-- <td><input type="button" value="수정" onclick="update('${m.num}','${pageInfo.pageNumber }')"></td>
	   			 --%>
	   			<td><a href="update.mem?id=${m.id }&pageNumber=${pageInfo.pageNumber}">수정</a></td>
	   			<td><a href="delete.mem?num=${m.num }&pageNumber=${pageInfo.pageNumber}">삭제</a></td>
	   		</tr>
   		</c:forEach>
   		
   		<tr>
   			<td colspan="13">
   			<input type="button" value="추가하기" onClick="insert()" class="btn btn-primary btn-sm">
   			</td>
   		</tr>
   	</table>
   	</center>
   	<center>
   	${pageInfo.pagingHtml }
   	</center>
<%@ include file="../mall/main_bottom.jsp" %>
