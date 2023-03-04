<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../mall/main_top.jsp"%>

<style type="text/css">
	.error{
		color: red;
		font-size: 15;
		
	}
	label {
		text-align: center;
		align: center;
		font-weight: bold;
		color:007bff;
	}
	/* table{
		width : 50%;
	}
	td{
		text-align : center;
	}*/

</style>



<script type="text/javascript">

	window.onload = function() {
		document.getElementById('submit').onclick = function() {
			
			if ( document.Idfind.name.value.trim() == '' ) {
				alert( '이름을 입력해주세요' );
				return false;
			}
			if ( document.Idfind.email.value.trim() == '' ) {
				alert( '이메일을 입력해주세요' );
				return false;
			}
			document.Idfind.submit();
		}
	}
		
	function historyBack() {
		history.back();
	}


</script>

<br>
<hr>
    
<center>
<h2><b>아이디 찾기</b></h2>
</center>
<hr>

<form action="findId.mem" method="post" name="Idfind">
		<br><br>
      	<!-- <div class="row">
        	<div align="center">
				<h3><bold>아이디 찾기</bold></h3>
			</div>	
		</div> -->	
			<br>
	<table class="table table-hover" border="0" align="center" class="mo"
		style="border: solid 4px margin:4%; width: 430px;">
		<tr height="20px" align="center">
			<td>
				<label for="name">닉네임 </label>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="text" name="name" id="name" style="height: 40px; width: 430px;" placeholder="이름을 입력하세요.">
			</td>
		</tr>
		
		<tr align="center">
			<td>
				<label for="email">가입메일주소 </label>
				
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="text" name="email1" id="email1"  style="height: 40px; width: 294px;" placeholder="이메일을 입력하세요.">
				<select class="box" id="email-list" name="email2" style="height: 40px; width: 131px;">
					<option value="@naver.com">@naver.com</option>
					<option value="@google.com">@google.com</option>
					<option value="@hanmail.net">@hanmail.net</option>
					<option value="@nate.com">@nate.com</option>
					<option value="@kakao.com">@kakao.com</option>
				</select>
			</td>
		</tr>
		
		<tr>
			
			<td colspan="2" align="center">
				<input type="button" class="btn btn-secondary btn-lg" onclick="historyBack()" style="height: 50px; width: 212px; font-size: 16px;" value="취소">
				<input type="submit" class="btn btn-primary btn-lg"  style="height: 50px; width: 212px; font-size: 16px;" value="검색">
			

			</td>
		</tr>
	</table>
</form>


<br><br><br><br>
<%@ include file="../mall/main_bottom.jsp"%>

