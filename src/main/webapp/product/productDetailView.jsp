<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container my-5">
        <div class="row">
            <div class="col-md-6">
                <!-- 제품사진 -->
                <img src="${pageContext.request.contextPath }/images/${product.p_image }" id="p_image" class="img-fluid rounded shadow">
            </div>
            <div class="col-md-6 my-4">
                <!-- 상품명,가격,구매수량,구매버튼,장바구니버튼 -->
                <h2>${product.p_name }</h2>
                <h4 class="text-muted">${product.p_price }</h4>
                <form action="buyAction.p?id=${id }&p_num=${product.p_num}" method="post">
                    <div class="form-group my-3">
                        <label for="buy_qty">구매수량 :</label>
                        <input type="text" name="buy_qty" class="form-control">
                    </div>
                    <div class="form-group my-3">
                        <label for="p_detail">상세설명 :</label>
                        <p>${product.p_detail }</p>
                    </div>
                    <c:if test="${product.p_hide=='0' }">
                    <button type="submit" class="btn btn-primary my-3">구매하기</button>
<<<<<<< HEAD
                    <button type="button" class="btn btn-secondary my-3" onclick="location.href='cartAddAction.ct'">장바구니</button>
                    
                    <c:if test="${sessionScope.id eq 'admin'}">
                   	 	<a href="productModifyForm.p?p_num=${product.p_num }" class="btn btn-link my-3">수정</a>
                  	</c:if>
=======
                    <button type="button" class="btn btn-secondary my-3" href="cartAddAction.ct">장바구니</button>
                    </c:if>
>>>>>>> branch 'master' of https://github.com/kimgunbbang/Arr.git
                    
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- 상품이미지2(설명용)넣기 -->
                <img src="${pageContext.request.contextPath }/images/${product.p_image2 }" id="p_image2" class="img-fluid rounded shadow my-5">
            </div>
        </div>
    </div>
    
</body>
</html>
