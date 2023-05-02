<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>구매 정보 입력</title>
<script type="text/javascript">
function changeMoney(event) {
	var qty = event.target.value; // 변경된 수량
	if(qty<1){
		qty=1;
		document.getElementById("buy_qty").value = qty;
	}
    var price = document.getElementById("p_price").value; // 상품금액
    var total = qty * price; // 총금액 계산
    document.getElementById("buy_totalmoney").value = total; // 총금액 표시
}

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

const buyId = ${buy.id};  // buy id 값을 가져옴
const deliSelectButton = document.getElementById("deliSelect");  // 배송지 선택 버튼 요소 가져오기

// 배송지 선택 버튼 클릭 이벤트 핸들러 함수
deliSelectButton.addEventListener("click", function() {
  // 아이디에 맞는 배송지 리스트를 보여주는 함수 호출
  showDeliveryList(buyId);
});

// 아이디에 맞는 배송지 리스트를 보여주는 함수
function showDeliveryList(buyId) {
  // 아이디에 맞는 배송지 리스트를 가져오는 로직 작성
  // ...

  // 가져온 배송지 리스트를 보여주는 로직 작성
  // ...

  // 배송지 리스트에서 배송지 선택시 수행할 로직 작성
  const deliverySelect = function() {
    // 선택한 배송지 정보를 가져오는 로직 작성
    // ...

    // 선택한 배송지 정보를 입력할 input 요소들을 생성하는 로직 작성
    const deliveryInfoDiv = document.createElement("div");
    deliveryInfoDiv.innerHTML = `
      수령인 : <input type="text" name="deli_username" id="deli_username" required><br>
      수령인 전화번호 : <input type="text" name="deli_phone" id="deli_phone" required><br>
      우편번호 : <input type="text" name="deli_zipcode" id="deli_zipcode" readonly>
      <input type="button" name="zipSearch" value="주소검색" id="zipSearch"><br>
      배송지 : <input type="text" name="deli_addr" id="deli_addr" readonly required><br>
      상세주소 : <input type="text" name="deli_addr2" id="deli_addr2"><br>
    `;

    // 생성한 input 요소들을 배송지 입력 폼에 추가
    const deliveryForm = document.getElementById("deliveryForm");
    deliveryForm.appendChild(deliveryInfoDiv);
  };

  // 배송지 리스트에서 각 배송지 요소에 클릭 이벤트 핸들러 등록
  const deliveryElements = document.querySelectorAll(".delivery-element");
  deliveryElements.forEach(function(element) {
    element.addEventListener("click", deliverySelect);
  });
}

</script>
</head>
<body>
<div class="container">
	<h1>구매 정보 입력</h1>
	<form action="buyAction.buy" method="post" name="buyForm" >
		<input type="hidden" name="id" id="${buy.id }" value="${buy.id }"><br>
		
		상품번호: ${buy.p_num }
		<input type="hidden" name="p_num" id="p_num" value="${buy.p_num }"><br>
		
		<label for="buy_qty">구매수량:</label>
		<input type="number" name="buy_qty" id="buy_qty" value ="${buy.buy_qty }" onchange="changeMoney(event)"><br>
		
		
		<input type="hidden" name="p_price" id="p_price" value ="${p_price }" ><br>
		
		<label for="buy_totalmoney">총금액:</label>
		<input type="text" name="buy_totalmoney" id="buy_totalmoney"
			readonly value="${buy.buy_totalmoney }"><br><br>
		<c:if test="${sessionScope ne null or buy.id ne null or buy.id ne '' }">
		
		<input type="button" name="deliSelect" id="deliSelect" value="배송지선택" >
		${buy.id }
			<a href="deliveryAdd.del?id=${buy.id }">배송지신규등록</a><br>
		</c:if>
		<form id="deliveryForm">
			수령인 : <input type="text" name = "deli_username" id = "deli_username" required><br>
			수령인 전화번호 :<input type="text" name = "deli_phone" id = "deli_phone" required><br>
			우편번호 : <input type="text" name = "deli_zipcode" id = "deli_zipcode" readonly>
			<input type="button" name="zipSearch" value="주소검색"  id="zipSearch" ><br>
			배송지 : <input type="text" name = "deli_addr" id = "deli_addr" readonly required><br>
			상세주소 : <input type="text" name = "deli_addr2" id = "deli_addr2" ><br>
		</form>
		
		<input type="submit" value="구매하기">
		<!-- 취소하기 눌렀을때 뒤로가기 -->
		<button onclick='history.back()'>취소하기</button>	
		</form>
		
		
	
</div>
</body>
</html>
