<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
</style>
</head>
<body>
<form action="reviewWriteAction.r">
  <input type="hidden" id="r_num">
  <input type="hidden" id="id">
  <input type="hidden" id="p_num">
  <label for="r_title">제목:</label>
  <input type="text" id="r_title" name="r_title" required><br>

  <label for="r_rating" id="r_rating">평점:</label>
  <div class="rating">
    <input type="radio" id="star5" name="r_rating" value="5">
    <label for="star5"></label>
    <input type="radio" id="star4" name="r_rating" value="4">
    <label for="star4"></label>
    <input type="radio" id="star3" name="r_rating" value="3">
    <label for="star3"></label>
    <input type="radio" id="star2" name="r_rating" value="2">
    <label for="star2"></label>
    <input type="radio" id="star1" name="r_rating" value="1">
    <label for="star1"></label>
  </div><br>

  <label for="r_detail">내용:</label><br>
  <textarea id="r_detail" name="r_detail" rows="4" cols="50" required></textarea><br>

  <label for="r_image">이미지 등록:</label>
  <input type="file" id="r_image" name="r_image"><br>

  <input type="submit" value="리뷰 작성">
</form>
</body>
</html>