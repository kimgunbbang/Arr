<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 목록 보기</title>
<style>

.table {
margin: auto;
text-align: center;
}

.row {
display: flex;
flex-wrap: wrap;
justify-content: center;
align-items: center;
}

.열1,
.열2 {
margin: 10px;
}

.열1 a {
text-decoration: none;
padding: 5px 10px;
background-color: #007bff;
color: #fff;
border-radius: 5px;
}

.열2 a {
text-decoration: none;
padding: 5px 10px;
background-color: #f8f9fa;
color: #495057;
border-radius: 5px;
border: 1px solid #ced4da;
}

.열2 a:hover {
background-color: #007bff;
color: #fff;
}

</style>
<script>
</script>
</head>
<body>
<div class="container">
	<div class="table">
		<div class="row">
			<h2>배송지 목록</h2><hr>
			<c:forEach var="delivery" items="${deliveryList}">
				<div class="열2"><a href="deliveryViewAction.del?deli_num=${delivery.deli_num}">${delivery.deli_name}</a></div>
			</c:forEach>
		</div>
					<div class="열1"><a href="deliveryAdd.del?id=${user.id}">배송지 신규 등록</a></div>
	</div>
</div>
</body>
</html>