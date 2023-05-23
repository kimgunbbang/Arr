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
<style>
.scroll-btn {
    position: fixed;
    bottom: 20px;
    right: 20px;
    width: 40px;
    height: 40px;
    background-color: #333;
    color: #fff;
    border-radius: 50%;
    text-align: center;
    line-height: 40px;
    cursor: pointer;
    opacity: 50;
    transition: opacity 0.3s;
    z-index: 9999;
}
</style>
</head>
<body>
<c:if test="${pagefile == null }">
<jsp:forward page="main.p"></jsp:forward>
</c:if>
<div id="scroll-btn" class="scroll-btn">
  <a href="#top"><i class="fas fa-arrow-up"></i></a>
</div>

   <div class="container1 top">
      <!-- 사이트 상단 부분 -->
      <div class="row" >
         <div class="col">
            <!-- 메뉴바자리 -->
            <jsp:include page="menu_top.jsp"></jsp:include>
         </div>
      </div>
      
      <!-- 사이트 본문 부분 -->
      <div class="row">
         <div class="col">
         <c:choose>
            <c:when test="${pagefile != null }">
               <jsp:include page="${pagefile}"></jsp:include>
            </c:when>
         </c:choose>
         </div>
      </div><br>
      <!-- 사이트 하단 부분 -->
	  <div class="row" style="background-color: #f7f7f7; color: #6E6E6E">
	  	<div class="col-5" style="margin-left:20px;margin-top:30px;">
	  	<small><small>
	  		YeongNam Dearfood<br>
			CEO. KP | TEL. 010-9940-6245 | E-MAIL. kkh6245@gmail.com<br>
			ADDRESS. 6th floor, Igok-dong, Dalseo-gu, Daegu, Republic of Korea, 06775<br>
			BUSINESS NO. 000-00-00000 | MAIL ORDER LICENSE. 제0000-대구달서-0000호 <br>
			© 2023<br>
		</small></small>
	  	</div>
	  	<div class="col-3" style="margin-bottom:10px; margin-top:10px;">
	  	
	  		<div class="row">
	  		<p style="font-size: medium; color:#3e7453;">BANK INFO</p>
	  		<small><small>
			BANK 대구은행 000-000000-0000<br>
			예금주 : 산대특kp주식회사
			</small></small>
	  		</div><br>
	  		<div class="row">
	  		<p style="font-size: medium; color:#3e7453;">사업 문의</p>
	  		<small><small>
			마케팅제휴 문의 : cc88@nate.com<br>
			해외수출 문의 : cc88@nate.com
			</small></small>
	  		</div>
	  	
	  	</div>
	  	<div class="col-3" style="margin-top:30px;">
	  		<div class="row">
	  		<p style="font-size: medium; color:#3e7453;">053-427-3313</p>
	  		<small><small>
			AM 09:00 ~ PM 18:00<br>
			PM 12:00 ~ 13:00 (Lunch)<br>
			SAT, SUN, HOLIDAY OFF<br>
			</small></small>
	  		</div>
	  		<div class="row">
	  		<small><small>
	  		CS Email. aaaa@bbbb.com
	  		</small></small>
	  		</div>
	  	</div>
	  </div>
</div>
</body>
</html>
