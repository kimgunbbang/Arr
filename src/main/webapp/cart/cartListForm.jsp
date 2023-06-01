<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
.container_cart {
  text-align: center;
  margin: 50px auto;
  width: 80%;
  font-size: 16px;
  color: #333;
  border: 1px solid lavender;
  padding-bottom: 15px;
  border-radius: 20px;
}

.top_cart {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  font-weight: bold;
  background-color: #27737c;
  color: white;
  padding: 5px;
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
#upImage{
	width:15px;
}
#downImage{
	width:15px;
}
.cart_image{
	width: 50%;
}


</style>
<script>
function checkAll(theForm) {
	if(theForm.remove.length==undefined){
		theForm.remove.checked = theForm.allCheck.checked;
	}else{
		for(var i=0;i<theForm.remove.length;i++){
			theForm.remove[i].checked = theForm.allCheck.checked;
		}
	}
}

function checkQty(cart_num,cart_qty) {//수량1밑으로 안가게 함
	if(cart_qty != 1){
		location.href="cartQtyDown.ct?cart_num="+cart_num;
	}
}


function hap(theForm) {
	  var hap = 0;
	  for (var i = 0; i < theForm.remove.length; i++) {
	    if (theForm.remove[i].checked) {
	      hap += theForm.buy_qty[i].value * theForm.p_price[i].value;
	    }
	  }
	  document.getElementById("hap").textContent = hap.toLocaleString();
}


</script>
</head>
<body>
<form action="" id="buyForm">
<input type="hidden" name="id" value="${sessionScope.id }">
<div class="container_cart">
   <div class="top_cart">
       <div class="col" style="display: none;"></div>
       <div class="col" style="display: none;"></div>
      <div class="col"><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"></div>
      <div class="col">번호</div>
      <div class="col">상품 이미지</div>
      <div class="col">상품명</div>
      <div class="col">가격</div>
      <div class="col">수량</div>
   </div>
   <% int num = 0;%>
   <c:set var="hap" value="0"/>
   <c:forEach var="cart" items="${cartList }" varStatus="status">
   <input type="hidden" name="p_image" value="${cart.p_image }">
   <div class="list">
      <div class="col" style="display: none;"><input type="hidden" name="cart_num" value="${cart.cart_num }"> ${cart.cart_num }</div>
      <div class="col" style="display: none;"><input type="hidden" name="p_num" value="${cart.p_num }"> ${cart.p_num }</div>
      <div class="col"><input type="checkbox"id="remove" name="remove" value="${cart.cart_num }" onclick="hap(this.form)"></div>
      <div class="col"><%=++num %></div>
      <div class="col"><img src="${pageContext.request.contextPath }/images/${cart.p_image }" id="cart_image" class="img-fluid rounded shadow" width="50%"></div>
      <div class="col">${cart.p_name }</div>
      <div class="col"><input type="hidden" name="p_price" id="p_price" value="${cart.p_price }"><fmt:formatNumber value="${cart.p_price }" pattern="#,###" />원 </div>
      <div class="col"><input type="hidden" name="buy_qty" id="buy_qty" value="${cart.cart_qty }">
       					<a href="cartQtyUp.ct?cart_num=${cart.cart_num }">
					<img src="${pageContext.request.contextPath }/images/up.jpg"
					id="upImage" border=0></a><br>
					${cart.cart_qty }<br>
					<a href="javascript:checkQty('${cart.cart_num }',${cart.cart_qty })">
					<img src="${pageContext.request.contextPath }/images/down.jpg"
					id="downImage" border=0></a><br>
       </div>
   </div>
   </c:forEach>
   <div class="money">총 금액 : <span id="hap">0</span>원</div>
   <div class="button" style="margin: auto;">
   <input type="submit" class="buy" value="구매" onclick="formCheck();">
   <input type="submit" class="remove" value="삭제" formaction="cartRemove.ct" >
   </div>
   <c:if test="${cartList eq null}">
		<section class="div_empty">
		장바구니가 비었습니다.
		</section>
	</c:if>
</div>
</form>
<script>
function formCheck() {
    var removeCheckboxes = document.getElementsByName('remove');
    var isChecked = false;

    for (var i = 0; i < removeCheckboxes.length; i++) {
      if (removeCheckboxes[i].checked) {
        isChecked = true;
        break;
      }
    }

    if (!isChecked) {
      event.preventDefault(); // 폼 액션 실행 방지
      alert('상품을 선택해주세요.!!');
    } else {
      document.getElementById('buyForm').action = "buyActionForm.buy";
    }
  }
</script>
</body>
</html>