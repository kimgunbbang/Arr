<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자모드(회원 목록 보기)</title>
<style>
.container{
	border:1px solid gray;
	margin: auto;
}
.table{
margin: auto;
text-align: center;
}
</style>
</head>
<body>
<div class="container">
	<div class="table">
		<div class="row">
			<h3>회원 목록</h3>
			<div class="열1">
			<c:forEach var="user" items="${userList }">
			<div class="열2" ><a href="userViewAction.u?id=${user.id }">${user.id }
			<c:if test="${user.user_bye }">
			(탈퇴회원)
			</c:if>
			</a></div>
			<div class="열2"><a href="userModifyForm.u?id=${user.id }">수정</a></div>
			<div class="열2"><a href="userDeleteAction.u?id=${user.id }" onclick="confirmDelete('${user.id}' ,event)">삭제</a></div>
			</c:forEach>
			</div>
		</div>
	</div>
</div>
<script>
function confirmDelete(userId, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 삭제하시겠습니까?")) {
	    location.href = "userDeleteAction.u?id=" + userId;
	  }
	}
</script>
</body>
</html>