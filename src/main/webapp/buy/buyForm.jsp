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

function setDeliveryInfo(selectBox) {
	  // 선택한 배송지의 데이터를 가져옴
	  var selectedOption = selectBox.options[selectBox.selectedIndex];
	  var name = selectedOption.getAttribute('data-name');
	  var phone = selectedOption.getAttribute('data-phone');
	  var zipcode = selectedOption.getAttribute('data-zipcode');
	  var addr = selectedOption.getAttribute('data-addr');
	  var addr2 = selectedOption.getAttribute('data-addr2');

	  // 가져온 데이터를 폼에 채움
	  document.getElementById('deli_username').value = name;
	  document.getElementById('deli_phone').value = phone;
	  document.getElementById('deli_zipcode').value = zipcode;
	  document.getElementById('deli_addr').value = addr;
	  document.getElementById('deli_addr2').value = addr2;
}

</script>
<%int num=0; %>
</head>
<body>
<div class="container">
	<h1>구매 정보 입력</h1>
	<form action="buyAction.buy" method="post" name="buyForm" >
		<c:if test="${not empty cartList}">
		<c:forEach var="cart" items="${cartList}" varStatus="idx">
			<input type="hidden" name="cart_num" value="${cart}">
		</c:forEach>
	</c:if>
	
		<input type="hidden" name="id" id="${buy.id }" value="${buy.id }"><br>
		
		<c:forEach var="buy" items="${buyList }" varStatus="idx">
		<%=++num %>.&nbsp;
		상품번호: ${buy.p_image }
		<input type="hidden" name="p_num" id="p_num" value="${buy.p_num }"> &nbsp;
		구매수량: ${buy.buy_qty }
		<input type="hidden" name="buy_qty" id="buy_qty" value ="${buy.buy_qty }" onchange='qtychage(event)'>&nbsp;
		<input type="hidden" name="p_price" id="p_price" value ="${p_price }" >
		
		상품금액:${buy.buy_totalmoney }
		<input type="hidden" name="buy_totalmoney" id="buy_totalmoney"
			value="${buy.buy_totalmoney }"><br>
		</c:forEach>
		총금액 : ${lastTotalMoney }<br>
		
		
<c:if test="${sessionScope ne null and not empty deliveryList}">
		배송지선택 : 
			<select name="deli_num" onchange="setDeliveryInfo(this)">
			<c:set var="first" value="true"/>
				<c:forEach var="delivery" items="${deliveryList }">
					<option value="${delivery.deli_num}" data-name="${delivery.deli_name}" 
														data-phone="${delivery.deli_phone}" 
														data-zipcode="${delivery.deli_zipcode}" 
														data-addr="${delivery.deli_addr}" 
														data-addr2="${delivery.deli_addr2}"
														${first? 'selected':'' }>
      					${delivery.deli_name}
      					<c:set var="first" value="false"/>
				</c:forEach>
				<option value="newDelivery">신규배송지
			</select>
		</c:if>

		<br>
			
			수령인 : <input type="text" name = "deli_username" id = "deli_username" value = "${not empty deliveryList?deliveryList[0].deli_name:''} " required><br>
			수령인 전화번호 :<input type="text" name = "deli_phone" id = "deli_phone" value = "${not empty deliveryList?deliveryList[0].deli_phone:''} " required><br>
			우편번호 : <input type="text" name = "deli_zipcode" id = "deli_zipcode" value = "${not empty deliveryList?deliveryList[0].deli_zipcode:''}" readonly>
			<input type="button" name="zipSearch" value="주소검색"  id="zipSearch" ><br>
			배송지 : <input type="text" name = "deli_addr" id = "deli_addr" value = "${not empty deliveryList?deliveryList[0].deli_addr:''}" readonly required><br>
			상세주소 : <input type="text" name = "deli_addr2" id = "deli_addr2" value = "${not empty deliveryList?deliveryList[0].deli_addr2:''}"><br>
			배송시요청사항<br><textarea rows="20" cols="20" name="deli_memo"></textarea>
		
		<input type="submit" value="구매하기">
		<!-- 취소하기 눌렀을때 뒤로가기 -->
		<button onclick='history.back()'>취소하기</button>	
		</form>
		
		
	
</div>
</body>
</html>
