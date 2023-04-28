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
<div class="container">
<form action="inventoryInOutAction.in" method="post">
    <div class="row">
        <div class="col">상품선택 :</div>
        <div class="col">
            <select name="p_num">
                <c:forEach var="productList" items="${productList}">
                    <option value="${productList.p_num}" ${param.p_num == productList.p_num ? 'selected' : ''}>
                        ${productList.p_num}:${productList.p_name}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col">입고량</div>
        <div class="col"><input type="text" name="inven_in" id="inven_in"></div>
    </div>
    <div class="row">
        <div class="col">출고량</div>
        <div class="col"><input type="text" name="inven_out" id="inven_out"></div>
    </div>
    <div class="row">
        <input type="submit" value="재고등록">
        <input type="reset" value="다시작성">
        <input type="button" value="돌아가기" onclick="history.back()">
    </div>
</form>
	
</div>
</body>
</html>