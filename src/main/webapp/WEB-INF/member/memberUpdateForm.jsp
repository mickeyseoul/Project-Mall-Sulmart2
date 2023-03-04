<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/common.jsp" %>
<%@ include file="../mall/main_top.jsp"%>
 
<style type="text/css">
	.error{
		color: red;
		font-size: 15;
	}
	label {
		align: center;
		font-weight: bold;
	}
	
	#zipcode1,#zipcode2,#zipcode3,#zipcode4{
	
		margin: 5px;
	}
</style>

 <%-- 
<c:set var="path" value="${pageContext.request.conte
xtPath}"/>


 --%>
 

<c:set var="path" value="<%=request.getContextPath() %>"/>


<script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/addressapi.js"></script>

 -->
<script type="text/javascript">

	
	$(document).ready(function(){
		
 		var isCheck = false;
		var isChange = false;
		var use;
		
		$('#pw_check').keydown(function() {
			
			
			isCheck = true;
			
			$.ajax({
				url : "pwcheck.mem",
				data : ({
					inputid : $("input[name=password]").val()
				}),
				success : function(data){

					if(data=="YES"){
						$('#pwMessage').html("<font color=blue>기존 비밀번호가 일치합니다</font>");
						$('#pwMessage').show();
						use = "possible";
						isChange= false;
					}
					else if(data=="NO"){
						$('#pwMessage').html("<font color=red>기존 비밀번호가 일치하지 않습니다</font>");
						$('#pwMessage').show();
						use = "impossible";
					}
				}//success
			}); // ajax
		}); // click
		
		
		
	});//ready
		
	
	
	function repwcheck(){
		
		if($('input[name=password]').val() != $('input[name=repassword]').val()){
			$('#pwmessage').html("<font color=red>새 비밀번호가 일치하지 않습니다.</font>");
			$('#pwmessage').show();
			pwsame = "nosame";
		}
		else{ // 값이 같다면
			$('#pwmessage').html("<font color=blue>새 비밀번호가 일치합니다.</font>");
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
    
    function memberdelete() {
    	
        var result = confirm("정말 탈퇴하겠습니까?");

        if (result) {
            alert("탈퇴가 완료되었습니다");
    		return location.href='delete.mem?num=${member.num }';
    		//return location.href='logout.jsp';
        }
        else {
            alert("취소했습니다.");
            return history.back();
        }
	}
      
    function memberupdate() {
    	
        	alert("수정되었습니다");
	}

</script>


<body>

<br>
<hr>
    
<center>
<h2><b>회원정보 수정</b></h2>
</center>
<hr>

<c:if test="${ loginInfo.id ne 'admin' }">
<%@ include file="../mall/myPage_top.jsp"%>
<br>
<table id="mem" style="width: 72%;">
	<tr class="table-light" align="left">
		<td colspan="5"><b>${loginInfo.name }(${loginInfo.id })</b> 님의	회원정보 수정</td>
	</tr>
</table>

</c:if>

	
	
<table id="mem" class="table table-hover" border="0" style="width: 72%;">
	<form:form method="post" action="update.mem" commandName="member" onsubmit="memberupdate()">
		<tr>
			<td style="border-top: none;"><strong  style="color:007bff;">${member.id }</strong> 님은 
			<strong  style="color:007bff;">
				<c:set var="seller" value="0" /><c:if test="${member.seller eq '0'}"> <c:out value="회원" /></c:if>
				<c:set var="seller" value="1" /><c:if test="${member.seller eq '1'}"> <c:out value="판매자" /></c:if></strong> 입니다!</td>
		</tr>
		<tr>
			<td style="background-color:fbfbfb;">
				<input type="hidden" name="id" id="id" value="${member.id }">
	 			<input type="hidden" name="num" id="num" value="${member.num }">
				<input type="hidden" name="pageNumber" value="${pageNumber }">
				<input type="hidden" name="seller" value="${member.seller }">
				
				<label for="id">아이디</label>
				<%-- 
				<input type="text" name="id" id="id" value="${member.id }">
				<input type="button" id="id_check" value="중복체크">
				<span id="idMessage"></span>
				<form:errors cssClass="error" path="id" />
				
			 --%>
			</td>
			<td style="color:007bff; font-weight:bold;" >
				 <bold>${member.id }</bold>
			</td>
		</tr>
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="name" >닉네임 </label>
			</td>
			<td>
				<input type="text" name="name" id="name" value="${member.name }">
				<form:errors cssClass="error" path="name" />
			</td>
		</tr>
	<%-- 
		<tr>
			<td>
				<label for="password">현재 비밀번호 </label>
			</td>
			<td>
				<input type="password" name="password" id="password" onBlur="pwcheck()">
				<span id="pwMessage" style="display:none;"></span>
				<form:errors cssClass="error" path="password"/>
			</td>
		</tr>
		 --%>
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="password">새 비밀번호 </label>
			</td>
			<td>
				<input type="password" name="password" id="password" onBlur="pwcheck()">
				<form:errors cssClass="error" path="password"/>
			</td>
		</tr>
	 
		<tr>
			<td style="background-color:fbfbfb;">
				<label>새 비밀번호 확인 </label>
			</td>
			<td>
				<input type="password" name="repassword" id="repassword" onKeyup="repwcheck()">
				<span id="pwmessage" style="display:none;"></span>
			</td>
		</tr>
		 
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="email">이메일 </label>
			</td>
			<td>
				<input type="text" name="email1" id="email1" value="${member.email1 }">
				<select class="box" id="email-list" name="email2" value="${member.email2 }">
						  <option value="@naver.com">@naver.com</option>
						  <option value="@google.com">@google.com</option>
						  <option value="@hanmail.net">@hanmail.net</option>
						  <option value="@nate.com">@nate.com</option>
						  <option value="@kakao.com">@kakao.com</option>
				</select>
				<form:errors cssClass="error" path="email1"/>
			</td>
		</tr>
		
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="rrn1">생년월일 </label>
			</td>
			<td>
				<select name="rrn1" id="rrn1" style="width: 100px;" value="${member.rrn1 }"></select>
				<select name="rrn2" id="rrn2" style="width: 100px;" value="${member.rrn2 }"></select>
				<select name="rrn3" id="rrn3" style="width: 100px;" value="${member.rrn3 }"></select>
				<form:errors cssClass="error" path="rrn1"/>
			</td>
		</tr>
		
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="hp1">핸드폰 </label>
			</td>
			<td>
				<input type="text" name="hp1" id="hp1" style="width: 80px;" value="${member.hp1 }">-
				
				<input type="text" name="hp2" id="hp2"  style="width: 90px;" value="${member.hp2 }">-
				
				<input type="text" name="hp3" id="hp3"  style="width: 90px;" value="${member.hp3 }">
				<form:errors cssClass="error" path="hp1"/>
			</td>
		</tr>
		
		<tr>
			<td style="background-color:fbfbfb;">
				<label for="zipcode1">배송지 </label>
			</td>
			<td>
				<input type="text" name="zipcode1" id="zipcode1" style="width: 90px;" value="${member.zipcode1 }">
				<input type="button" class="btn btn-primary btn-sm" id="zipcode_w" onclick="sample6_execDaumPostcode()" name ="zipcode_w" value="우편번호"><br>
				
				<input type="text" name="zipcode2" id="zipcode2" style="width: 405px;" value="${member.zipcode2 }"><br>
				
				<input type="text" name="zipcode4" id="zipcode4" style="width: 150px;" value="${member.zipcode4 }">
				<input type="text" name="zipcode3" id="zipcode3" style="width: 240px;" value="${member.zipcode3 }">
				<form:errors cssClass="error" path="zipcode1"/>
			</td>
		</tr>
	
 
		<tr>
			<td colspan="2" align="center">
			<br>
				<button type="button" class="btn btn-secondary btn-lg" onclick="memberdelete()" style="height: 50px; width: 210px; font-size: 16px;">탈퇴하기</button>
				<button type="submit" id="btnSubmit" class="btn btn-primary btn-lg" style="height: 50px; width: 210px; font-size: 16px;">수정하기</button>
<!-- 				<input type="button" class="btn btn-secondary btn-lg" onclick="memberdelete()" style="height: 50px; width: 210px; font-size: 16px;" value="탈퇴하기"> -->
				<!-- <input type="button" class="btn btn-primary btn-lg" style="height: 50px; width: 210px; font-size: 16px;" id="btnSubmit" value="수정하기" onclick="memberupdate()"> -->
 				<!-- <input type="submit" class="btn btn-primary btn-lg" style="height: 50px; width: 210px; font-size: 16px;" value="수정하기" id="btnSubmit"> -->
			</td><br>
		</tr>
	</form:form>
	</table>
