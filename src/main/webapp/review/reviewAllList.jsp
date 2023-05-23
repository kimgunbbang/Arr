<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        <c:if test="${review.p_num eq param.p_num}">
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
        </c:if>
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
</body>
</html>