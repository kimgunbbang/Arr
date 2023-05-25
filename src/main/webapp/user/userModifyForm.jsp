<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<style>
.containerJoin {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
 
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input[type="text"],
.form-group input[type="password"] {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.form-group .button-container {
  margin-top: 20px;
  text-align: center;
}

.form-group .button-container button {
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.form-group .button-container button:not(:last-child) {
  margin-right: 10px;
}

.form-group .button-container button:hover {
  background-color: #0056b3;
}

.form-group .button-container button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
/* 중복확인 버튼 스타일 */
.duplicate-button {
padding: 5px 10px;
font-size: 16px;
border-radius: 5px;
background-color: #27737c;
color: #fff;
border: none;
cursor: pointer;
margin-top: 10px;
}

.duplicate-button:hover {
background-color: #1b4e54;
}

/* 주소검색 버튼 스타일 */
.zip-search-button {
padding: 5px 10px;
font-size: 16px;
border-radius: 5px;
background-color: #27737c;
color: #fff;
border: none;
cursor: pointer;
margin-top: 10px;
}

.zip-search-button:hover {
background-color: #1b4e54;
}

/* 회원가입 버튼 스타일 */
.signup-button {
padding: 10px 20px;
font-size: 16px;
border-radius: 5px;
background-color:#27737c;
color: #fff;
border: none;
cursor: pointer;
width: 100%;
margin-top: 10px;
}

.signup-button:hover {
background-color: #1b4e54;
}
</style>
<script type="text/javascript">
function changePW(chk){
	if(chk.checked){
		document.getElementById("dspPWChange").style.display="block";
	}else{
		document.getElementById("dspPWChange").style.display="none";
	}
}

function modifyFormSubmit(f){
	if(f.user_pwch.checked){
		if(f.user_pass.value != f.user_pwchk.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.user_pass.value="";
			f.user_pwchk.value="";
			f.user_focus();
			return false;
		}
	}
	f.submit();
}
</script>
</head>
<body>
<div class="containerJoin">
<form action="userModifyAction.u" method="post" name="modiform">
	<input type="hidden" name="id" id="id" value="${user.id }">
			<h2>회원정보수정하기</h2>
			 <div class="form-group">
			      <label for="id">아이디</label><input type="text" value="${user.id }" readonly>
			 </div>
			 <div class="form-group">
				<div><input type="checkbox" name="user_pwch" id="user_pwch" onclick="changePW(this)">비밀번호변경</div>
				<div id="dspPWChange" style="display:none">
				비밀번호 :<input type="password" name="user_pass" id="user_pass">
				비밀번호 확인 :<input type="password" name="user_pwchk" id="user_pwchk">
				</div>
			 </div>
			 <div class="form-group">
			 	이름  <input type="text" name="user_name" id="user_name" value="${user.user_name }">
			 </div>
			 <div class="form-group">
			 	우편번호 :<input type="text" name="user_zipcode" id="user_zipcode" value="${user.user_zipcode }" readonly>
			 </div>
			 <div class="form-group">
			 	<input type="button" name="zipSearch" value="주소검색"  id="zipSearch" class="zip-search-button"/>
			 </div>
			 <div class="form-group">
			 	주소 : <input type="text" name="user_addr" id="user_addr" value="${user.user_addr }">
			 </div>
			 <div class="form-group">
				상세 주소 : <input type="text" name="user_addr2" id="user_addr2" value="${user.user_addr2 }">
			 </div>
			 <div class="form-group">
				전화번호 : <input type="text" name="user_phone" id="user_phone" value="${user.user_phone }">
			 </div>
			 
			<div class="form-group">
			<input type="submit" value="정보수정" class="zip-search-button">
			<input type="reset" value="다시작성" class="zip-search-button">&nbsp;
			<input type="button" value="돌아가기" onclick="history.back()" class="zip-search-button">
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