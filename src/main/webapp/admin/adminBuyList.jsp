<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://assets4.cre.ma/widgets/assets/pc-c9334d1331a67b88a5bbb28c4c16b01ef5184ab1c165983eb0e8207debdf3b2d.css" rel="stylesheet" type="text/css">

<style>
 .form-border {
   border: 1px solid #ccc; /* 외곽선 스타일 지정 */
   padding: auto; /* 폼 요소와 외곽선 간격 조정 */
 }
.col{
	display: inline;
}
input{
	border:none;
	outline: none;
}
</style>
<script>
function onChange(selectElement) {
	var buy_num = selectElement.form.getElementID("buy_num");
	var buy_state = selectElement.form.getElementID("buy_state");
	this.value = selectElement.value;
}
function openModal(buy_num, event){
	event.preventDefault(); // 기본 이벤트 막기
	window.open('buyInfoDetail.buy?buy_num='+buy_num, 'popup', 'width=500, height=500');

}
</script>
</head>
<body>
<div class = "container">
	<div class="row">
		<!-- 탭 -->
		<ul class="nav nav-tabs nav-fill">
		  <li class="nav-item">
		    <a class="nav-link ${state == '' || state eq null? 'active':'' }" href="adminBuyList.ad">전체주문</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'ready'? 'active':'' }" href="adminBuySelectList.ad?buy_state=ready">배송준비</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'deliver'? 'active':'' }" href="adminBuySelectList.ad?buy_state=deliver">배송중</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'completion'? 'active':'' }" href="adminBuySelectList.ad?buy_state=completion">배송완료</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'finish'? 'active':'' }" href="adminBuySelectList.ad?buy_state=finish">구매확정</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'cancel'? 'active':'' }" href="adminBuySelectList.ad?buy_state=cancel">주문취소건</a>
		  </li>
		</ul>
	</div>
	<div class="row">
		<div class="col-1">순번</div>
		<div class="col-1">주문번호</div>
		<div class="col-2">주문날짜</div>
		<div class="col-1">아이디</div>
		<div class="col-3">상품명</div>
		<div class="col-1">수량</div>
		<div class="col-1">금액</div>
		<div class="col-1">주문상태</div>
		<div class="col-1"></div>
	</div>
	<%int num = 0; %>
	
	<c:forEach var="buyNum" items="${buyNumList}" varStatus="i">
	<%int count=0; //1건이상이면 '외 ' 추가할용도 %>
		<form action="stateChange.ad" class="form-border" id="buyListForm">
		<div class="row">
			<div class="col-1"><%=++num %></div>
			<c:set var="first" value="true" />
			<c:forEach var="buy" items="${buyList }" varStatus="i">
			<c:if test="${buyNum eq buy.buy_num}">
				<c:if test="${first}">
				<div class="col-1"><a href="" onclick="openModal(${buy.buy_num },event)"><input type="text" name="buy_num" value="${buy.buy_num }"  readonly></a></div>
				<div class="col-2"><a href="" onclick="openModal(${buy.buy_num },event)"><input type="text" name="buy_date" value="${buy.buy_date }" readonly></a></div>
				<div class="col-1"><a href="" onclick="openModal(${buy.buy_num },event)"><input type="text" name="id" value="${buy.id }" readonly></a></div>
				<div class="col-3"><a href="" onclick="openModal(${buy.buy_num },event)"><input type="text" name="p_name" value="${buy.p_name }" readonly></a>
					<c:if test="${!i.last && buyList[i.index + 1].buy_num eq buy.buy_num}">
					외
					</c:if>
				</div>
				<div class="col-1"><input type="text" name="buy_qty" value="${buy.buy_qty }" readonly></div>
				<div class="col-1"><input type="text" name="buy_totalmoney" value="${buy.buy_totalmoney }" readonly></div>
				<div class="col-1">
					<select name="buy_state" onchange="handleChange(this)">
						<option value="ready" ${buy.buy_state =='ready'? 'selected':'' }>배송준비</option>
						<option value="deliver" ${buy.buy_state =='deliver'? 'selected':'' }>배송중</option>
						<option value="completion" ${buy.buy_state =='completion'? 'selected':'' }>배송완료</option>
						<option value="cancel" ${buy.buy_state =='cancel'? 'selected':'' }>주문취소</option>
						<option value="finish" ${buy.buy_state =='finish'? 'selected':'' }>구매확정</option>
						<!-- 교환/환불넣기.. -->
					</select>
				</div>
				<div class="col-1"><button type="submit" onclick="this.form.submit()">상태변경</button></div>
				<c:set var="first" value="false" />
				</c:if>
			</c:if>
			</c:forEach>
			</div>
		</form>
	</c:forEach>
</div>
</body>
</html>