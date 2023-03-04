<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/common.jsp" %>
<%@ include file="../mall/main_top.jsp"%>

<style type="text/css">
	.error{
		color: red;
		font-size: 15;
		
	}

	#zipcode1,#zipcode2,#zipcode3,#zipcode4{
	
		margin: 5px;
	}
	.memmessage{
		color: gray;
		font-size: 12;
	}
</style>

 <%-- 
<c:set var="path" value="${pageContext.request.conte
xtPath}"/>


 --%>
 

<c:set var="path" value="<%=request.getContextPath() %>"/>
<script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 우편서비스 -->

<script type="text/javascript">

	$(document).ready(function(){
		//alert(11);
			
 		var isCheck = false;
		var isChange = false;
		var use;
		
		$('#id_check').click(function() {
			//alert(3);

			isCheck = true;
			
			$.ajax({
				url : "idcheck.mem",
				data : ({
					inputid : $("input[name=id]").val()
				}),
				success : function(data){
					//alert('data:' + data);
					if ($('input[name="id"]').val() == "") {
						$('#idMessage').html("<font color=red>아이디를 입력해주세요</font>");
						$('#idMessage').show();
					}

					else if(data=="YES"){
						$('#idMessage').html("<font color=blue>멋진 아이디네요!</font>");
						$('#idMessage').show();
						use = "possible";
						isChange= false;
					}
					else{
						$('#idMessage').html("<font color=red>이미 등록된 아이디 입니다</font>");
						$('#idMessage').show();
						use = "impossible";
					}
				}//success
			}); // ajax
		}); // click

		$('input[name=id]').keydown(function(){
			$('#idMessage').css('display','none');
			isChange = true;
			use = "";
		});
		
		$('#btnSubmit').click(function(){
			//alert(1);
			
			if(use == "impossible" ){
				alert('이미 사용중인 아이디 입니다.');
				return false;
			}
			else if(isCheck== false || isChange == true){
				alert("중복체크를 하세요");
				return false;
			}
		});//click
		 

	});//ready
	
	function repwcheck(){
		
		if($('input[name=password]').val() != $('input[name=repassword]').val()){
			$('#pwmessage').html("<font color=red>비밀번호가 일치하지 않습니다</font>");
			$('#pwmessage').show();
			pwsame = "nosame";
		}
		else{ // 값이 같다면
			$('#pwmessage').html("<font color=blue>비밀번호가 일치합니다</font>");
			$('#pwmessage').show();
			pwsame = "same";
		}
	}
	
 	function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
            	
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("zipcode4").value = extraAddr;
                
                } else {
                    document.getElementById("zipcode4").value = '';
                }
				
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode1').value = data.zonecode;
                
               // $('input[id="zipcode1"]').val(data.zonecode);
                
    			//alert($('input[id="zipcode1"]').val()); 

               // $('input[id=zipcode1]').val(data.zonecode);
              //  $('input[id=zipcode2]').val(addr);
               // alert(document.getElementById('zipcode1').value);
                document.getElementById("zipcode2").value = addr;
    			//alert($('input[id="zipcode2"]').val()); 
    			
               //alert(document.getElementById('zipcode2').value);
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("zipcode3").focus(); 
               // alert($('input[id="zipcode3"]').val());
            }
        }).open();
    };
    
    $(document).ready(function(){            
        var now = new Date();
        var year = now.getFullYear();
        var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
        var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());           
        //년도 selectbox만들기               
        for(var i = 1940 ; i <= 2003 ; i++) {
            $('#rrn1').append('<option value="' + i + '">' + i + '년</option>');    
        }

        // 월별 selectbox 만들기            
        for(var i=1; i <= 12; i++) {
            var rrn2 = i > 9 ? i : "0"+i ;            
            $('#rrn2').append('<option value="' + rrn2 + '">' + rrn2 + '월</option>');    
        }
        
        // 일별 selectbox 만들기
        for(var i=1; i <= 31; i++) {
            var rrn3 = i > 9 ? i : "0"+i ;            
            $('#rrn3').append('<option value="' + rrn3 + '">' + rrn3+ '일</option>');    
        }
        $("#rrn1  > option[value="+rrn1+"]").attr("selected", "true");        
        $("#rrn2  > option[value="+rrn2+"]").attr("selected", "true");    
        $("#rrn3  > option[value="+rrn3+"]").attr("selected", "true");       
      
    })
    
    function historyBack() {
		history.back();
	}

</script>


<body>

<br>
<hr>
    
<center>
<h2><b>회원가입</b></h2>
</center>
<hr>

<table class="table table-hover" style="width:45%;" border=0 >
	<form:form method="post" action="registerForm.mem" commandName="member" >
			

			<tr align="center">
				<td style="font-weight:bold; width:25%; background-color:fbfbfb;">가입유형</td>

				<td>
					<input type="radio" name="seller" id="seller" value="1" <c:if test="${member.seller eq '1'}"> checked </c:if>>판매자로 가입하기&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="seller" id="seller" value="0" <c:if test="${member.seller eq '0'}"> checked </c:if>>회원으로 가입하기
					<br><span class="memmessage">*판매자는 심사 후에 음식을 판매할수 있어요!</span>
					<br><form:errors cssClass="error" path="seller" />
				</td>
				
				
			</tr>
			
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">아이디</td>
				<td>
					<input type="text" name="id" id="id" style="height: 40px; width: 305px;" value="${member.id }">
					<input type="button" class="btn btn-primary" id="id_check" style="height: 40px; width: 119px;" value="중복체크">
					<br><span id="idMessage"></span>
					<br><span class="memmessage">*아이디는 영문, 숫자만 가능하며 5 ~ 10자리까지 가능합니다</span>
					<br><form:errors cssClass="error" path="id" />
				</td>
			</tr>

			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">닉네임</td>

				<td>
					<input type="text" name="name" id="name" style="height: 40px; width: 430px;" value="${member.name }">
					<br><form:errors cssClass="error" path="name" />
				</td>
			</tr>
	
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">비밀번호</td>

				<td>
					<input type="password" name="password" id="password" style="height: 40px; width: 430px;" onBlur="pwcheck()">
					<br><span class="memmessage">*비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다</span>
					<br><form:errors cssClass="error" path="password"/>
				</td>
			</tr>
		
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">비밀번호 확인</td>

				<td>
					<input type="password" name="repassword" id="repassword" style="height: 40px; width: 430px;" onKeyup="repwcheck()">
					<br><span id="pwmessage" style="display:none;"></span>
				</td>
			</tr>
		
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">이메일</td>

				<td>
					<input type="text" name="email1" id="email1" style="height: 40px; width: 294px; value="${member.email1 }">
					
					<select class="box" id="email-list" name="email2" style="height: 40px; width: 130px; value="${member.email2 }">
						  <option value="@naver.com">@naver.com</option>
						  <option value="@google.com">@google.com</option>
						  <option value="@hanmail.net">@hanmail.net</option>
						  <option value="@nate.com">@nate.com</option>
						  <option value="@kakao.com">@kakao.com</option>
					</select>
					<br><form:errors cssClass="error" path="email1"/>
				</td>
			</tr>

			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">생년월일</td>

				<td>
					<select name="rrn1" id="rrn1" style="height: 40px; width: 139px;" value="${member.rrn1 }"></select>
					<select name="rrn2" id="rrn2" style="height: 40px; width: 139px;" value="${member.rrn2 }"></select>
					<select name="rrn3" id="rrn3" style="height: 40px; width: 139px;" value="${member.rrn2 }"></select>
					<br><form:errors cssClass="error" path="rrn1"/>
					
				</td>
			</tr>
			
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">핸드폰 번호</td>

				<td>
					<input type="text" name="hp1" id="hp1" style="height: 40px; width: 130px; value="${member.hp1 }"> -
					
					<input type="text" name="hp2" id="hp2" style="height: 40px; width: 130px;" value="${member.hp2 }"> -
					
					<input type="text" name="hp3" id="hp3" style="height: 40px; width: 130px;" value="${member.hp3 }">
					<br><form:errors cssClass="error" path="hp1"/>
				</td>
			</tr>
			
			<tr align="center">
				<td style="align:center; font-weight:bold; background-color:fbfbfb;">배송지</td>

				<td>
					<input type="text" name="zipcode1" id="zipcode1" style="height: 40px; width: 305px;" value="${member.zipcode1 }">
					<input type="button" class="btn btn-primary" id="zipcode_w" name ="zipcode_w" onclick="sample6_execDaumPostcode()" style="height: 40px; width: 119px;" value="우편번호">
					<br>
					<input type="text" name="zipcode2" id="zipcode2" style="height: 40px; width: 430px;" value="${member.zipcode2 }">
					<br>
					<input type="text" name="zipcode4" id="zipcode4" style="height: 40px; width: 430px;" value="${member.zipcode4 }">

					<input type="text" name="zipcode3" id="zipcode3" style="height: 40px; width: 430px;" value="${member.zipcode3 }">
					<br><form:errors cssClass="error" path="zipcode1"/>
				</td>
			</tr>
 			
 			<tr align="center">
 				<td colspan="2">
					<input type="button" class="btn btn-secondary btn-lg" value="취소" onclick="historyBack()" style="height: 50px; width: 212px; font-size: 16px; ">
					<input type="submit" class="btn btn-primary btn-lg" value="가입하기" id="btnSubmit" style="height: 50px; width: 212px; font-size: 16px;">
				</td><br>
			</tr>
	</form:form>
</table>

<br><br>
<%@ include file="../mall/main_bottom.jsp"%>

