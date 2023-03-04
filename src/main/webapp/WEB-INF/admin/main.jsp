<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main.jsp<br> -->
<%@ include file="main_top.jsp" %>

<c:if test="${ loginInfo.id eq 'admin' }">
	<!-- <h2>관리자 메인 페이지</h2> -->
<br>	
	<table style="width: 70%;">
		<tr align="left" valign="top">
			<td>
				<span style="font-size: 45;"><br>
				<font style="font-weight: bold;">${ loginInfo.id }</font>님<br>
				반갑습니다.</span><br><br><br>
			</td>
		</tr>
		<tr align="left" valign="top">
			<td bgcolor="">
				<font style="font-size: 30; background-color: cyan;">상품 소식</font><br><br>
				<span style="font-size: 25;">
				승인 대기 상품 <a href="snackList.ad"><font color="red">${ fn:length(apprLists) }</font></a> 개<br>
				<br>
				총 상품 <font style="font-weight: bold;">${ totalCount }</font> 개<br>
				주류 상품 <a href="alcoholList.ad"><font color="blue">${ totalAlcohol }</font></a> 개<br>
				안주 상품 <a href="snackList.ad"><font color="blue">${ totalSnack }</font></a> 개
				</span><br><br><br>
			</td>
			<td>
				<font style="font-size: 30; background-color: yellow;">주문 소식</font><br><br>
				<span style="font-size: 25;">
				지난달 총 주문 ${ fn:length(lastLists) } 건<br>
				총 매출 <fmt:formatNumber pattern="###,###" value="${ lastTotalSaleAmount }"/> 원<br><br>
				
				<font style="font-weight: bold;">${ month }</font>
				월 총 주문 
				<a href="orderList.ad?whatColumn=month&keyword=${ month }">
				<font style="font-weight: blue;">${ fn:length(orderLists) }</font>
				</a>
				 건<br>
				총 매출 <font style="font-weight: bold;"><fmt:formatNumber pattern="###,###" value="${ totalSaleAmount }"/></font> 원
				</span>
			</td>
		</tr>
	</table>
	
</c:if>

<c:if test="${ loginInfo.id ne 'admin' }">
	<!-- <h2>판매자 메인 페이지</h2> -->
<br><br><br><br><br>
	<table style="width: 70%">
		<tr align="left">
			<td>
			<span style="font-size: 40;">술마트의 든든한 파트너<br>
			<font style="font-weight: bold;">${ loginInfo.id }</font>님<br>
			반갑습니다.</span><br><br><br>
			</td>
		</tr>
		<tr>
			<td>
			<span style="font-size: 25;">
			승인 대기 상품 <a href="sellerList.ad"><font color="red">${ fn:length(myDlists) }</font></a> 개<br>
			판매중 상품 <a href="sellerList.ad"><font color="blue">${ totalCountA }</font></a> 개
			</span><br><br><br>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="상품 등록 하기" class="btn btn-primary btn-lg"
				 onclick="javascript:location.href='sellerInsert.ad'">
			</td>
		</tr>
	</table>
	
</c:if>

<br><br><br>

<%@ include file="../mall/main_bottom.jsp" %>