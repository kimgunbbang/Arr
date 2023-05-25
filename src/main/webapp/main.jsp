<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		width: 80%;
	}


.img{
	height:100%;
	width:100%;
}
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

</style>
<body>
<div class="container-fluid">
    <div class="row">
    <!-- 베스트상품 슬라이드형식으로 나오게끔,, -->
    	<div class="col">
			<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item">
				    <a href="productDetailView.p?p_num=1">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner1.jpg" class="d-block w-100" alt="banner1">
				    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="#">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner2.jpg" class="d-block w-100" alt="banner2">
				    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="productDetailView.p?p_num=2">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner3.jpg" class="d-block w-100" alt="banner3">
				    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="productDetailView.p?p_num=3">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner4.jpg" class="d-block w-100" alt="banner4">
				    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="#">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner5.jpg" class="d-block w-100" alt="banner5">
				    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="productDetailView.p?p_num=4">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner6.jpg" class="d-block w-100" alt="banner6">
				    </a>
			    </div>
			    <div class="carousel-item active">
				    <a href="productDetailView.p?p_num=16">
				      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner7.jpg" class="d-block w-100" alt="banner6">
				    </a>
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
    	</div>
  	</div>
    <br>
</div>
  	<div class="container">
  	  <!-- 메인상품 나오게끔,, -->
  	  <div class="row">
  	  <h3 align="center">NEW</h3><hr>
  	  <c:forEach var="product" items="${productList }" begin="${fn:length(productList)-4}" end="${fn:length(productList)}" varStatus="status">
  	  	<c:if test="${product.p_hide=='0' }">
		<c:choose>
		<c:when test="${product.p_qty > 0}"><!-- 품절아닐때 -->
			<div id="product" class="position-relative">
				<a href="productDetailView.p?p_num=${product.p_num}">
					<img src="${pageContext.request.contextPath}/images/${product.p_image}" id="p_image">
					<span class="badge bg-danger position-absolute top-0 start-0">NEW</span>
				</a><br>
					 <h4>${product.p_name}</h4>
					<h5 style="text-align: right; margin-right: 70px"><fmt:formatNumber value="${product.p_price}" pattern="#,###" /></h5>
					<br>
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:when>
		<c:otherwise><!-- 품절일때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${product.p_num}">
					<img src="${pageContext.request.contextPath}/images/${product.p_image}" id="p_image">
				</a><br>
					
					<h4>${product.p_name}</h4><br>
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
  	  
  	  <div class="row">
  	  <h3 align="center">ALL</h3><hr>
  	  	<c:forEach var="product" items="${productList }" begin="1" end="4" varStatus="status">
  	  	<c:if test="${product.p_hide=='0' }">
		<c:choose>
		<c:when test="${product.p_qty > 0}"><!-- 품절아닐때 -->
			<div id="product" class="position-relative">
				<a href="productDetailView.p?p_num=${product.p_num}">
					<img src="${pageContext.request.contextPath}/images/${product.p_image}" id="p_image">
				</a><br>
					 <h4>${product.p_name}</h4>
					<h5 style="text-align: right; margin-right: 70px"><fmt:formatNumber value="${product.p_price}" pattern="#,###" /></h5>
					<br>
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:when>
		<c:otherwise><!-- 품절일때 -->
			<div id="product">
				<a href="productDetailView.p?p_num=${product.p_num}">
					<img src="${pageContext.request.contextPath}/images/${product.p_image}" id="p_image">
				</a><br>
					
					<h4>${product.p_name}</h4><br>
					품절입니다.
			</div>
			<c:if test="${status.count % 4 == 0}">
				<div style="clear: both;"></div>
			</c:if>
		</c:otherwise>
		</c:choose>
		</c:if>
  	  </c:forEach>
  	  <div style="text-align: center"><a href="productAllList.p">더보기</a></div>
  	  </div>
  	</div>
</body>
</html>
