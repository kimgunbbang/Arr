<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
</head>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="container">
<h1> 상품 수정 페이지</h1>
	<form action="productModifyAction.p" name="productModifyForm" method="post" enctype="multipart/form-data">
		<div class="table">
			<div class="row">
				<div class="col">카테고리명</div>
											<div class="col">
												<select name="category_name">
													<option value="food" ${product.category_name eq food? 'selected':'' }>사료/간식
													<option value="cloth" ${product.category_name eq cloth? 'selected':'' }>의류/악세사리
													<option value="play" ${product.category_name eq play? 'selected':'' }>산책/놀이
													<option value="care" ${product.category_name eq care? 'selected':'' }>미용/목욕
													<option value="health" ${product.category_name eq health? 'selected':'' }>영양/건강
													<option value="potty" ${product.category_name eq potty? 'selected':'' }>배변/위생
												</select>
											</div>
			</div>
			<input type="hidden" name="p_num" id ="p_num" value = "${product.p_num }">
			<input type="hidden" name="p_readcount" id ="p_readcount" value = "${product.p_readcount }">
			
			<div class="row">
				<div class="col">상품명</div><div class="col"><input type="text" name="p_name" id="p_name" value = "${product.p_name }" required></div>
			</div>
			<div class="row">
				<div class="col">가격</div><div class="col"><input type="text" name="p_price" id="p_price" value = "${product.p_price }" required></div>
			</div>
			<div class="row">
				<div class="col">상품상세내용</div><div class="col"><textarea cols="60" name="p_detail" id="p_detail">${product.p_detail }</textarea></div>
			</div>
			<div class="row">
				<div class="col">상품이미지</div><div class="col">
					메인용<input type="file" name="p_image" id="p_image" value="${product.p_image}">
					설명용<input type="file" name="p_image2" id="p_image2" value="${product.p_image2}">
				</div>
			</div>
			<div class="row">
				<input type="radio" name="p_hide" id="p_hide" value="false" ${product.p_hide eq 'false' ? 'checked' : ''}>보이기
				<input type="radio" name="p_hide" id ="p_hide" value ="true" ${product.p_hide eq 'true' ? 'checked' : ''}>품절
			</div>
			<div class="row">
				<div class="col">
				<input type="submit" value="상품수정">&nbsp;
				<input type="reset"  value="다시작성">&nbsp;
				<input type="button" value="목록보기" onclick="window.location.href='productAllList.p'">
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>