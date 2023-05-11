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
<style>
	#product {
		width: 24%;
		float: left;
		margin: 0.5%;
	}
	#p_image {
		width: 200px;
		height: 200px;
	}
</style>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="container">
<h3><a href="productAddForm.p">상품등록</a></h3>
	<c:if test="${empty productList }">
	<h1>상품 없음</h1>
	</c:if>
	<c:forEach var="productList" items="${productList}" varStatus="status">
			<div id="product">
				<a href="productDetailView.p?p_num=${productList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productList.p_image}" id="p_image">
				</a><br>
					조회수: ${productList.p_readcount}<br>
					상품명: ${productList.p_name}<br>
					금액: ${productList.p_price}<br>
					<button onclick="javascript:location.href='productModifyForm.p?p_num=${productList.p_num}'">상품수정</button>

			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		
		
	
</c:forEach>
</div>
</body>
</html>