<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#datePick {
  display: inline;
}
.img{
	width: 50%;
}
</style>
<script>
function optionChange(e) {
	var otherPick = document.getElementById("otherPick");
	var datePick = document.getElementById("datePick");
	if(e.target.value == 'inven_date'){
		otherPick.style.display="none";
		datePick.style.display="inline-block";
	}else{
		otherPick.style.display="inline-block";
		datePick.style.display="none";
	}
}

function dateChk() {
    var startDate = new Date(document.getElementById("startDate").value);
    var endDate = new Date(document.getElementById("endDate").value);
    if (endDate < startDate) {
        alert("끝날짜가 시작날짜보다 이전 일 수 없습니다.");
        return false;
    }
}


</script>
<body>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<div class="container">
<div class="row">
		<!-- 탭 -->
		<ul class="nav nav-tabs nav-fill">
		 <li class="nav-item">
		    <a class="nav-link" href="inventoryInOutForm.in">입출고등록</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state == '' || state eq null? 'active':'' }" href="inventoryList.in">전체목록</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link ${state eq 'invenStatus'? 'active':'' }" href="inventoryProductList.in">상품별재고현황</a>
		  </li>
		  
		</ul>
	</div>
<form action="invenSearchList.in"  onsubmit="return dateChk()">
			<select name="invenSearchOption" onchange="optionChange(event)">
				<option value="p_num" ${invenSearchOption == 'p_num' ? 'selected' : ''}>상품번호</option>
				<option value="p_name" ${invenSearchOption == 'p_name' ? 'selected' : ''}>상품명</option>
				<option value="inven_date" ${invenSearchOption == 'inven_date' ? 'selected' : ''}>날짜</option>
			</select>
			<div id="otherPick" style="display: inline-block;" >
				<input type="text" name="invenSearchValue" id="searchValue">
			</div>
				
			<div id="datePick" style="display: none;">
				<input type="date" id="startDate" name="invenSearchValueStartDate">
				~
				<input type="date" id="endDate" name="invenSearchValueEndDate" onchange="dateChk()">
			</div>
			<input type="submit" value="검색" >
</form>

<c:choose>
	<c:when test="${empty inventoryList }">
	입출고내역이 없습니다.
	</c:when>
	<c:when test="${not empty inventoryList  && (state eq '' || state eq null)}">
		<div class="table">
			<div class="row">
				<div class="col">순번</div>
				<div class="col">상품번호</div>
				<div class="col">상품명</div>
				<div class="col">입고량</div>
				<div class="col">출고량</div>
				<div class="col">재고량</div>
				<div class="col">입출고날짜</div>
			</div><hr>
			<c:forEach var="inventory" items="${inventoryList }">
				<div class="row">
					<div class="col">${inventory.inven_num }</div>
					<div class="col">${inventory.p_num }</div>
					<c:forEach var="product" items="${productList }">
						<c:if test="${inventory.p_num eq product.p_num }">
							<div class="col">${product.p_name }</div>
						</c:if>
					</c:forEach>
					<div class="col">${inventory.inven_in }</div>
					<div class="col">${inventory.inven_out }</div>
					<div class="col">${inventory.inven_qty }</div>
					<div class="col">${inventory.inven_date }</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:when test="${not empty inventoryList  && state eq 'invenStatus'}">
		<div class="table">
			<div class="row">
				<div class="col">상품번호</div>
				<div class="col">상품사진</div>
				<div class="col">상품명</div>
				<div class="col">총입고량</div>
				<div class="col">총출고량</div>
				<div class="col">현재고량</div>
			</div><hr>
			<c:forEach var="inventory" items="${inventoryList }">
				<div class="row">
					<div class="col">${inventory.p_num }</div>
					<c:forEach var="product" items="${productList }">
						<c:if test="${inventory.p_num eq product.p_num }">
							<div class="col"><img class="img" src="${pageContext.request.contextPath}/images/${product.p_image }"></div>
							<div class="col">${product.p_name }</div>
						</c:if>
					</c:forEach>
					<div class="col">${inventory.inven_in }</div>
					<div class="col">${inventory.inven_out }</div>
					<div class="col">${inventory.inven_qty }</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
<section id="pageList">
	<c:choose>
	<c:when test="${pageinfo.page<=1 }">[이전]&nbsp;</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test="${searchinfo != null }">
			<a href="invenSearchList.in?page=${pageinfo.page-1 }&invenSearchOption=${searchinfo[0] }&invenSearchValue=${searchinfo[1] }&invenSearchValueStartDate=${searchinfo[2] }&invenSearchValueEndDate=${searchinfo[3] }">[이전]</a>&nbsp;
		</c:when>
		<c:otherwise>
			<a href="inventoryList.in?page=${pageinfo.page-1 }">[이전]</a>&nbsp;
		</c:otherwise>
		</c:choose>
	</c:otherwise>
	</c:choose>
	<c:forEach var="a" begin="${pageinfo.startpage }" end="${pageinfo.endpage }" step="1">
		<c:choose>
		<c:when test="${a==pageinfo.page }">[${a }]</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${searchinfo != null }">
					<a href="invenSearchList.in?page=${a }&invenSearchOption=${searchinfo[0] }&invenSearchValue=${searchinfo[1] }&invenSearchValueStartDate=${searchinfo[2] }&invenSearchValueEndDate=${searchinfo[3] }">[${a }]</a>&nbsp;
				</c:when>
				<c:otherwise>
					<a href="inventoryList.in?page=${a }">[${a }]</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:choose>
		<c:when test="${pageinfo.page>=pageinfo.maxpage }">[다음]</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${searchinfo != null }">
				<a href="invenSearchList.in?page=${pageinfo.page+1 }&invenSearchOption=${searchinfo[0] }&invenSearchValue=${searchinfo[1] }&invenSearchValueStartDate=${searchinfo[2] }&invenSearchValueEndDate=${searchinfo[3] }">[다음]</a>&nbsp;
			</c:when>
			<c:otherwise>
				<a href="inventoryList.in?page=${pageinfo.page+1 }">[다음]</a>&nbsp;
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</section>
</div>
</body>
</html>