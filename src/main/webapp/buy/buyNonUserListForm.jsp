<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비회원 주문 조회하기</title>
</head>
<body>
    <div class="container" style="width:80%; margin:auto; text-align: center;">
    <h3>비회원 주문 조회하기</h3><hr>
        <form action="nonUserBuyListAction.buy" method="post">
		 <div style="margin: 0 auto; max-width: 600px;">
	            <div class="col-3" style="float: left;">
	                <label for="buy_num">구매번호</label>
	            </div>
	            <div class="col-6" style="float: left;">
	                <input type="text" class="form-control" id="buy_num" name="buy_num" required>
	            </div>
			
            <div style="clear: both;"></div><br>
            	<div class="col-3" style="float: left;">
                	<label for="buy_phone">구매자연락처</label>
                </div>
                <div class="col-6" style="float: left;">
                	<input type="text" class="form-control" id="buy_phone" name="buy_phone" placeholder="(-)없이 입력하세요" required>
            	</div>
            	
            <div style="clear: both;"></div><br>
            <button type="submit" class="btn btn-primary" style="background-color: #27737c; border:none;">조회하기</button>
        </div>
        </form>
    </div>
</body>
</html>
