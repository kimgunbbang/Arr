<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container{
	text-align: center;
}
.table{
	text-align: center;
}
</style>
</head>
<body>
<div class="containor">
	<div class="table">
		<div class="row">
			<input type="hidden" name="deli_num" id="deli_num" value="${delivery.deli_num }">
			<div class="열1">배송지명 : </div><div class="열1">${delivery.deli_name }</div>
			<div class="열1">우편번호 : </div><div class="열1">${delivery.deli_zipcode }</div>
			<div class="열1">배송주소 : </div><div class="열1">${delivery.deli_addr }</div>
			<div class="열1">배송상세주소 : </div><div class="열1">${delivery.deli_addr2 }</div>
			<div class="열1">수령인 : </div><div class="열1">${delivery.deli_username }</div>
			<div class="열1">수령인전화번호 : </div><div class="열1">${delivery.deli_phone }</div>
			<div class="열1"><a href="deliveryModifyForm.del?deli_num=${delivery.deli_num}">수정</a></div>
			<div class="열1"><a href="deliveryDeleteAction.del?deli_num=${delivery.deli_num}" onclick="confirmDelete('${delivery.deli_num}', event)">삭제</a></div>

<script>
function confirmDelete(userId, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 삭제하시겠습니까?")) {
	    location.href = "deliveryDeleteAction.del?deli_num=" + userId;
	  }
	}
</script>
			
		</div>
	</div>
</div>
</body>
</html>