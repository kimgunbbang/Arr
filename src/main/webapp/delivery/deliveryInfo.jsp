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
			<div class="열1">배송지명 : </div><div class="열1">${delivery.deli_name }</div>
			<div class="열1">우편번호 : </div><div class="열1">${delivery.deli_zipcode }</div>
			<div class="열1">배송주소 : </div><div class="열1">${delivery.deli_addr }</div>
			<div class="열1">배송상세주소 : </div><div class="열1">${delivery.deli_addr2 }</div>
			<div class="열1">수령인 : </div><div class="열1">${delivery.deli_username }</div>
			<div class="열1">수령인전화번호 : </div><div class="열1">${delivery.deli_phone }</div>
			<div class="열1"><a href="deliveryModifyForm.del?id=${user.id }">수정</a></div>
			<div class="열1"><a href="deliveryDeleteAction.del?id=${user.id}" onclick="confirmDelete('${user.id}')">삭제</a></div>

<script>
function confirmDelete(userId) {
  if (confirm("정말 삭제하시겠습니까?")) {
    location.href = "deliveryDeleteAction.del?id=" + userId;
  } else {
    window.close();
  }
}
</script>
			
		</div>
	</div>
</div>
</body>
</html>