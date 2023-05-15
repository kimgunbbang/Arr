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
  .img{
  	width:100px;
  	height:100px;
  }
</style>
<script>
function cancel(buy_num, event) {
	  event.preventDefault(); // 이벤트의 기본 동작을 취소

	  if (confirm("정말 취소하시겠습니까?")) {
	    location.href = "buyCancel.buy?buy_num=" + buy_num;
	  }
	}
</script>
<body>
<div class="container">
<h1>구매목록</h1><hr>
<c:choose>
	<c:when test="${empty buyList }">
	구매내역이 없습니다.
	</c:when>
	<c:otherwise>
		
		<c:forEach var="buyNum" items="${buyNumList}" varStatus="i">
		<c:set var="lastTotalMoney" value="${0 }"/>
		  <form class="form-border">
		    <c:set var="first" value="true" />
		    <c:forEach var="buy" items="${buyList}" varStatus="j">
		      <c:if test="${buyNum eq buy.buy_num}">
		        <!-- 첫 번째 buy 객체이면 날짜 등 정보를 출력 -->
		        <c:if test="${first}">
		          <h3>${buy.buy_date }</h3>
		          ${buy.buy_state } 확인용&nbsp;
		          <c:set var="state" value="${buy.buy_state }"/>
		          <c:choose>
			          <c:when test="${buy.buy_state eq 'ready'}">
				        <a href="buyInfoForm.buy?buy_num=${buy.buy_num }">상세보기</a> &nbsp;
			          	<a href="#">배송조회</a> &nbsp;
			            <a href="buyCancel.buy?buy_num=${buy.buy_num}" onclick="cancel('${buy.buy_num}' ,event)">주문취소</a><br> 
			          	배송준비
			          </c:when>
			          <c:when test="${buy.buy_state eq 'cancel' }">
			            <a href="buyInfoForm.buy?buy_num=${buy.buy_num }">상세보기</a> &nbsp;
			          	취소완료
			          </c:when>
			          <c:when test="${buy.buy_state eq 'completion' }">
			          	<a href="buyInfoForm.buy?buy_num=${buy.buy_num }">상세보기</a> &nbsp;
			          	<a href="" >구매확정</a>
			          	배송완료
			          </c:when>
			          <c:when test="${buy.buy_state eq 'finish' }">
			          	<a href="buyInfoForm.buy?buy_num=${buy.buy_num }">상세보기</a> &nbsp;
			          	<a href="" >후기작성</a>
			          	구매확정
			          </c:when>
			          <c:when test="${buy.buy_state eq 'deliver' }">
			          	<a href="buyInfoForm.buy?buy_num=${buy.buy_num }">상세보기</a> &nbsp;
			          	배송중
			          </c:when>
			          <c:otherwise>
			          </c:otherwise>
		          </c:choose>
		          <c:set var="first" value="false" />
		          
		        </c:if>
		        <!-- buy 객체 정보 출력 -->
		        <a href="productDetailView.p?p_num=${buy.p_num }"><img class="img" src="${pageContext.request.contextPath}/images/${buy.p_image}"></a> &nbsp;
		        ${buy.p_name } &nbsp;
		        ${buy.buy_qty }개 &nbsp;
		        ${buy.buy_totalmoney }원 &nbsp;<br>
		        <c:set var="lastTotalMoney" value="${lastTotalMoney+buy.buy_totalmoney }"/>
		      </c:if>
		    </c:forEach>
		    <c:choose>
			    <c:when test="${state eq 'cancel' }">
			    <b style="text-decoration: line-through;">총 금액 : ${lastTotalMoney}원</b>
			    </c:when>
			    <c:otherwise>
			    <b>총 금액 : ${lastTotalMoney }원</b>
			    </c:otherwise>
			</c:choose>
		  </form>
		</c:forEach>
	
	</c:otherwise>
</c:choose>
</div>
</body>
</html>