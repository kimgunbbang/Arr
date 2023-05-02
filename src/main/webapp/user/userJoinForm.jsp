<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
.container{
	text-align: center;
}
.table{
	text-align: center;
}
</style>
<script>

function chkForm(f) {

	if(f.user_pass.value.trim()==""){
		alert("비밀번호를 입력하세요.");
		f.user_pass.focus();
		return false;
	}
	if(f.user_pass.value.trim()!=f.passChk.value.trim()){
		alert("비밀번호가 일치하지 않습니다.");
		f.user_pass.value="";
		f.passChk.value="";
		f.user_pass.focus();
		return false;
	}
	if(f.passChk.value.trim()==""){
		alert("비밀번호 확인을 입력하세요.");
		f.passChk.focus();
		return false;
	}
	if(f.user_pass.value.trim()!=f.passChk.value.trim()){
		alert("비밀번호가 일치하지 않습니다.");
		f.user_pass.value="";
		f.passChk.value="";
		f.user_pass.focus();
		return false;
	}
	f.submit();
}
</script>
</head>
<body>
<div class="containor">
<form name="joinform" action="userJoinAction.u" method="post" onsubmit="return chkForm(this);">
	<div class="table">
		<div class="row">
			<h2>아르르와 동행하기</h2>
			<div class="열1">아이디 : </div>
			<div class="열1"><input type="text" name="id" id="id"></div>
			<div class="열2">비밀번호 : </div><div class="열2"><input type="password" name="user_pass" id="user_pass"> </div>
			<div class="열3">비밀번호 확인 : </div><div class="열3"><input type="password" name="passChk" id="passChk"> </div>
			<div class="열4">이름 : </div><div class="열4"><input type="text" name="user_name" id="user_name"> </div>
			<div class="열5">우편번호 : </div><div class="열5"><input type="text" name="user_zipcode" id="user_zipcode" size="6" readonly></div>
			<div class="열5"><input type="button" name="zipSearch" value="주소검색"  id="zipSearch" /> </div>
			<div class="열6">주소 : </div><div class="열6"><input type="text" name="user_addr" id="user_addr" readonly> </div>
			<div class="열7">상세 주소 : </div><div class="열7"><input type="text" name="user_addr2" id="user_addr2"></div>
			<div class="열8">전화번호 : </div><div class="열8"><input type="text" name="user_phone" id="user_phone"></div>
			<div class="열9">
			<input type="submit" value="회원가입">
			<input type="reset" value="다시작성">&nbsp;
			<input type="button" value="돌아가기" onclick="history.back()">
			</div>
		</div>	
	</div>
</form>
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("zipSearch").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	document.getElementById("user_zipcode").value = data.zonecode;
                document.getElementById("user_addr").value = data.address; // 주소 넣기
                document.querySelector("input[name=user_addr2]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
</html>