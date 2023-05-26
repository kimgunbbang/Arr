<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>구매 정보 상세</title>
</head>
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
 .row {
   display: flex;
   align-items: center;
 }

.col{
	display: inline;
}
img{
	width:80%;
	height:80%;
}
</style>
<body>
<div class="containerJoin">
<h3>구매날짜 : ${buyInfo.buy_date }</h3><hr>
<div class="form-group">
	<div class="row">
		<div class="col-3">사진</div>
		<div class="col-5">상품명</div>
		<div class="col-2">수량</div>
		<div class="col-2">금액</div>
	</div>
</div><hr>
	<c:set var="lastMoney" value="0"/>
	<c:forEach var="buy" items="${buyList }">
		<div class="row">
			<div class="col-3"><img src="${pageContext.request.contextPath}/images/${buy.p_image }"></div>
			<div class="col-5">${buy.p_name }</div>
			<div class="col-2">${buy.buy_qty }</div>
			<div class="col-2"><fmt:formatNumber value="${buy.buy_totalmoney }" pattern="#,###"/></div>
		</div>
	<c:set var="lastMoney" value="${lastMoney+ buy.buy_totalmoney}"/>
	</c:forEach><hr>
	<div class="form-group">
	<div class="row">
		전체금액  <fmt:formatNumber value="${lastMoney}" pattern="#,###"/>
	</div>
	</div><hr>
	<div class="row">
		결제방식 안넣을거임
	</div><hr>
<div class="form-group">
	<div class="row">
		구매자 : ${buyInfo.buy_name } <br>
		전화번호 : ${buyInfo.buy_phone } <br>
		주소 : ${buyInfo.buy_addr } <br>
		상세주소 : ${buyInfo.buy_addr2 } <br>
		배송메모 : ${buyInfo.deli_memo } <br>
	</div>
</div>
</div>
</body>
</html>