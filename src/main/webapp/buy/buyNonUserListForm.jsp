<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<form action="nonUserBuyListAction.buy" method="post">
<h3>비회원 주문 조회하기</h3>
구매번호 <input type="text" name="buy_num" required><br>
구매자연락처 <input type="text" name="buy_phone" placeholder="(-)없이 입력하세요" required><br>
<button onclick="this.form.submit()">조회하기</button>
</form>
</div>
</body>
</html>