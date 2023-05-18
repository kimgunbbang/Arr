<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지관리</title>
<style>
.container{
	text-align: center;
}
.table{
	text-align: center;
}
</style>

</head>
<body>
<div class="containor">
<form name="deliform" action="deliveryModifyAction.del" method="post">
	<div class="table">
		<div class="row">
			<h2>배송지 수정</h2>
			<div class="열1"></div><div class="열4">
			<input type="hidden" name="id" id="id" value="${user.id }" readonly>
			<input type="hidden" name="deli_num" id="deli_num" value="${delivery.deli_num }"> 
			</div>
			
			<div class="열1">배송지명 : </div><div class="열4"><input type="text" name="deli_name" id="deli_name" value="${delivery.deli_name }"> </div>
			<div class="열2">우편번호 : </div><div class="열5"><input type="text" name="deli_zipcode" id="deli_zipcode" size="6" value="${delivery.deli_zipcode }" readonly></div>
			<div class="열2"><input type="button" name="zipSearch" value="주소검색"  id="zipSearch" /> </div>
			<div class="열3">배송지 주소 : </div><div class="열6"><input type="text" name="deli_addr" id="deli_addr" value="${delivery.deli_addr }" readonly> </div>
			<div class="열4">배송지 상세 주소 : </div><div class="열7"><input type="text" name="deli_addr2" id="deli_addr2" value="${delivery.deli_addr2 }"></div>
			<div class="열4">배송인 성함 : </div><div class="열7"><input type="text" name="deli_username" id="deli_username" value="${delivery.deli_username }"></div>
			<div class="열5">수령인 전화번호 : </div><div class="열8"><input type="text" name="deli_phone" id="deli_phone" value="${delivery.deli_phone }"></div>
			<div class="열6">
			<input type="submit" value="배송지 수정">
			<input type="reset" value="다시작성">&nbsp;
			<input type="button" value="돌아가기" onclick="history.back()">
			</div>
		</div>	
	</div>
</form>
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("zipSearch").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	document.getElementById("deli_zipcode").value = data.zonecode;
                document.getElementById("deli_addr").value = data.address; // 주소 넣기
                document.querySelector("input[name=deli_addr2]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
</html>