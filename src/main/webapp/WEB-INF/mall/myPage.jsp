<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- myPage.jsp<br> -->
<%@ include file="main_top.jsp"%>

<!-- <style>
	#myPage{
		margin-left: 430;
	}
</style> -->

<br>
<hr>
<center>
<h2><b>마이페이지</b></h2>
<hr>


	<div class="col-2"></div>
	<div class="col-6">
<hr>
<div class="row row-cols-1 row-cols-md-2 g-4" style="width: 70%">
  <div class="col">
    <div class="card">
      <img height=180 style="padding:30px" src="<%=request.getContextPath()%>/resources/images/주문내역이미지.png" class="card-img-top" alt="주문내역">
      <div class="card-body">
        <h5 class="card-title">
        	<a href="order.mall" class="list-group-item list-group-item-action ">주문내역/주문취소</a>
        </h5>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img  height=180 style="padding:30px" src="<%=request.getContextPath()%>/resources/images/회원정보이미지.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">
        	<a href="update.mem?id=${ loginInfo.id }" class="list-group-item list-group-item-action ">회원정보변경/탈퇴</a>
        </h5>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img height=180 style="padding:30px" src="<%=request.getContextPath()%>/resources/images/찜하기.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">
        	<a href="myPageReview.mall" class="list-group-item list-group-item-action ">찜리스트</a>
        </h5>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img height=180 style="padding:30px" src="<%=request.getContextPath()%>/resources/images/문의.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">
        	<a href="myqna.qna" class="list-group-item list-group-item-action ">1:1문의</a>
        </h5>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img height=180 style="padding:30px" src="<%=request.getContextPath()%>/resources/images/리뷰아이콘.JPG" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">
        	<a href="myReview.mall" class="list-group-item list-group-item-action ">마이리뷰</a>
        </h5>
      </div>
    </div>
  </div>
</div>





</div>
	<div class="col-2"></div>
	
</center>

<br>


<%@ include file="main_bottom.jsp"%>