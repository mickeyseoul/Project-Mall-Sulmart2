<%@page import="member.model.MemberBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../mall/main_top.jsp"%>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<!-- 바로 결제 -->
<script type="text/javascript">
function goOrder(num){ 
	//alert(1);
	orderqty = f.orderqty.value;
	
	if(f.orderqty.value =="" || f.orderqty.value <= 0){ //숫자가 없거나 음수를 넣으면 
		alert('상품갯수는 1개이상 입력해야합니다.');
		return false;
	}
	location.href='orderList.mall?num='+num+'&orderqty='+orderqty;
}

function stock(){
	//alert(f.orderqty.value);
	//alert(f.chstock.value);
	if(f.orderqty.value =="" || f.orderqty.value <= 0){
		alert('상품갯수는 1개이상 입력해야합니다.');
		return false;
	}
	
	if(f.orderqty.value>f.chstock.value){
		alert("주문 수량이 재고보다 많습니다. (재고:"+f.chstock.value+")");
		return false;
	}
}

function HeartAjax(num) {
	//alert(num);
	
	$.ajax({
		url : '/ex/heart.al',
		data : {
			num : num
		},
		success : function(data) {
			if (data < 1) {
				alert("통신 실패");
			} else {
				alert("해당 상품이 찜 되었습니다.");
			}
		}
	});
	
}

</script>

<br>
<hr>
<center>
	<h2><b>상세보기</b></h2>
</center> 
<hr>

<center>

	<br>
	<form name="f" action="add.mall" method="post">
		<input type="hidden" name="num" value="${ ab.num }"> 
		<input type="hidden" name="pageNumber" value="2">
		<input type="hidden" name="chstock" value="${ stock }">

		<table border="1" style="width: 80%; height: 400px;" class="table table-sm">
			<tr>
				<td rowspan="7" align="center" valign="middle"><img
					src="<%=request.getContextPath()%>/resources/images/alcohol/${ab.image}"
					 width="400px"></td>
				<td>상품명</td>
				<td>&nbsp;${ab.name}</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
				<fmt:formatNumber pattern="#,###" value="${ ab.price }"/>
				 원</td>
			</tr>
			<tr>
				<td>상품 코드</td>
				<td>&nbsp;${ab.code }</td>
			</tr>
			<tr>
				<td>제조사</td>
				<td>&nbsp;${ab.brand }</td>
			</tr>
			<tr>
				<td>제조국가</td>
				<td>&nbsp;${ab.country }</td>
			</tr>

			<td>수량</td>
			<td><input type="text" name="orderqty" value="1" min="1" size="2">
				<input type="submit" value="장바구니" class="btn btn-primary btn-sm" onclick="return stock()">
				<input type="button" value="바로결제" class="btn btn-primary btn-sm"
				onclick="goOrder(${ab.num})"></td>
			</tr>
	
			<!-- 찜기능 -->
			<tr>
				<c:choose>
					<c:when test="${sessionScope.loginInfo eq null}">
						<tr>
							<td align="center"><input type="button" id="heart" data-id="${ab.num}"
										onclick="HeartAjax(${ ab.num });" value="찜하기"
										class="btn btn-primary btn-sm"></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${ab.heart == 1 || ab.heart eq null}">
								<tr>
									<td align="center">
										<input type="button" id="heart" data-id="${ab.num}"
										onclick="HeartAjax(${ ab.num });" value="찜하기"
										class="btn btn-primary btn-sm">
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><input type="button" id="heartDel" data-id="${ab.num}"
										onclick="HeartAjax(this);" value="찜하기 취소"></input></td>
								</tr>
							</c:otherwise>
						</c:choose>

					</c:otherwise>
				</c:choose>
		</table>

		<div style="width: 50%; display: flex; justify-content: flex-start;">
			<img src="<%=request.getContextPath()%>/resources/images/alcohol/${ab.image}" width="100px"
				style="border: 1px solid #d3d3d3;">
		</div>


	</form>
	
	
	<br><br>
	<!-- 본문 이미지 -->
	<table width="80%" style="background-color: #EAEAEA;">
		<tr height="50"></tr>
		<tr><td align="center">
			<img src="<%=request.getContextPath()%>/resources/images/alcohol/${ab.contentImage}" width="50%" />
		</td></tr>
		<tr height="50"></tr>
	</table>
	
	
	<br>
	
	
	<table border="1" width="500" height="400">
		<tr>
			<td>평가<br> <c:choose>
					<c:when test="${prd.starAvg  eq null || empty prd.starAvg}">0</c:when>
					<c:otherwise>${prd.starAvg }</c:otherwise>
				</c:choose><br>


			</td>

			<td>리뷰등록 : ${prd.totalReviewCount}<br> 포토리뷰 :
				${prd.imgReviewCount}<br> 상품조회수 : ${ab.readcount} <br>
			</td>

			<td><c:forEach var="item" items="${std}">
					
						${item.star } : ${item.count } <br>




				</c:forEach></td>

		</tr>
	</table>


	<table border="1" width="500" height="400" class="table table-hover">
		<tr>
			<td align=center width="800">
				<form method="post" action="/ex/reWrite.al"
					enctype="multipart/form-data">
					<input type="hidden" name="num" value="${ab.num}"> <input
						type="hidden" name="ref" value="${ab.num}"> <select
						name="star">
						<option value="5" selected>매우만족</option>
						<option value="4">만족</option>
						<option value="3">보통</option>
						<option value="2">불만족</option>
						<option value="1">매우불만족</option>
					</select> <br> <br>
					<textarea name="content" style="resize: none;" cols="100" rows="9"></textarea>
					<br>
					<input type="file" name="reviewFile"> <br> <br> <input
						type="submit" value="리뷰등록" class="btn btn-primary btn-sm"
						text-align="right">
				</form>
			</td>
		</tr>
	</table>

	<br> <br>

	<table border="1" width="500" height="200" class="table table-hover"
		align=center>

		<c:forEach var="item" items="${rb}">
			
			<tr>
				<td width="150">별점 : ${item.star} <br> 날짜 :
					${item.reg_date} <br>

				</td>

				<td width="350">내용 : ${item.content}<br> <c:if
						test="${item.image ne null || not empty item.image}">
						<img src="<%=request.getContextPath() %>resources/images/alcohol/${item.image}">
					</c:if> <br> 추천 :
					<p id="recomm">${item.recomm}</p>

					<button id="recommbtn" data-id="${item.num}"
						onclick="recommAjax(this);" class="btn btn-primary btn-sm">추천</button>

					<c:if test="${item.writer eq sessionScope.loginInfo.id}">
						<button id="deletebtn" data-id="${item.num}"
							onclick="deleteReview(this);" class="btn btn-danger btn-sm">삭제</button>
						<button id="updateBtn" data-id="${item.num}"
							onclick="updateReview(this);" class="btn btn-success btn-sm">수정</button>
					</c:if> 댓글 : <input type="text" name="comment">
					<button type="button" onclick="writeComment(this);" data-rid="${item.num}">작성</button> <br>

					
						<c:forEach var="rc" items="${rc}">
							<div>
								작성자 : ${rc.id } <br>
								내용 :${rc.content} <br>
							</div>
						</c:forEach>
					
				</td>
			</tr>
		</c:forEach>
	</table>
</center>

<script>
	/* 리뷰 추천 */
	function recommAjax(event) {
		$.ajax({
			url : '/ex/recomm.al',
			data : {
				id : $(event).data('id')   // 상품 번호
			},
			type : 'GET',
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(resp) {
				
				if (resp.message < 1) {
					alert("통신 실패");
					return;
				} else {
					if(resp.message != '') {
						alert(resp.message);
						location.reload();
					}
				}
			}
		});
	}
	
	function writeComment(event) {
		$.ajax({
			url : '/ex/writeComment.al',
			data : {
				review_id : $(event).data('rid'),
				content: $("input[name='comment']").val()
			},
			type : 'POST',
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(resp) {
				
				if (resp.message < 1) {
					alert("통신 실패");
					return;
				} else {
					if(resp.message != '') {
						alert(resp.message);
						location.reload();
					}
				}
			}
		});
	}

	/* 찜
	function HeartAjax(event) {
		
		$.ajax({
			url : '/ex/heart.al',
			data : {
				id : $(event).data('id')
			},
			type : 'GET',
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(resp) {
				if (resp.row < 1) {
					alert("통신 실패");
					return;
				} else {
					if(typeof(resp.row) != "undefined" || resp.row != null || resp.row != "") {
						alert(resp.message);
						return;
					}
					
				}
			}
		});
		
	}
	*/
	
	function deleteReview(event) {
		console.log($(event).data("id"));
		
		$.ajax({
			url : '/ex/deleteReview.al',
			data : {
				id : $(event).data('id')
			},
			type : 'POST',
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(resp) {
				console.log("row : [" + resp.row + "]");
				if (resp.row == '-1') {
					alert("통신 실패");
					return;
				} else {
					if(typeof(resp.row) != "undefined" || resp.row != null || resp.row != "") {
						alert(resp.message);
						location.reload();
					}
					
				}
			}
		});
		
	}	

	$(document).ready(function() {
		$("input[name=orderqty]").bind('keyup mouseup',function() {
			let price = $("#price").data('price');
			let qty = $("input[name=orderqty]").val();

			$("#price").text((parseInt(price) * parseInt(qty)).toString())

		});
	})
	
</script>
