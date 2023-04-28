<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
test123
	<div class="container">
		<!-- 사이트 상단 부분 -->
		<div class="row" >
			<div class="col">
				<!-- 메뉴바자리 -->
				<jsp:include page="menu_top.jsp"></jsp:include>
			</div>
		</div>
		
		<!-- 사이트 본문 부분 -->
		<div class="row">
			<div class="cols">
			<c:choose>
				<c:when test="${pagefile != null }">
					<jsp:include page="${pagefile}"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="main.jsp"></jsp:include>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		
		<!-- 사이트 하단 부분 -->
		<div class="row">
			<div class="cols"> 
			
			</div>
			
			<div class="cols"> 
			
			</div>
			
			<div class="cols"> 
			
			</div>
		</div>
		
	</div>
</body>
</html>