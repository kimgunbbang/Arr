<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/cb777d7294.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js"></script>
<html>
<head>

<style>
.dropdown:hover .dropdown-menu {
  display: block;
}
.custom-container {
    padding-left: 0;
    padding-right: 0;
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

.offcanvas-header {
  display: flex;
  justify-content: center;
}
  
.offcanvas-header form {
  margin: auto;
}
.btn.btn-primary1 {
    color: blue;
    background-color: white;
    border: 0px;
}


</style>
<script>
function toggleVisibility(event) {
	 event.preventDefault(); // 기본 동작 막기
    var toggleDiv = document.getElementById("toggle");
    if (toggleDiv.style.display === "none") {
      toggleDiv.style.display = "block";
    } else {
      toggleDiv.style.display = "none";
    }
  }
</script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class="container custom-container" style="height:170px; background-color: #FFF">
  <div class="row" style="height:180px; display: flex; align-items: center; justify-content: center;">
    <div class="col-3">
    <a href="main.p">
      <img src="${pageContext.request.contextPath}/images/달봉이네1.png" alt="로고" width="150" height="150"/>
    </a>
    </div>
    <div class="col-4">
      <ul class="nav nav-pills">
        <li class="nav-item dropdown">
          <a class="nav-link" style="color: black;" href="productAllList.p" id="navbarDropdown" role="button"  aria-expanded="false">
            <b>SHOP</b>
          </a>
          <!-- 드롭다운 메뉴 -->
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="productAllList.p">전체상품</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=food">사료/간식</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=cloth">생활/의류</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=play">산책/놀이</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=care">미용/목욕</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=health">영양/건강</a></li>
            <li><a class="dropdown-item" href="productSelectList.p?category_name=potty">배변/위생</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link" href="productBestList.p" style="color: #FE2E2E;" ><b>BEST</b></a><!-- 베스트 상품 보이게하기 -->
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link" style="color: black;" href="#" id="navbarDropdown" role="button"  aria-expanded="false">
            <b>커뮤니티</b>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">게시판 목록보기</a></li>
            <li><a class="dropdown-item" href="#">상품전체 리뷰보기</a></li>  
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
               <li><a class="dropdown-item" href="adminBuyList.ad">주문관리</a></li>
               <li><a class="dropdown-item" href="adminProductList.ad">상품관리</a></li>
               <li><a class="dropdown-item" href="#">게시글관리</a></li>
            </ul>
         </li>
         <% } else if(userId != null){ %>
         <li class="nav-item dropdown">
            <a class="nav-link" style="color: black;" id="navbarDropdown" role="button"  aria-expanded="false"href="#">
            <b>마이페이지</b></a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
               <li><a class="dropdown-item" href="userViewAction.u?id=${sessionScope.id }">내정보보기</a></li>
               <li><a class="dropdown-item" href="buyListForm.buy">구매목록</a></li>
               <li><a class="dropdown-item" href="qnaMyListAction.q">1:1문의내역</a></li>
            </ul>
         </li>
         <% } else{%>
         <li class="nav-item dropdown">
             <a class="nav-link" href="nonUserBuyListForm.buy"><b>비회원주문조회</b></a>
           </li>
         <%} %>
    </ul>
    
</div>
       
           <c:choose>
              <c:when test="${empty sessionScope.id  }"><!-- 로그인 안됬을때 -->
              <div class="col-3">
	              <form class="d-flex align-items-center justify-content-end" action="userLoginAction.u">
	                <div class="input-group">
	                  <input type="text" class="form-control me-2" placeholder="아이디" aria-label="Username" name="id" id="id">
	                  <input type="password" class="form-control me-2" placeholder="비밀번호" aria-label="Password" name="user_pass" id="user_pass">
	                  <button class="btn btn-outline-primary me-2" type="submit">LOGIN</button>
	                </div>
	                <div class="d-flex align-items-center ms-3">
	                  <a href="userJoinForm.u" class="text-decoration-none me-2">JOIN</a>&nbsp;
	                </div>
	              </form>
              </div>
             </c:when>
             
             <c:otherwise><!-- 로그인 됬을때 -->
             <div class="col-3">
                <form class="d-flex align-items-center justify-content-end" action="userLoginAction.u">
                <div class="input-group" align="right" style="padding-left: 100px">
                <a href="userViewAction.u?id=${sessionScope.id }" class="text-decoration-none me-2" >
                  ${sessionScope.id }님
                </a>
                  <a class="btn btn-outline-primary me-2" href="userLogout.u">LOGOUT</a>
                </div>
                </form>
             </div>
			 </c:otherwise>
			 </c:choose>
			 <div class="col-1">
                <div class="d-flex align-items-center ms-3">
                  <a href="#" id="btn_toggle" class="btn btn-primary btn-primary1" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop"><i class="fa-solid fa-magnifying-glass"></i></a>
                  <a href="productRecent.p" class="text-decoration-none btn btn-primary btn-primary1"><i class="fa-solid fa-eye"></i></a>
	              <a href="cartList.ct" class="text-decoration-none btn btn-primary btn-primary1"><i class="fa-solid fa-cart-shopping"></i></a>
                </div>
              	<div class="offcanvas offcanvas-top" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
	              <div class="offcanvas-header">
					  <form action="pSearch.p" style="text-align: center;">
					  <h3>SEARCH</h3>
					    <input type="text" name="pSearch">
					    <button type="submit" onclick="submitForm()"><i class="fa-solid fa-magnifying-glass"></i></button>
					  	<button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
					  </form>
				  </div>
			  	</div>
             </div>	
</div>

</div>
</body>
</html>