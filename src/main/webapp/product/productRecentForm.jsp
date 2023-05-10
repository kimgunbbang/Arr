<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        }
        .product img {
            width: 100px;
            height: 100px;
            margin-right: 10px;
        }
        .product-info {
            flex-grow: 1;
        }
        .product-name {
            font-weight: bold;
        }
        .product-price {
            margin-top: 5px;
        }
        .buttons {
            margin-left: 10px;
        }
        .buttons button {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <h1>최근 본 상품</h1>

    <%-- 세션에서 최근 본 상품 정보를 가져옴 --%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="vo.Product" %>
    <% ArrayList<Product> recentViewProduct = (ArrayList)session.getAttribute("recentViewProduct"); %>

    <%-- 최근 본 상품이 있을 경우에만 표시 --%>
    <% if (recentViewProduct != null && !recentViewProduct.isEmpty()) { %>
        <%-- 최근 본 상품 목록 중 최대 10개만 표시 --%>
        <% int maxItems = Math.min(recentViewProduct.size(), 10); %>

        <%-- 최근 본 상품 목록을 순회하며 표시 --%>
        <% for (int i = 0; i < maxItems; i++) { %>
            <% Product product = recentViewProduct.get(i); %>
            <div class="product">
                <img src="<%= product.getP_image() %>" alt="<%= product.getP_name() %>">
                <div class="product-info">
                    <div class="product-name"><%= product.getP_name() %></div>
                    <div class="product-price"><%= product.getP_price() %> 원</div>
                </div>
                <div class="buttons">
                    <button onclick="location.href = 'buyActionForm.buy?id=${sessionScope.id }&p_num=${product.p_num}'">'">구매하기</button>
                    <button onclick="location.href = 'cartAddAction.ct?p_num=${product.p_num}'">장바구니</button>
                </div>
            </div>
        <% } %>
    <% } else { %>
        <p>최근 본 상품이 없습니다.</p>
    <% } %>
</body>
</html>