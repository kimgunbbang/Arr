<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="container">
<h1> 상품 등록 페이지</h1>
	<form action="productAddAction.p" name="productAddForm" method="post" enctype="multipart/form-data">
		<div class="table">
			<div class="row">
				<div class="col">카테고리명</div>
											<div class="col">
												<select name="category_name">
													<option value="food">사료/간식
													<option value="cloth">의류/악세사리
													<option value="play">산책/놀이
													<option value="care">미용/목욕
													<option value="health">영양/건강
													<option value="potty">배변/위생
												</select>
											</div>
			</div>
			<div class="row">
				<div class="col">상품명</div><div class="col"><input type="text" name="p_name" id="p_name" required></div>
			</div>
			<div class="row">
				<div class="col">가격</div><div class="col"><input type="text" name="p_price" id="p_price" required></div>
			</div>
			<div class="row">
				<div class="col">상품상세내용</div><div class="col"><textarea cols="60" name="p_detail" id="p_detail"></textarea></div>
			</div>
			<div class="row">
				<div class="col">상품이미지</div><div class="col">
					메인용<input type="file" name="p_image" id="p_image">
					설명용<input type="file" name="p_image2" id="p_image2">
				</div>
			</div>
			<div class="row">
				<div class="col">
				<input type="submit" value="상품등록">&nbsp;
				<input type="reset"  value="다시작성">&nbsp;
				<input type="button" value="목록보기" onclick="window.location.href='productAllList.p'">
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>