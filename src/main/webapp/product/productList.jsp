<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
/* cdn import */
@font-face {
  font-family: 'Godo';
  font-style: normal;
  font-weight: 400;
  src: url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2') format('woff2'), url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff') format('woff');
}

@import url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2') format('woff2'), url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff') format('woff');
body {
    font-family: 'Godo';
}
	#product {
		width: 24%;
		float: left;
		margin: 0.5%;
	}
	#p_image {
		width: 80%;
	}
</style>
<script>

</script>
<body>
<div class="container">
<c:choose>
<c:when test="${category_name eq 'food' }">
<h3 align="center">FOOD</h3><hr>
</c:when>
<c:when test="${category_name eq 'care' }">
<h3 align="center">CARE</h3><hr>
</c:when>
<c:when test="${category_name eq 'play' }">
<h3 align="center">PLAY</h3><hr>
</c:when>
<c:when test="${category_name eq 'cloth' }">
<h3 align="center">CLOTH</h3><hr>
</c:when>
<c:when test="${category_name eq 'potty' }">
<h3 align="center">POTTY</h3><hr>
</c:when>
<c:when test="${category_name eq 'health' }">
<h3 align="center">HEALTH</h3><hr>
</c:when>
<c:otherwise>
<h3 align="center">ALL</h3><hr>
</c:otherwise>
</c:choose>
<div style="text-align: right">
<a href="productSort.p?sort=price&category_name=${category_name }">가격순</a>
<a href="productSort.p?sort=best&category_name=${category_name }">인기순</a>
<a href="productSort.p?sort=readcount&category_name=${category_name }">조회순</a>
</div>

	<c:if test="${empty productList }">
	<h1>상품을 준비 중입니다.</h1>
	</c:if>
	<c:forEach var="productList" items="${productList}" varStatus="status">
	<c:if test="${productList.p_hide=='0' }"><!-- 삭제한상품 아닐때 -->
	
	
	
	<c:choose>
		<c:when test="${productList.p_qty > 0}"><!-- 품절아닐때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${productList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productList.p_image}" id="p_image">
				</a><br>
					 <h4>${productList.p_name}</h4>
					<h5 style="text-align: right; margin-right: 70px"><fmt:formatNumber value="${productList.p_price}" pattern="#,###" /></h5>
					<br>
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:when>
		<c:otherwise><!-- 품절일때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${productList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productList.p_image}" id="p_image">
				</a><br>
					
					<h4>${productList.p_name}</h4><br>
					품절입니다.
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:otherwise>
	</c:choose>
	</c:if>
</c:forEach>
</div>
</body>
</html>