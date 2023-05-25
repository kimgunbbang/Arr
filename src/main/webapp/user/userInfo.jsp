<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
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
      <h2>회원 정보</h2><hr>
        <div class="form-group">
            아이디 <input type="text" value="${user.id}"readonly>
        </div>
        <div class="form-group">
            비밀번호 <input type="text" value="${user.user_pass}"readonly>
        </div>
        <div class="form-group">
            이름 <input type="text" value="${user.user_name}"readonly>
        </div> 
        <div class="form-group">
            우편번호 <input type="text" value="${user.user_zipcode}" readonly>
        </div>  
        <div class="form-group">
            주소 <input type="text" value="${user.user_addr}" readonly>
        </div>    
        <div class="form-group">
            상세주소 <input type="text" value="${user.user_addr2}" readonly>
        </div> 
        <div class="form-group">
            전화번호 <input type="text" value="${user.user_phone}" readonly>
        </div>
        
      <div class="form-group">
        <a href="userModifyForm.u?id=${user.id}" class="zip-search-button" style="text-decoration: none;">수정</a>
        <a href="deliveryListAction.del?id=${user.id}" class="zip-search-button" style="text-decoration: none;">배송지관리</a>
        <a href="#" class="zip-search-button" onclick="confirmDelete('${user.id}', event)" style="text-decoration: none;">탈퇴</a>
      </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
function confirmDelete(userId, event) {
  event.preventDefault(); // 이벤트의 기본 동작을 취소

  if (confirm("정말 탈퇴하시겠습니까?")) {
    location.href = "userByeAction.u?id=" + userId;
  }
}
</script>
</body>
</html>