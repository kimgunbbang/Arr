<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/cb777d7294.js" crossorigin="anonymous"></script>
<html>
<head>

<style>
.dropdown:hover .dropdown-menu {
  display: block;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<div class="container" style="height:200px; background-color: #FFF" >
  <div class="row" style="height:180px; display: flex; align-items: center; justify-content: center;">
    <div class="col-lg-4">
    <a href="main.p">
      <img src="${pageContext.request.contextPath}/images/logo4.png" alt="로고" width="150" height="150"/>
    </a>
    </div>
    <div class="col-lg-4">
      <ul class="nav nav-pills">
        <li class="nav-item dropdown">
          <a class="nav-link" style="color: black;" href="productAllList.p" id="navbarDropdown" role="button"  aria-expanded="false">
            <b>SHOP</b>
          </a>
          <!-- 드롭다운 메뉴 -->
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="productSelectList.p?category_name=food">사료/간식</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=cloth">의류/악세사리</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=play">산책/놀이</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=care">미용/목욕</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=health">영양/건강</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=potty">배변/위생</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" style="color: #FE2E2E;" ><b>BEST</b></a><!-- 베스트 상품 보이게하기 -->
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link" style="color: black;" href="#" id="navbarDropdown" role="button"  aria-expanded="false">
            <b>커뮤니티</b>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="userListAction.u">게시판 글쓰기</a></li>
            <li><a class="dropdown-item" href="#">게시판 목록 보기</a></li>  
          </ul>
        </li>
        <%
			  String userId = (String)session.getAttribute("id");
			%>
			
			<% if (userId != null && userId.equals("admin")) { %>
			 <li class="nav-item dropdown">
				<a href="" class="nav-link" style="color: black;" id="navbarDropdown" role="button"  aria-expanded="false">
            	<b>관리자모드</b></a>
            	<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="inventoryList.in">재고관리</a></li>
					<li><a class="dropdown-item" href="userListAction.u">회원관리</a></li>
					<li><a class="dropdown-item" href="">주문관리</a></li>
					<li><a class="dropdown-item" href="adminProductList.ad">상품관리</a></li>
				</ul>
			</li>
			<% } else if(userId != null){ %>
			<li class="nav-item dropdown">
				<a class="nav-link" style="color: black;" id="navbarDropdown" role="button"  aria-expanded="false"href="#">
				<b>마이페이지</b></a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="userViewAction.u">내정보보기</a></li>
					<li><a class="dropdown-item" href="buyListForm.buy">구매목록</a></li>
					<li><a class="dropdown-item" href="">서브메뉴3</a></li>
					<li><a class="dropdown-item" href="">서브메뉴4</a></li>
				</ul>
			</li>
			<% } %>
    </ul>
    
</div>
       <div class="col-lg-4">
           <c:choose>
              <c:when test="${empty sessionScope.id  }"><!-- 로그인 안됬을때 -->
              <form class="d-flex align-items-center justify-content-end" action="userLoginAction.u">
                <div class="input-group">
                  <input type="text" class="form-control me-2" placeholder="아이디" aria-label="Username" name="id" id="id">
                  <input type="password" class="form-control me-2" placeholder="비밀번호" aria-label="Password" name="user_pass" id="user_pass">
                  <button class="btn btn-outline-primary me-2" type="submit">LOGIN</button>
                </div>
                <div class="d-flex align-items-center ms-3">
                  <a href="userJoinForm.u" class="text-decoration-none me-2">JOIN</a>&nbsp;
                  <a href="#" class="text-decoration-none me-2"><i class="fa-solid fa-magnifying-glass"></i></a>
                  <a href="#" class="text-decoration-none me-2"><i class="fa-solid fa-eye"></i></a>
                  <a href="#" class="text-decoration-none me-2"><i class="fa-solid fa-cart-shopping"></i></a>
                </div>
              </form>
             </c:when>
             
             <c:otherwise><!-- 로그인 됬을때 -->
                <form class="d-flex align-items-center justify-content-end" action="userLoginAction.u">
                <div class="input-group" align="right" style="padding-left: 100px">
                <a href="userViewAction.u" class="text-decoration-none me-2" >
                  ${sessionScope.id }님
                </a>
                  <a class="btn btn-outline-primary me-2" href="userLogout.u">LOGOUT</a>
                </div>
                <div class="d-flex align-items-center ms-3">
                  <a href="#" class="text-decoration-none me-2"><i class="fa-solid fa-magnifying-glass"></i></a>
                  <a href="#" class="text-decoration-none me-2"><i class="fa-solid fa-eye"></i></a>
                  <a href="cartList.ct" class="text-decoration-none me-2"><i class="fa-solid fa-cart-shopping"></i></a>
                </div>
              </form>
             </c:otherwise>
         </c:choose>
      </div>
</div>  	
</div>
</body>
</html>