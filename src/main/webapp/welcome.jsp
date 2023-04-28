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
<%if(session.getAttribute("id")==null){ %>
<script>
alert('로그인하세요!');
location.href='userLogin.u';
</script>
<%}else{ %>
<h1>아르르르르 으를르러러러렁 컹컹</h1>
<%} %>
</body>
</html>