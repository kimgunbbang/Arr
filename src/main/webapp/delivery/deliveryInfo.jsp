<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<body>
<div class="containerJoin">
			<input type="hidden" name="deli_num" id="deli_num" value="${delivery.deli_num }">
			<div class="form-group">
			배송지명 <input type="text" value="${delivery.deli_name }">
			</div>
			<div class="form-group">
			우편번호 <input type="text" value="${delivery.deli_zipcode }">
			</div>
			<div class="form-group">
			배송주소 <input type="text" value="${delivery.deli_addr }">
			</div>
			<div class="form-group">
			배송상세주소 <input type="text" value="${delivery.deli_addr2 }">
			</div>
			<div class="form-group">
			수령인 <input type="text" value="${delivery.deli_username }">
			</div>
			<div class="form-group">
			수령인전화번호 <input type="text" value="${delivery.deli_phone }">
			</div>
			<div class="form-group">
			<a href="deliveryModifyForm.del?deli_num=${delivery.deli_num}" class="zip-search-button" style="text-decoration: none;">수정</a>
			<a href="deliveryDeleteAction.del?deli_num=${delivery.deli_num}" onclick="confirmDelete('${delivery.deli_num}', event)" class="zip-search-button" style="text-decoration: none;">삭제</a>
			</div>

<script>
function confirmDelete(userId, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 삭제하시겠습니까?")) {
	    location.href = "deliveryDeleteAction.del?deli_num=" + userId;
	  }
	}
</script>
			
</div>
</body>
</html>