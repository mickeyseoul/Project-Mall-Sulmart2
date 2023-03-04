<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!-- main.jsp<br> -->
<%@ include file="main_top.jsp"%>

<script>
$(window).ready(function() {
   $('.carousel').carousel({
     interval: 8000
   });
});
</script>

<br>
<center>

<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false" style="width: 60%;">
  <div class="carousel-indicators">
    <!-- <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <a href="detail.al?num=4">
      <img src="<%= request.getContextPath() %>/resources/images/대문이미지1.jpg" class="d-block w-100">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>단감명작 7도 750ml</h5>
        <p>단감으로 만든 한 잔의 명작을 만나보세요</p>
      </div>
    </div>
    <div class="carousel-item">
    	<a href="detail.al?num=5">
      <img src="<%= request.getContextPath() %>/resources/images/대문이미지2.jpg" class="d-block w-100">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>보리수헤는밤 8도 375ml</h5>
        <p>매실과 보리수의 특별한 만남을 즐겨보세요</p>
      </div>
    </div>
    <div class="carousel-item">
    	<a href="detail.al?num=6">
      <img src="<%= request.getContextPath() %>/resources/images/대문이미지3.jpg" class="d-block w-100">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>문경 바람 오크 25도 375ml</h5>
        <p>사과로 만든 소주를 오크통 숙성까지?</p>
      </div>
    </div>
  </div>
  <!-- <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button> -->
</div>

</center>


<br>


<table width="90%">
	<!-- 
	<tr>
		<td>뉴스레터 구독</td>
	</tr>
	 -->
	<tr>
		<td><font size="4">주류 신상품</font></td>
	</tr>
	
	<tr>
		<td>
			<table class="table table-hover">
				<tr>
					<c:forEach var="alcohol" items="${ lists }">
						<td>
							<a href="detail.al?num=${ alcohol.num }"> <img
								src="<%=request.getContextPath()%>/resources/images/alcohol/${ alcohol.image }"
								width="300" height="300"><br> ${ alcohol.name }
							</a><br> 
							<fmt:formatNumber pattern="#,###"
								value="${ alcohol.price }" />원
						</td>
					</c:forEach>
				</tr>
			</table>
		</td>
	</tr>

	<tr>
		<td height="60"></td>
	</tr>

	<tr>
		<td><font size="4">안주 신상품</font></td>
	</tr>
	<tr>
		<td>
			<table class="table table-hover">
				<tr>
					<c:forEach var="snack" items="${ lists2 }">
						<td><a href="detail.al?num=${ snack.num }"> <img
								src="<%=request.getContextPath()%>/resources/images/alcohol/${ snack.image }"
								width="300" height="300"><br> ${ snack.name }
						</a><br> <fmt:formatNumber pattern="#,###"
								value="${ snack.price }" />원</td>
					</c:forEach>
				</tr>
			</table>

		</td>
	</tr>

	<!-- <tr>
		<td><font size="4">후기</font></td>
	</tr> -->

</table>



<%@ include file="main_bottom.jsp"%>
<%@ include file="userBottom.jsp"%>

