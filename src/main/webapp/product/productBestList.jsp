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
<body>
<h3>조회수 순	</h3>
<div class="컨테이너">
	<c:if test="${empty productReadList }">
	<h1>상품을 준비 중입니다.</h1>
	</c:if>
	<c:forEach var="productReadList" items="${productReadList}" varStatus="status">
	<c:choose>
		<c:when test="${productReadList.p_hide == '1'}"><!-- 품절아닐때 -->
		<c:if test="${status.count <= 4}">
			<div id="product">
				<a href="productDetailView.p?p_num=${productReadList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productReadList.p_image}" id="p_image">
				</a><br>
					조회수: ${productReadList.p_readcount}<br>
					상품명: ${productReadList.p_name}<br>
					금액: ${productReadList.p_price}<br>
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
				</c:if>
			</c:if>
		</c:when>
		<c:otherwise><!-- 품절일때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${productReadList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productReadList.p_image}" id="p_image">
				</a><br>
					조회수: ${productReadList.p_readcount}<br>
					품절입니다.
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>
</div>
<h3>판매량 순</h3>
<div class="컨테이너">
	<c:if test="${empty productSaleList }">
	<h1>상품을 준비 중입니다.</h1>
	</c:if>
	<c:forEach var="productSaleList" items="${productSaleList}" varStatus="status">
	<c:choose>
		<c:when test="${productSaleList.p_hide == '1'}"><!-- 품절아닐때 -->
		<c:if test="${status.count <= 4}">
			<div id="product">
				<a href="productDetailView.p?p_num=${productSaleList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productSaleList.p_image}" id="p_image">
				</a><br>
					조회수: ${productSaleList.p_readcount}<br>
					상품명: ${productSaleList.p_name}<br>
					금액: ${productSaleList.p_price}<br>
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
				</c:if>
			</c:if>
		</c:when>
		<c:otherwise><!-- 품절일때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${productSaleList.p_num}">
					<img src="${pageContext.request.contextPath}/images/${productSaleList.p_image}" id="p_image">
				</a><br>
					조회수: ${productSaleList.p_readcount}<br>
					품절입니다.
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>
</div>
</body>
</html>