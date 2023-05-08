<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
.container1 {
  text-align: center;
  margin: 50px auto;
  width: 80%;
  font-size: 16px;
  color: #333;
}

.top {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  font-weight: bold;
}

.col {
  flex: 1;
  text-align: center;
}

.list {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.list .col {
  flex: 1;
}

.list .col:nth-child(2) {
  font-weight: bold;
}

.money {
  margin-top: 20px;
  font-size: 18px;
  font-weight: bold;
}

.remove {
  margin-top: 20px;
  text-align: center;
}

.remove input[type="submit"] {
  padding: 10px;
  border: none;
  background-color: #f00;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
}

.remove input[type="submit"]:hover {
  background-color: #f33;
}

.buy {
  margin-top: 20px;
  text-align: center;
}

.buy input[type="submit"] {
  padding: 10px;
  border: none;
  background-color: #f00;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
}

.buy input[type="submit"]:hover {
  background-color: #f33;
}
</style>
</head>
<body>
<input type="hidden" name="id" value="${sessionScope.id }">
<div class="container1">
   <div class="top">
       <div class="col" style="display: none;"></div>
       <div class="col" style="display: none;"></div>
      <div class="col"><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"></div>
      <div class="col">번호</div>
      <div class="col">상품 이미지</div>
      <div class="col">상품명</div>
      <div class="col">가격</div>
      <div class="col">수량</div>
   </div>
   <% int num = 0; %>
   <c:forEach var="cart" items="${cartList }" varStatus="status">
   
   <div class="list">
      <div class="col" style="display: none;"><input type="hidden" name="cart_num"> ${cart.cart_num }</div>
      <div class="col" style="display: none;"><input type="hidden" name="p_num"> ${cart.p_num }</div>
      <div class="col"><input type="checkbox" id="remove" name="remove"></div>
      <div class="col"><%=++num %></div>
      <div class="col">${cart.p_image }</div>
      <div class="col">${cart.p_name }</div>
      <div class="col"><input type="hidden" name="p_price"> ${cart.p_price }</div>
      <div class="col"><input type="hidden" name="buy_qty"> ${cart.cart_qty }</div>
   </div>
   </c:forEach>
   <div class="money">총 금액 : ${totalMoney }원</div>
   
   <div class="buy" ><input type="submit" value="구매" formaction="buyActionForm.buy"> </div>
   <div class="remove" ><input type="submit" value="삭제" formaction="cartRemove.ct" > </div>
   
</div>
</body>
</html>