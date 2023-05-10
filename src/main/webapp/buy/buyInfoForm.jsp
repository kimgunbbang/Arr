<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
  .form-border {
    border: 1px solid #ccc; /* 외곽선 스타일 지정 */
    padding: 10px; /* 폼 요소와 외곽선 간격 조정 */
  }
</style>
<head>
<meta charset="UTF-8">
<title>주문상세정보</title>
</head>
<body>
<div class="container">
<h1>주문상세정보</h1><hr>
<form class="form-border">
	<h3>주문날짜 : ${buyInfo.buy_date }</h3>
	<h4>배송지</h4>
	수령인 : ${buyInfo.buy_name }<br>
	수령인 연락처 : ${buyInfo.buy_phone }<br>
	주소 : ${buyInfo.buy_addr }<br>
	상세주소 : ${buyInfo.buy_addr2 }<br>
	배송요청사항 : ${buyInfo.deli_memo }<br>
	

</form>
</div>

</body>
</html>