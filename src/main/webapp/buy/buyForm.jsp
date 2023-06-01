<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"/>

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
<html>
<head>
<meta charset="UTF-8">
<title>구매 정보 입력</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
window.onload = function(){
    document.getElementById("zipSearch").addEventListener("click", function(){
        new daum.Postcode({
            oncomplete: function(data) {
            	document.getElementById("deli_zipcode").value = data.zonecode;
                document.getElementById("deli_addr").value = data.address;
                document.querySelector("input[name=deli_addr2]").focus();
            }
        }).open();
    });
}

function setDeliveryInfo(selectBox) {
	  var selectedOption = selectBox.options[selectBox.selectedIndex];
	  var name = selectedOption.getAttribute('data-name');
	  var phone = selectedOption.getAttribute('data-phone');
	  var zipcode = selectedOption.getAttribute('data-zipcode');
	  var addr = selectedOption.getAttribute('data-addr');
	  var addr2 = selectedOption.getAttribute('data-addr2');

	  document.getElementById('deli_username').value = name;
	  document.getElementById('deli_phone').value = phone;
	  document.getElementById('deli_zipcode').value = zipcode;
	  document.getElementById('deli_addr').value = addr;
	  document.getElementById('deli_addr2').value = addr2;
}
function cancelSubmit(event) {
	  event.preventDefault();
	  history.back();
}
</script>
<%int num=0; %>
</head>
<body>
<div class="container">
	<h2 style="text-align: center;">구매 정보 입력</h2><hr>
	<form action="buyAction.buy" method="post" name="buyForm" class="needs-validation" novalidate>
		<c:if test="${not empty cartList}">
		<c:forEach var="cart" items="${cartList}" varStatus="idx">
			<input type="hidden" name="cart_num" value="${cart}">
		</c:forEach>
		</c:if>
	
		<input type="hidden" name="id" id="${buy.id }" value="${buy.id }"><br>
		<div class="row" style="text-align: center;">
			<div class="col">순번</div>
			<div class="col">상품이미지</div>
			<div class="col">구매수량</div>
			<div class="col">금액</div>
		</div><hr>
		<c:forEach var="buy" items="${buyList }" varStatus="idx">
		<div class="row" style="text-align: center;">
			<div class="col"><%=++num %></div>
			<div class="col"><img src="${pageContext.request.contextPath }/images/${buy.p_image }" style="width:20%;"></div>
			<input type="hidden" name="p_num" id="p_num" value="${buy.p_num }">
			<div class="col">${buy.buy_qty }</div>
			<input type="hidden" name="buy_qty" id="buy_qty" value ="${buy.buy_qty }" onchange='qtychage(event)'>&nbsp;
			<input type="hidden" name="p_price" id="p_price" value ="${p_price }" >
			
			<div class="col"><fmt:formatNumber value="${buy.buy_totalmoney }" pattern="#,###"/></div>
			<input type="hidden" name="buy_totalmoney" id="buy_totalmoney"
				value="${buy.buy_totalmoney}">
		</div>
		</c:forEach><hr>
		<b>총금액 : <fmt:formatNumber value="${lastTotalMoney }" pattern="#,###"/></b><br>
			<c:if test="${sessionScope ne null and not empty deliveryList}">
		<div class="form-group">
			<label for="deli_num">배송지 선택:</label>
			<select name="deli_num" id="deli_num" class="form-control" onchange="setDeliveryInfo(this)">
				<c:set var="first" value="true"/>
				<c:forEach var="delivery" items="${deliveryList}">
					<option value="${delivery.deli_num}" data-name="${delivery.deli_name}" 
						data-phone="${delivery.deli_phone}" 
						data-zipcode="${delivery.deli_zipcode}" 
						data-addr="${delivery.deli_addr}" 
						data-addr2="${delivery.deli_addr2}"
						${first? 'selected':'' }>
						${delivery.deli_name}
					</option>
					<c:set var="first" value="false"/>
				</c:forEach>
				<option value="newDelivery">신규배송지</option>
			</select>
		</div>
	</c:if>

	<br>
	
	<div class="form-group">
		<label for="deli_username">수령인:</label>
		<input type="text" name="deli_username" id="deli_username" class="form-control" 
			value="${not empty deliveryList? deliveryList[0].deli_name:''}" required>
		<div class="invalid-feedback">수령인을 입력해주세요.</div>
	</div>

	<div class="form-group">
		<label for="deli_phone">수령인 전화번호:</label>
		<input type="text" name="deli_phone" id="deli_phone" class="form-control" 
			value="${not empty deliveryList? deliveryList[0].deli_phone:''}" required>
			<div class="invalid-feedback">수령인 전화번호를 입력해주세요.</div>
	</div>

	<div class="form-group">
		<label for="deli_zipcode">우편번호:</label>
		<div class="input-group">
			<input type="text" name="deli_zipcode" id="deli_zipcode" class="form-control" 
				value="${not empty deliveryList? deliveryList[0].deli_zipcode:''}" readonly>
			<div class="input-group-append">
				<input type="button" name="zipSearch" value="주소검색" id="zipSearch" class="btn btn-primary">
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="deli_addr">배송지:</label>
		<input type="text" name="deli_addr" id="deli_addr" class="form-control" 
			value="${not empty deliveryList? deliveryList[0].deli_addr:''}" readonly required>
			<div class="invalid-feedback">배송지를 입력해주세요.</div>
	</div>

	<div class="form-group">
		<label for="deli_addr2">상세주소:</label>
		<input type="text" name="deli_addr2" id="deli_addr2" class="form-control" 
			value="${not empty deliveryList? deliveryList[0].deli_addr2:''}">
	</div>
		<div class="form-group">
		<label for="deli_memo">배송시 요청사항:</label>
		<textarea rows="5" cols="50" name="deli_memo" id="deli_memo" class="form-control"></textarea>
	</div>
	
	<input type="submit" value="구매하기" class="btn btn-primary">
	<button onclick="cancelSubmit(event)" class="btn btn-secondary">취소하기</button>
</form>
	</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
		