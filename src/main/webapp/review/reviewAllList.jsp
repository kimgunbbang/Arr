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
    <link href="https://assets4.cre.ma/widgets/assets/pc-c9334d1331a67b88a5bbb28c4c16b01ef5184ab1c165983eb0e8207debdf3b2d.css" rel="stylesheet" type="text/css">
	

<style>

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
#prdtBtmAcdn {
    margin: 50px auto;
    padding: 0 10px;
}
#prdtBtmAcdn .accordionButton {
    font-size: 12px;
    height: 40px;
    padding: 5px 0;
}
#prdtBtmAcdn .accordionButton {
    font-family: 'HGGGothicssi','Helvetica','Arial','Apple SD Gothic Neo','맑은 고딕','Malgun Gothic','돋움','Dotum',sans-serif;
    height: 50px;
    border-top: 1px solid #477a7b;
    padding: 10px 0;
    color: #477a7b;
    font-weight: 600;
    position: relative;
    cursor: pointer;
}
#prdtBtmAcdn .accordionContent {
    padding: 10px 0 0;
    margin-bottom: 30px;
    line-height: 1.7em;
}
.accordionContent {
    width: 100%;
    line-height: 1.8em;
    color: #333;
    padding: 25px 30px 25px 40px;
    margin: 0;
    font-size: 13px;
    clear: both;
}
ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}
.imgsize{
	width: 70%;
	
}
.nav nav-tabs{
text-align: center;
}
  .review-image{
  	width:200px;
  	height:200px;
  }
  .review-item {
    margin-bottom: 20px;
    padding: 20px;
    background-color: aliceblue;
    border-radius: 20px;
    border: 1px solid lavender;
    
  }
  .review-item td.review-info {
  	text-align: right;
 
  }
  .star-icon {
  color: gold; /* 별 아이콘의 색상 */
  font-size: 20px; /* 별 아이콘의 크기 */
}


.rating {
  display: inline-block;
}

.rating input[type="radio"] {
  display: none;
}

.rating label {
  color: #ddd;
  float: right;
}

.rating label:before {
  content: "\2605";
  margin-right: 5px;
}

.rating input[type="radio"]:checked ~ label {
  color: #ffdd00;
}

<%-- 문의 게시판 --%>

  .qna-board {
    display: flex;
    flex-direction: column;
  }
  
  .qna {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
  }
  
  .qna-summary {
    cursor: pointer;
    background-color: #ecfdf3;
    padding: 10px;
    margin-bottom: 5px;
    border-radius: 20px;
    border: 1px solid lavender;
  }
  
  .qna-content {
    display: none;
    background-color: #ecfdf3;
    padding: 10px;
  }
  
  .qna-summary-info {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  
  .qna-summary-info div {
    margin-right: 10px;
  }
  
  .answer-form textarea {
    width: 100%;
    height: 100px;
    resize: vertical;
    margin-bottom: 5px;
  }
  
  .answer-form button {
    padding: 5px 10px;
    background-color: #f33;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .answer-form button:hover {
    background-color: #d00;
  }
  
</style>
</head>
<body>
<!-- 리뷰 게시판 -->
<div class="review" id="reviewSection">
  <div class="col-md-12">
    <br>  <br>
    <div style="text-align: center;">
      <h3><b>REVIEW</b></h3>
    </div>
    
    <div class="reviewForm">
      <%-- 게시판 데이터를 반복해서 출력하는 부분 --%>
      <c:forEach var="review" items="${reviewList}">
          <div class="review-item" style="height: auto; width: 800px; margin: auto; margin-bottom: 20px;">
            <div class="rating">
              <c:forEach var="i" begin="1" end="${review.r_rating}">
                <span class="star-icon">&#9733;</span>
              </c:forEach> 
            </div>
            <div class="review-info" style="display: flex; justify-content: space-between;">
              <div style="text-align: left;"><b>${review.r_title}</b></div>
              <div style="text-align: right;">작성자: ${review.id}</div>
            </div>
            <div>${review.r_detail}</div>
            <c:if test="${not empty review.r_image}">
              <div class="review-image">
                <img src="${pageContext.request.contextPath}/images/${review.r_image}" class="review-image img-fluid rounded shadow">
              </div>
            </c:if>
            <c:if test="${sessionScope.id eq review.id}">
              <div style="text-align: right;">
                <a href="reviewDeleteAction.r?r_num=${review.r_num}&p_num=${review.p_num}" onclick="confirmDelete('${review.r_num}', event)">삭제</a>
              </div>
            </c:if>
          </div>
      </c:forEach>
    </div>
  </div>
</div>
<script>
function convertRating() {
  var stars = document.getElementsByName("r_ratingValue");
  var ratingValue = 0;
  
  for (var i = 0; i < stars.length; i++) {
    if (stars[i].checked) {
      ratingValue = stars[i].value;
      break;
    }
  }
  
  document.getElementById("r_rating").value = ratingValue;
}
</script>

<script>
function confirmDelete(userId, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 삭제하시겠습니까?")) {
	    location.href = "reviewDeleteAction.r?r_num=" + userId;
	  }
	}
</script>

<section id="pageList" style="text-align: center;">
	<c:choose>
	<c:when test="${pageinfo.page<=1 }">[이전]&nbsp;</c:when>
	<c:otherwise>
			<a href="reviewList.r?page=${pageinfo.page-1 }">[이전]</a>&nbsp;
	</c:otherwise>
	</c:choose>
	<c:forEach var="a" begin="${pageinfo.startpage }" end="${pageinfo.endpage }" step="1">
		<c:choose>
			<c:when test="${a==pageinfo.page }">[${a }]</c:when>
		<c:otherwise>
			<a href="reviewList.r?page=${a }">[${a }]</a>&nbsp;
		</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:choose>
		<c:when test="${pageinfo.page>=pageinfo.maxpage }">[다음]</c:when>
		<c:otherwise>
			<a href="reviewList.r?page=${pageinfo.page+1 }">[다음]</a>&nbsp;
		</c:otherwise>
	</c:choose>
</section>



</body>
</html>
