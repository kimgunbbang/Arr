<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비회원 주문 조회하기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form action="nonUserBuyListAction.buy" method="post" class="mt-4">
            <h3 class="mb-4">비회원 주문 조회하기</h3>
            <div class="form-group">
                <label for="buy_num">구매번호</label>
                <input type="text" class="form-control" id="buy_num" name="buy_num" required>
            </div>
            <div class="form-group">
                <label for="buy_phone">구매자연락처</label>
                <input type="text" class="form-control" id="buy_phone" name="buy_phone" placeholder="(-)없이 입력하세요" required>
            </div>
            <button type="submit" class="btn btn-primary">조회하기</button>
        </form>
    </div>
</body>
</html>
