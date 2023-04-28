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
			<div class="열1">아이디 : </div><div class="열1">${user.id }</div>
			<div class="열1">비밀번호 : </div><div class="열1">${user.user_pass }</div>
			<div class="열1">이름 : </div><div class="열1">${user.user_name }</div>
			<div class="열1">우편번호 : </div><div class="열1">${user.user_zipcode }</div>
			<div class="열1">주소 : </div><div class="열1">${user.user_addr }</div>
			<div class="열1">상세주소 : </div><div class="열1">${user.user_addr2 }</div>
			<div class="열1">전화번호 : </div><div class="열1">${user.user_phone }</div>
			<div class="열1"><a href="userModifyForm.u?id=${user.id }">수정</a></div>
			<div class="열1"><a href="deliveryListAction.del?id=${user.id }">배송지관리</a></div>
			<div class="열1"><a href="userByeAction.u?id=${user.id}">탈퇴</a></div>

<!-- <script>
function confirmDelete(userId) {
  if (confirm("정말 탈퇴하시겠습니까?")) {
    location.href = "userByeAction.u?id=" + userId;
  } else {
    window.close();
  }
}
</script> -->
			
		</div>
	</div>
</div>
</body>
</html>