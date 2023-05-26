<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="containerJoin">
<h2> 상품 등록 페이지</h2><hr>
	<form action="productAddAction.p" name="productAddForm" method="post" enctype="multipart/form-data">
		<div class="form-group">
				카테고리명
					<select name="category_name">
						<option value="food">사료/간식
						<option value="cloth">의류/악세사리
						<option value="play">산책/놀이
						<option value="care">미용/목욕
						<option value="health">영양/건강
						<option value="potty">배변/위생
					</select>
		</div>
		<div class="form-group">	
				상품명<input type="text" name="p_name" id="p_name" required>
		</div>
		<div class="form-group">	
				가격<input type="text" name="p_price" id="p_price" required>
		</div>
		<div class="form-group">	
				상품상세내용</div><div class="col"><textarea cols="60" name="p_detail" id="p_detail"></textarea>
		</div>
		<div class="form-group">	
				상품이미지<br>
					메인용<input type="file" name="p_image" id="p_image"><br>
					설명용<input type="file" name="p_image2" id="p_image2">
		</div>
			
		<div class="form-group">
				<input type="submit" value="상품등록" class="zip-search-button">&nbsp;
				<input type="reset"  value="다시작성" class="zip-search-button">&nbsp;
				<input type="button" value="목록보기" onclick="window.location.href='productAllList.p'" class="zip-search-button">
		</div>
			
	</form>
</div>
</body>
</html>