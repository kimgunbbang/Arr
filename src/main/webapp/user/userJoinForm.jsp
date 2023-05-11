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
var idChecked = false;

function winopen() {
	var id = document.getElementById('id');
	if(document.joinform.id.value =="" || document.joinform.id.value.length < 0){
		alert("아이디를 먼저 입력하세요.");
		document.joinform.id.focus();
	}else{
		window.open("idCheck.jsp?id="+document.joinform.id.value,"","width=500, height=300");
		idChecked = true;
	}
}
function checkPasswordFormat(str) {
	  const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/; // 비밀번호 형식을 정규식으로 설정 (8자 이상, 영문과 숫자의 조합)
	  return regex.test(str);
	}
	

function chkForm(f) {
	if (!idChecked) {
		alert("아이디 중복확인을 해주세요.");
		return false;
	}
	
	if(f.id.value.trim() =="" || f.id.value.length < 0){
	    alert("아이디를 입력하세요.");
	    f.id.focus();
	    return false;
	}

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
	
	if(!checkPasswordFormat(f.user_pass.value.trim())) { // 비밀번호 형식 체크
		alert("비밀번호는 영문과 숫자의 조합으로 8자 이상이어야 합니다.");
		f.user_pass.value = "";
	    f.passChk.value = "";
		f.user_pass.focus();
		return false;
 	}
	if (!checkIdFormat(f.id.value.trim())) { // 아이디 형식 체크
		alert("아이디는 영문과 숫자의 조합으로 4자 이상이어야 합니다.");
		f.id.value = "";
		f.id.focus();
		return false;
		}
	f.submit();
}

function setIdChecked(checked) {
	idChecked = checked;
}

function chkCharCode(event) {
	  const regExp = /[^0-9a-zA-Z]/g;
	  const ele = event.target;
	  if (regExp.test(ele.value)) {
	    ele.value = ele.value.replace(regExp, '');
	  }
	};
	
function chkCharCode2(event) {
	  const keyCode = event.keyCode;
	  const isValidKey = (
	    (keyCode >= 48 && keyCode <= 57) || // Numbers
	    (keyCode >= 97 && keyCode <= 122) || // Numbers, Keypad
	    (keyCode >= 65 && keyCode <= 90) || // Alphabet
	    (keyCode === 32) || // Space
	    (keyCode === 8) || // BackSpace
	    (keyCode === 189) // Dash
	  );
	  if (!isValidKey) {
	    event.preventDefault();
	    return false;
	  }
	};
</script>
</head>
<body>
<div class="containor">
<form name="joinform" action="userJoinAction.u" method="post" onsubmit="return chkForm(this);">
	<div class="table">
		<div class="row">
			<h2>아르르와 동행하기</h2>
			<div class="열1">아이디 : </div>
			<div class="열1"><input type="text" name="id" id="id" pattern="[a-zA-Z0-9]" onkeyup="chkCharCode(event)"  onkeypress="chkCharCode2(event)">
			<input type="button" value="중복확인" onclick="winopen()"><br>
			</div>
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