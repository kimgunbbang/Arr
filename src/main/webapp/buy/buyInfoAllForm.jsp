<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>구매 정보 상세</title>
</head>
<style>
.col{
	display: inline;
}
img{
	width:100%;
	height:100%;
}
</style>
<body>
<div class="container">
	<div class="row">
		<div class="col-3">사진</div>
		<div class="col-5">상품명</div>
		<div class="col-2">수량</div>
		<div class="col-2">금액</div>
	</div><hr>
	<c:forEach var="buy" items="${buyList }">
		<div class="row">
			<div class="col-3"><img src="${pageContext.request.contextPath}/images/${buy.p_image }"></div>
			<div class="col-5">${buy.p_name }</div>
			<div class="col-2">${buy.buy_qty }</div>
			<div class="col-2">${buy.buy_totalmoney }</div>
		</div>
	</c:forEach>
</div>
</body>
</html>