<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<title>구매목록</title>
</head>
<body>
<div class="container">
구매목록<br>
<c:choose>
	<c:when test="${empty buyList }">
	구매내역이 없습니다.
	</c:when>
	<c:otherwise>
		<c:forEach>
			<c:forEach var="buy" items="${buyList }">
				<br>
			</c:forEach>
		</c:forEach>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>