<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="main_top.jsp" %>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>


<br>
<hr>
<center>
	<h2><b>나의 리뷰</b></h2>
</center> 
<hr>

<%@ include file="myPage_top.jsp"%>


<table align="center" class="table table-hover" style="width: 72%">
	<tr class="table-light">
			<td colspan="5"> <b>${loginInfo.name }(${loginInfo.id })</b> 님의 리뷰 </td>
	</tr>
	<tr align="center" class="table-light">
		<c:forEach var="item" items="${rstatus}">
			<td class="pos" onclick="clickPosToCom(this);"
				style="background-color: #d3d3d3;">작성가능 후기(${item.possible})</td>
			<td class="com" onclick="clickPosToCom(this);">작성완료
				후기(${item.complete})</td>
		</c:forEach>
	</tr>

	<!-- 해당 상품으로 이동 -->
	<!-- 마이페이지 작성 가능한 후기-> 후기쓰러가기 -->
	<c:forEach var="pb" items="${pbreview}">
		<tr id="pbview" align="center" class="table-light">
			<td colspan="2"><img src="<%= request.getContextPath() %>/resources/${pb.image}"
				width="150px">
				<p>${pb.name}</p>
				<p>${pb.qty}개구매</p> <a href="/ex/detail.al?num=${pb.num}">후기쓰러가기</a></td>
		</tr>
	</c:forEach>

	<!-- 마이페이지 작성 완료한 후기 -> 상품보러가기 -->
	<c:forEach var="cm" items="${cmreview}">
		<tr id="cmview" style="display: none;" class="table-light">
			<td colspan="2" align="center">
				<img src="<%= request.getContextPath() %>/resources/${cm.image}"
				width="150px">
				<p>${cm.name}</p>
				<p>${cm.qty}개구매</p> <a href="/ex/detail.al?num=${cm.num}">상품
					보러가기</a></td>
		</tr>
	</c:forEach>

</table>

<script>
	/* 클릭시 background-color */
	function clickPosToCom(event) {
		if ($(event).attr('class') == 'pos') {
			$('.pos').css("background-color", "#d3d3d3");
			$('.com').css("background-color", "#ffffff");
			$("tr[id^='pbview']").show();
			$("tr[id^='cmview']").hide();
		} else {
			$('.pos').css("background-color", "#ffffff");
			$('.com').css("background-color", "#d3d3d3");
			$("tr[id^='pbview']").hide();
			$("tr[id^='cmview']").show();
		}
	}
</script>
