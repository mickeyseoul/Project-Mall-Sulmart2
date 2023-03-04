<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="main_top.jsp" %>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<style>
	#ta2{
		margin-left: ;
	}
</style>

<br>
<hr>
<center>
	<h2><b>찜리스트</b></h2>
</center> 
<hr>

<%@ include file="myPage_top.jsp"%>
	

<table id="ta2" border="1" class="table table-hover" style="width: 72%">
		<tr class="table-light">
			<td colspan="5"> <b>${loginInfo.name }(${loginInfo.id })</b> 님의 찜 리스트 </td>
		</tr>
		<tr align="center" class="table-info">
			<td><input type="checkbox" id="allCheck"></td>
			<td>상품명/옵션</td>
			<td>상품금액/수량</td>
			<td>혜택</td>
			<td>합계</td>
		</tr>
		<c:if test="${ fn:length(heartList) eq 0 }">
			<tr class="table-light">
				<td colspan="5" align="center">찜한 상품이 없습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="item" items="${heartList}">
			<tr class="table-light" align="center">

					<td><input type="checkbox" name="heartCheck"
						value="${item.prod_num}"></td>
					<td><img src="<%= request.getContextPath() %>/resources/${item.contentimage}"
						width="150px"><br> ${item.name}</td>

					<td><fmt:formatNumber value="${item.price}"
							groupingUsed="true" /> / <span id="qty">1</span>개</td>


					<td>적립 <br>상품 + <fmt:formatNumber type="number"
							maxFractionDigits="0" value="${item.price * 0.1}" /></td>

					<td><input type="submit" value="장바구니"
						class="btn btn-primary btn-sm" /> <br><br>
						<button class="btn btn-secondary btn-sm" onclick="singleProductDeleteClick(this);" data-id="${item.prod_num}">삭제하기
						</button>
					</td>
			</tr>
		</c:forEach>
	</table>

<div style="margin-left: 750;">
	<button onclick="prodcutDeleteClick();" type="button" class="btn btn-secondary btn-sm">전체 상품 삭제</button>
	<button type="button" class="btn btn-secondary btn-sm">선택 상품 장바구니</button>
</div>	



<script>
	$(document).ready(function() {
		$("#allCheck").click(function() {
			if ($("#allCheck").is(":checked"))
				$("input[name=heartCheck]").prop("checked", true);
			else
				$("input[name=heartCheck]").prop("checked", false);
		});

		$("input[name=heartCheck]").click(function() {
			var total = $("input[name=heartCheck]").length;
			var checked = $("input[name=heartCheck]:checked").length;

			if (total != checked)
				$("#allCheck").prop("checked", false);
			else
				$("#allCheck").prop("checked", true);
		});
	});

	function prodcutDeleteClick() {
		if ($("input:checkbox[name=heartCheck]").is(":checked") == false) {
			alert("삭제할 항목을 선택해주세요.");
			return;
		}

		let checkBoxArr = [];
		$("input:checkbox[name='heartCheck']:checked").each(function() {
			checkBoxArr.push($(this).val());

		});
	
		$.ajax({
			type : 'POST',
			url : "/ex/prodDelete.mall",
			data : {
				checkBoxArr : checkBoxArr
			},
			success : function(result) {
				if (result.msg !== '') {
					alert(result.msg);
				}

				if (result.status == '1') {
					location.href = '/ex/myPageReview.mall';
				}

			}
		})
	}

	function singleProductDeleteClick(event) {
		let targetSingleProduct = [$(event).data("id")];

		
		$.ajax({
			type : 'POST',
			url : "/ex/prodDelete.mall",
			data : {
				checkBoxArr :targetSingleProduct
			},
			success : function(result) {
				if (result.msg !== '') {
					alert(result.msg);
				}

				if (result.status == '1') {
					location.href = '/ex/myPageReview.mall';
				}

			}
		})
	}
</script>

