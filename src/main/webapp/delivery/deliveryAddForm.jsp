<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<head>
<meta charset="UTF-8">
<title>배송지관리</title>
<style>
.container{
	text-align: center;
}
.table{
	text-align: center;
}
</style>

</head>
<body>
<div class="containerJoin">
<form name="deliform" action="deliveryAddAction.del" method="post">

			<h2>배송지 관리</h2>
			<input type="hidden" name="id" id="id" value="${sessionScope.id }" readonly>
			<div class="form-group">
			배송지명 <input type="text" name="deli_name" id="deli_name">
			</div>
			<div class="form-group">
			우편번호 <input type="text" name="deli_zipcode" id="deli_zipcode" size="6" readonly>
			</div>
			<div class="form-group">
			<input type="button" name="zipSearch" value="주소검색"  id="zipSearch" class="zip-search-button"/>
			</div>
			<div class="form-group">
			배송지 주소 <input type="text" name="deli_addr" id="deli_addr" readonly>
			</div>
			<div class="form-group">
			배송지 상세 주소 <input type="text" name="deli_addr2" id="deli_addr2">
			</div>
			<div class="form-group">
			배송인 성함 <input type="text" name="deli_username" id="deli_username">
			</div>
			<div class="form-group">
			수령인 전화번호 <input type="text" name="deli_phone" id="deli_phone">
			</div>
			<div class="form-group">
			<input type="submit" value="배송지 등록" class="zip-search-button">
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
            	document.getElementById("deli_zipcode").value = data.zonecode;
                document.getElementById("deli_addr").value = data.address; // 주소 넣기
                document.querySelector("input[name=deli_addr2]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
</html>