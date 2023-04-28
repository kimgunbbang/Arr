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
<!-- 세션아이디가 null이거나 또는 admin이 아닐때 -->
<c:if test="${sessionScope.id eq null || sessionScope.id ne 'admin' }">
	<script type="text/javascript">
	alert('관리자로 로그인하세요!');
	location.href='${pageContext.request.contextPath }/productAllList.p';
	</script>
</c:if>
</body>
</html>