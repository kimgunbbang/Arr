<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="row">
    <!-- 베스트상품 슬라이드형식으로 나오게끔,, -->
    	<div class="col">
<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner1.jpg" class="d-block w-100" alt="banner1">
    </div>
    <div class="carousel-item">
      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner2.jpg" class="d-block w-100" alt="banner2">
    </div>
    <div class="carousel-item">
      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner3.jpg" class="d-block w-100" alt="banner3">
    </div>
    <div class="carousel-item">
      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner4.jpg" class="d-block w-100" alt="banner4">
    </div>
    <div class="carousel-item">
      <img class="img" src="${pageContext.request.contextPath}/images/slidebanner5.jpg" class="d-block w-100" alt="banner5">
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
    
  	<div class="row">
  	  <!-- 메인상품 나오게끔,, -->
  	  메인상품 영역입니다.
	</div>
</div>
</body>
</html>
