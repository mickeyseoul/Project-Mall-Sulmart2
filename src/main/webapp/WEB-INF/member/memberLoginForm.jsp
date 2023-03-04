<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../mall/main_top.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Signin Template · Bootstrap v5.0</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">

    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">


<style type="text/css">

/* 
body {
        height: 100vh;
        width: 100vw;
        background-image: url('');
        background-repeat : no-repeat;
        background-size : cover;
      }
       */
#title {
	text-align: light;
	font-size: 30pt;
	color: #000000;
	margin-top:10%;
	margin-bottom:10%

  .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
}


</style>





<!-- <form action="login.mem" method="post"> -->

<center>

<form action="login.mem" method="post">

	<div id="title">Login</div><br><br>
		
    <div>
      <input type="text"  style=" width: 430px;" class="form-control" name="id" id="id" placeholder="아이디를 입력해주세요">
      <label for="floatingInput"></label>
    </div>

    <div>
      <input type="password" style=" width: 430px;" class="form-control" name="password" id="password" placeholder="비밀번호를 입력해주세요">
      <label for="floatingPassword"></label>
    </div>

	<button class="btn btn-primary btn-lg" type="submit" style=" width: 430px;">Sign in</button>

		<br><br>
		<div>
			<a style="text-decoration:none;" href="registerForm.mem">회원가입 |</a>
		
			<a style="text-decoration:none;" href="findId.mem">아이디찾기 |</a>
			
			<a style="text-decoration:none;" href="findpw.mem">비밀번호찾기</a>
		</div>
		
	
</form>

</center>

<br><br><br><br><br><br>
<%@ include file="../mall/main_bottom.jsp"%>

