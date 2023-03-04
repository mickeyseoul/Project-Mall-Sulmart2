<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- myPage_top.jsp<br> -->

<style>
	#ta1{
		float: left;
		margin-left: 60;
		margin-right: -30;
	}
	/* #mem{
		margin-left: 430;
	} */
</style>

<table id="ta1" style="width: 15%">
	<tr><td>
		<h5><b>마이페이지</b></h5>
	</td></tr>
	<tr><td>
		<a href="order.mall" 
			class="list-group-item list-group-item-action ">주문내역/주문취소</a>
	</td></tr>
	<tr><td>
		<a href="update.mem?id=${ loginInfo.id }"
			class="list-group-item list-group-item-action ">회원정보변경/탈퇴</a>
	</td></tr>
	<tr><td>
		<a href="myPageReview.mall"
			class="list-group-item list-group-item-action ">찜리스트</a>
	</td></tr>
	<tr><td>
		<a href="myqna.qna"
			class="list-group-item list-group-item-action ">1:1문의</a>
	</td></tr>
	<tr><td>
		<a href="myReview.mall"
			class="list-group-item list-group-item-action ">마이리뷰</a>
	</td></tr>
</table>
	
