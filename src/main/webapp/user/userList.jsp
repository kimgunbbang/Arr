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
			<div class="열2"><a href="userViewAction.u?id=${user.id }">${user.id }</a></div>
			<div class="열2"><a href="userModifyForm.u?id=${user.id }">수정</a></div>
			<div class="열2"><a href="userDeleteAction.u?id=${user.id }">삭제</a></div>
			</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>