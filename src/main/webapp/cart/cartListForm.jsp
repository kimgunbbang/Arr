<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container{
	text-align: center;
}
.col{
	display: inline-block;
}

.list{
	display: inline-block;
}
</style>
</head>
<body>
<div class="container">
	<div class="top">
		<div class="col"><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"></div>
		<div class="col">번호</div>
		<div class="col">상품 이미지</div>
		<div class="col">상품명</div>
		<div class="col">가격</div>
		<div class="col">수량</div>
	</div>
	<c:forEach var="cart" items="${cartList }" varStatus="status">
	<div class="list">
		<div class="list"><input type="checkbox" id="remove" name="remove"></div>
		<div class="list">${cart.cart_num }</div>
		<div class="list">${cart.p_num }</div>
		<div class="list">${cart.id }</div>
		<div class="list">${cart.qty }</div>
	</div>
	</c:forEach>
	<div class="money">총 금액 : ${totalMoney }원</div>
	<div class="remove" ><input type="submit" value="삭제" formaction="cartRemove.ct" > </div>
</div>
</body>
</html>