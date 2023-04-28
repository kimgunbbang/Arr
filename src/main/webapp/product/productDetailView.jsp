<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
</head>
<body>
<div class="컨테이너">
	<div class="행">
		<div class="열1">
			<!-- 제품사진 -->
			<img src="${pageContext.request.contextPath }/images/${product.p_image }" id="p_image">
		</div>
		<div class="열2">
			<!-- 상품명,가격,구매수량,구매버튼,장바구니버튼 -->
			<form action="productBuyAction.p?id=${id }" method="post">
				<div>
					상품명 : ${product.p_name }
					가격 : ${product.p_price }
					구매수량 : <input type="text" name="buy_qty">
					상세설명 : ${product.p_detail }
				</div>
			<input type="submit" value="구매하기">
			<input type="button" value="장바구니">
			<div>
				<!-- 관리자일때 -->
				<a href="productModifyForm.p?p_num=${product.p_num }">수정</a>
			</div>
			</form>
		</div>
	</div>
	<div class="행2">
		<div class="열1">
			<!-- 상품이미지2(설명용)넣기 -->
			설명용 이미지 넣기
			<img src="${pageContext.request.contextPath }/images/${product.p_image2 }" id="p_image2">
		</div>
	</div>
</div>
</body>
</html>