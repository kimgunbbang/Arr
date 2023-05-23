<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자모드(회원 목록 보기)</title>

</head>
<body>
<div class="container">
	<div class="table" style="margin:auto; text-align: center; width:80%">
			<h3 class="title" style="text-align: center">회원 목록</h3><hr>
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="col-3">아이디</th>
						<th class="col-6">상태</th>
						<th class="col-1">수정</th>
						<th class="col-2">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList }">
						<tr>
							<td>
								<a href="userViewAction.u?id=${user.id }">${user.id}</a>
								<c:if test="${user.user_bye }">
									<span class="badge badge-danger">(탈퇴회원)</span>
								</c:if>
							</td>
							<td></td>
							<td><a href="userModifyForm.u?id=${user.id }" class="btn btn-primary">수정</a></td>
							<td><a href="userDeleteAction.u?id=${user.id }" onclick="confirmDelete('${user.id}', event)" class="btn btn-danger">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${empty userList}">
				<div class="alert alert-warning alert-message" role="alert">
					회원이 없습니다.
				</div>
			</c:if>
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