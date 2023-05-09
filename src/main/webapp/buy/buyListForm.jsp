<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<title>구매목록</title>
</head>
<style>
  .form-border {
    border: 1px solid #ccc; /* 외곽선 스타일 지정 */
    padding: 10px; /* 폼 요소와 외곽선 간격 조정 */
  }
</style>
<body>
<div class="container">
구매목록<br>
<c:choose>
	<c:when test="${empty buyList }">
	구매내역이 없습니다.
	</c:when>
	<c:otherwise>
		<c:forEach var="buyNum" items="${buyNumList }" varStatus="i">
			<form class="form-border">
				<c:forEach var="buy" items="${buyList }" varStatus="j">
					<c:if test="${buyNum==buy.buy_num }">
						<c:if test="${j.index==0  }">
							<h3>${buy.buy_date }</h3>
							${buy.buy_state } &nbsp;
							<a href="#">상세보기</a> &nbsp;
							<a href="#">배송조회</a> &nbsp;
							<c:if test="${buy.buy_state eq 'ready' }">
							<a href="#">주문취소</a> 
							</c:if><br>
							<img src="${pageContext.request.contextPath}/images/${buy.p_image}"> &nbsp;
							${buy.p_name } &nbsp;
							${buy.buy_qty } &nbsp;
							${buy.buy_totalmoney } &nbsp;
						</c:if>
						
					
					</c:if>
				</c:forEach>
			</form>
		</c:forEach>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>