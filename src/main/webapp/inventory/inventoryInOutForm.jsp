<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.containerJoin {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
 
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input[type="text"],
.form-group input[type="password"] {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.form-group .button-container {
  margin-top: 20px;
  text-align: center;
}

.form-group .button-container button {
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.form-group .button-container button:not(:last-child) {
  margin-right: 10px;
}

.form-group .button-container button:hover {
  background-color: #0056b3;
}

.form-group .button-container button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
/* 중복확인 버튼 스타일 */
.duplicate-button {
padding: 5px 10px;
font-size: 16px;
border-radius: 5px;
background-color: #27737c;
color: #fff;
border: none;
cursor: pointer;
margin-top: 10px;
}

.duplicate-button:hover {
background-color: #1b4e54;
}

/* 주소검색 버튼 스타일 */
.zip-search-button {
padding: 5px 10px;
font-size: 16px;
border-radius: 5px;
background-color: #27737c;
color: #fff;
border: none;
cursor: pointer;
margin-top: 10px;
}

.zip-search-button:hover {
background-color: #1b4e54;
}

/* 회원가입 버튼 스타일 */
.signup-button {
padding: 10px 20px;
font-size: 16px;
border-radius: 5px;
background-color:#27737c;
color: #fff;
border: none;
cursor: pointer;
width: 100%;
margin-top: 10px;
}

.signup-button:hover {
background-color: #1b4e54;
}
</style>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="containerJoin">
<form action="inventoryInOutAction.in" method="post">
	<h2>상품 입출고 등록</h2><hr>
	<div class="form-group">
    상품선택
            <select name="p_num">
                <c:forEach var="productList" items="${productList}">
                	<c:if test="${productList.p_hide eq '0' }">
                    <option value="${productList.p_num}" ${param.p_num == productList.p_num ? 'selected' : ''}>
                        ${productList.p_num}:${productList.p_name}
                    </option>
                    </c:if>
                </c:forEach>
            </select>
	</div>
    <div class="form-group">
        입고량<input type="text" name="inven_in" id="inven_in">
    </div>
    <div class="form-group">
        출고량<input type="text" name="inven_out" id="inven_out">
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