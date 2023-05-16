<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>구매가 완료 되었습니다.</h1><br>
<c:choose>
	<c:when test="${sessionScope.id eq null || sessionScope.id eq '' }">
	구매번호는 ${buy_num }입니다.(비회원 주문조회시 꼭필요)<br>
	<a href="nonUserBuyListForm.buy">비회원주문조회</a>
	</c:when>
	<c:otherwise>
	<a href="buyListForm.buy?id=${sessionScope.id }">구매목록</a>
	<a href="main.p">홈으로 가기</a>
	</c:otherwise>
</c:choose>
</body>
</html>