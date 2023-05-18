<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
.container1 {
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
</style>
</head>
<body>
<div class="container1">
  <h2>아르르와 동행하기</h2>
  <form name="joinform" action="userJoinAction.u" method="post" onsubmit="return chkForm(this);">
    <div class="form-group">
      <label for="id">아이디</label>
      <input type="text" name="id" id="id" pattern="[a-zA-Z0-9]" onkeyup="chkCharCode(event)" onkeypress="chkCharCode2(event)">
      <input type="button" value="중복확인" onclick="winopen()" >
    </div>
    <div class="form-group">
      <label for="user_pass">비밀번호</label>
      <input type="password" name="user_pass" id="user_pass">
    </div>
    <div class="form-group">
      <label for="passChk">비밀번호 확인</label>
      <input type="password" name="passChk" id="passChk">
    </div>
    <div class="form-group">
      <label for="user_name">이름</label>
      <input type="text" name="user_name" id="user_name">
    </div>
    <div class="form-group" >
      <label>우편번호</label>
      <input type="text" name="user_zipcode" id="user_zipcode" size="6" readonly>
      <input type="button" name="zipSearch" value="주소검색" id="zipSearch" >
    </div>
    <div class="form-group">
      <label>주소</label>
      <input type="text" name="user_addr" id="user_addr" readonly>
    </div>
    <div class="form-group">
      <label>상세 주소</label>
      <input type="text" name="user_addr2" id="user_addr2">
    </div>
    <div class="form-group">
      <label>전화번호</label>
      <input type="text" name="user_phone" id="user_phone">
    </div>
    <div class="form-group button-container">
      <button type="submit">회원가입</button>
      <button type="reset">다시작성</button>
      <button type="button" onclick="history.back()">돌아가기</button>
    </div>
  </form>
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("zipSearch").addEventListener("click", function(){
        new daum.Postcode({
            oncomplete: function(data) {
              document.getElementById("user_zipcode").value = data.zonecode;
              document.getElementById("user_addr").value = data.address;
              document.querySelector("input[name=user_addr2]").focus();
            }
        }).open();
    });
}
</script>
</html>