<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 목록 보기</title>
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
<script>

</script>
</head>
<body>
<div class="container">
	<div class="table">
		<div class="row">
			<h3>배송지 목록</h3>
			<div class="열1"><a href="deliveryAdd.del?id=${user.id }">배송지신규등록</a></div>
			<c:forEach var="delivery" items="${deliveryList }">
			<div class="열2"><a href="deliveryViewAction.del?id=${user.id }">${delivery.deli_name }</a></div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>