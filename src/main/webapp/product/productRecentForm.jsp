<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>최근 본 상품</title>
    <style>
        .product {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            margin: auto;
            width: 70%;
        }
        .product img {
            width: 13%;
            margin-right: 30px;
            margin-bottom: 5px;
        }
        .product-info {
            flex-grow: 1;
            display: flex;
            align-items: center;
        }
        .product-name {
            font-weight: bold;
            margin-right: 10px;
        }
        .product-price {
            margin-top: 5px;
            margin-right: 10px;
        }
        .buttons {
            display: flex;
            align-items: center;
            margin-left: 10px;
        }
        .buttons button {
            margin-right: 5px;
        }
        .col{
        text-align: center;
        }
        
        .buttons button {
        margin-right: 5px;
        padding: 5px 12px;
        background-color: #f33;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    
    .buttons button:hover {
        background-color: #27737c;
    }
    </style>
</head>
<body>
	<div style="margin: auto;">
	<h1>최근 본 상품</h1>
	</div>
    


    <% ArrayList<Product> recentViewProduct = (ArrayList<Product>) session.getAttribute("recentViewProduct"); %>

    <%-- 최근 본 상품이 있을 경우에만 표시 --%>
    <c:if test="${not empty recentViewProduct}">
        <%-- 최근 본 상품 목록을 순회하며 표시 --%>
        <c:forEach var="product" items="${recentViewProduct}">
            <div class="product">
                <input type="hidden" name="p_num" value="${product.p_num}">
                <img src="${pageContext.request.contextPath }/images/${product.p_image }" id="p_image" class="img-fluid rounded shadow">
                <div class="product-info">
                    <div class="product-name" >${product.p_name}</div>
                </div>
                <div>
                    <div class="product-price" ><fmt:formatNumber value="${product.p_price}" pattern="#,###" />원</div>
               </div> 
                <div class="buttons">
                    <button onclick="location.href = 'cartAddAction.ct?p_num=${product.p_num}'">장바구니</button>
                    <button onclick="location.href = 'buyActionForm.buy?id=${sessionScope.id }&p_num=${product.p_num}'">구매하기</button>
                </div>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${empty recentViewProduct}">
        <p>오늘 본 상품이 없습니다.</p>
    </c:if>
</body>
</html>