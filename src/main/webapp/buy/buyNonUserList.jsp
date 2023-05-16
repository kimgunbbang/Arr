<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function cancel(buy_num, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 취소하시겠습니까?")) {
	    location.href = "buyCancel.buy?buy_num=" + buy_num;
	  }
	}
</script>
<style>

 .row {
   display: flex;
   align-items: center;
 }

.col{
	display: inline;
}
.img{
	width:80%;
	height:80%;
}
</style>
<body>
<div class="container">
<h3>구매날짜 : ${buyInfo.buy_date } / 상태 : 
<c:choose>
	<c:when test="${buyList[0].buy_state eq 'ready' }">
		배송준비중 &nbsp; <a href="buyCancel.buy?buy_num=${buyInfo.buy_num}" onclick="cancel('${buyInfo.buy_num}' ,event)">주문취소</a>
		
	</c:when>
	<c:when test="${buyList[0].buy_state eq 'cancel' }">
		주문취소
	</c:when>
	<c:when test="${buyList[0].buy_state eq 'completion' }">
		배송완료
		<button>구매확정</button>
	</c:when>
	<c:when test="${buyList[0].buy_state eq 'finish' }">
		구매확정
	</c:when>
	<c:when test="${buyList[0].buy_state eq 'deliver' }">
		배송중
	</c:when>
</c:choose>
</h3><hr>
	<div class="row">
		<div class="col-3">사진</div>
		<div class="col-5">상품명</div>
		<div class="col-2">수량</div>
		<div class="col-2">금액</div>
	</div><hr>
	<c:set var="lastMoney" value="0"/>
	<c:forEach var="buy" items="${buyList }">
		<div class="row">
			<div class="col-3"><img class="img" src="${pageContext.request.contextPath}/images/${buy.p_image }"></div>
			<div class="col-5">${buy.p_name }</div>
			<div class="col-2">${buy.buy_qty }</div>
			<div class="col-2">${buy.buy_totalmoney }</div>
		</div>
	<c:set var="lastMoney" value="${lastMoney+ buy.buy_totalmoney}"/>
	</c:forEach><hr>
	<div class="row">
		전체금액  ${lastMoney}
	</div><hr>
	<div class="row">
		결제방식 안넣을거임
	</div><hr>
	<div class="row">
		구매자 : ${buyInfo.buy_name } <br>
		전화번호 : ${buyInfo.buy_phone } <br>
		주소 : ${buyInfo.buy_addr } <br>
		상세주소 : ${buyInfo.buy_addr2 } <br>
		배송메모 : ${buyInfo.deli_memo } <br>
	</div>
</div>
</body>
</html>