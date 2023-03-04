<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

function repwcheck(){
	
	if(${memeber.password}.val() != $('input[name=password]').val()){
		$('#pwmessage').html("<font color=red>비밀번호가 일치하지 않습니다.</font>");
		$('#pwmessage').show();
		pwsame = "nosame";
	}
	else{ // 값이 같다면
		$('#pwmessage').html("<font color=blue>비밀번호가 일치합니다.</font>");
		$('#pwmessage').show();
		pwsame = "same";
	}
}

/* 
    $(document).ready(function () {
        $('.js_btn_back').click(function () {
            window.location.href = '../mypage/index.php';
        });

        $('#formFind').validate({
            dialog: false,
            rules: {
                findPassword: {
                    required: true
                }
            },
            messages: {
                findPassword: {
                    required: "패스워드를 입력해주세요"
                }
            }, submitHandler: function (form) {
                var $target = $(':password#findPassword', form);
                if (!_.isEmpty($target.val())) {
                    var $ajax = $.ajax('../mypage/my_page_ps.php', {
                        type: "post", data: {
                            memPw: $target.val(),
                            mode: "verifyPassword"
                        }
                    });
                    $ajax.done(function (data, textStatus, jqXHR) {
                        console.log('gd_member ajax', data, textStatus, jqXHR);
                        var code = data.code;
                        var message = data.message;
                        if (_.isUndefined(code) && _.isUndefined(message)) {
                            top.location.href = "../mypage/my_page.php";
                        } else {
                            $(form).find('.member_warning').addClass('prior_wrong');
                            $(form).find('.text_warning').text(message);
                        }
                    });
                }
            }
        });
    });
  */
</script>
 
 

<body>
	<br><br><br>
      	<div class="row">
        	<div align="center">
				<h2>회원정보 수정</h2><br>
			</div>	
	<p align="center"><strong>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해 주세요.</strong></p><br>
	<form action="updatego.mem" method="post" align="center">
		<table border=0 align="center">
			<tr>
				<td><strong>아이디 </strong> </td>
				<td><strong>${member.id}</strong></td>
			</tr>
			<tr>
			
				<td><strong>비밀번호</strong></td>
				<td>
					<!-- <input type="hidden" name=pageNumber id="pageNumber"> -->
					<!-- <input type="hidden" name=num id="num"> -->
					<input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요." onKeyup="repwcheck()">
					<span id="pwmessage" style="display:none;"></span>
				</td>
			</tr>
			<tr>
			<td colspan=2></td>
			</tr>
			<tr>
			<td colspan=2></td>
			</tr>
			<tr class="btn_center_box" align="center">
				<td	colspan=2>
					
					<input type="submit" value="인증하기" id="btnSubmit">
				</td>
			</tr>
		</table>
	</form>
	