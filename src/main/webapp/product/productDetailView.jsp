<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://assets4.cre.ma/widgets/assets/pc-c9334d1331a67b88a5bbb28c4c16b01ef5184ab1c165983eb0e8207debdf3b2d.css" rel="stylesheet" type="text/css">
<style>
#prdtBtmAcdn {
    margin: 50px auto;
    padding: 0 10px;
}
#prdtBtmAcdn .accordionButton {
    font-size: 12px;
    height: 40px;
    padding: 5px 0;
}
#prdtBtmAcdn .accordionButton {
    font-family: 'HGGGothicssi','Helvetica','Arial','Apple SD Gothic Neo','맑은 고딕','Malgun Gothic','돋움','Dotum',sans-serif;
    height: 50px;
    border-top: 1px solid #477a7b;
    padding: 10px 0;
    color: #477a7b;
    font-weight: 600;
    position: relative;
    cursor: pointer;
}
#prdtBtmAcdn .accordionContent {
    padding: 10px 0 0;
    margin-bottom: 30px;
    line-height: 1.7em;
}
.accordionContent {
    width: 100%;
    line-height: 1.8em;
    color: #333;
    padding: 25px 30px 25px 40px;
    margin: 0;
    font-size: 13px;
    clear: both;
}
ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}
.imgsize{
	width: 80%;
}
</style>
</head>
<body>
    <div class="container my-5">
        <div class="row">
            <div class="col-md-6">
                <!-- 제품사진 -->
                <img src="${pageContext.request.contextPath }/images/${product.p_image }" id="p_image" class="img-fluid rounded shadow imgsize">
            </div>
            <div class="col-md-6 my-4">
                <!-- 상품명,가격,구매수량,구매버튼,장바구니버튼 -->
                
                <form action="buyActionForm.buy?id=${id }&p_num=${product.p_num}" method="post">
                <h2>${product.p_name }</h2>
                <input type="hidden" class="text-muted" name="p_price" value="${product.p_price }" readonly>${product.p_price }
                    <div class="form-group my-3">
                        <label for="buy_qty">구매수량 :</label>
                        <input type="number" name="buy_qty" class="form-control" value="1" min="1">
                    </div>
                    <div class="form-group my-3">
                        <label for="p_detail">상세설명 :</label>
                        <p>${product.p_detail }</p>
                    </div>
                    <c:if test="${product.p_hide=='0' }">
                    <button type="submit" class="btn btn-primary my-3">구매하기</button>

                    <button type="button" class="btn btn-secondary my-3" 
                   onclick="location.href = 'cartAddAction.ct?p_num=${product.p_num}'">
                    장바구니</button>

                    </c:if>

                    
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- 상품이미지2(설명용)넣기 -->
                <img src="${pageContext.request.contextPath }/images/${product.p_image2 }" id="p_image2" class="img-fluid rounded shadow my-5 imgsize">
            </div>
        </div>
     <ul id="prdtBtmAcdn">
<li>
                        <div class="accordionButton on">상품결제정보</div>
                        <div class="accordionContent on" >
                            고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다. 확인과정에서 도난 카드의 사용이나 타인 명의의 주문등
      정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류 또는 취소할 수 있습니다. &nbsp; <br>
      <br>
      무통장 입금은 상품 구매 대금은 PC뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입금하시면 됩니다. &nbsp;<br>
      주문시 입력한&nbsp;입금자명과 실제입금자의 성명이 반드시 일치하여야 하며, 7일 이내로 입금을 하셔야 하며&nbsp;입금되지
      않은 주문은 자동취소 됩니다. <br>
                        </div>
                    </li>
                    <li>
                        <div class="accordionButton on">배송정보</div>
                        <div class="accordionContent" >
                            <ul class="delivery">
								<li>배송 방법 : 택배</li>
                                <li>배송 지역 : 전국지역</li>
                                <li>배송 비용 : 3,000원</li>
                                <li>배송 기간 : 3일 ~ 7일</li>
                                <li>배송 안내 : - 산간벽지나 도서지방은 별도의 추가금액을 지불하셔야 하는 경우가 있습니다.<br>
   								 고객님께서 주문하신 상품은 입금 확인후 배송해 드립니다. 다만, 상품종류에 따라서 상품의 배송이 다소 지연될 수 있습니다.<br>
								</li>
                            </ul>
						</div>
                    </li>
                    <li>
                        <div class="accordionButton on">교환 및 반품정보</div>
                        <div class="accordionContent" >
                            <div><div><b>교환 및 반품이 가능한 경우</b></div>
                            <div>- 구매자 단순변심은 상품 수령 후 7일 이내 (구매자 반품배송비 부담)</div>
                            <div>- 표시/광고와 상이, 상품하자의 경우 상품 수령 후 3개월 이내 </div>
                            <div>　표시/광고와 다른 사실을 안 날로부터 30일 이내 (판매자 반품배송비 부담) </div>
                            <div>　둘 중 하나 경과 시 반품/교환 불가 </div>
                            <div><br></div><div>
                            <b>교환 및 반품이 불가능한 경우</b></div>
                            <div>- 반품요청기간이 지난 경우</div>
                            <div>- 구매자의 책임 있는 사유로 상품 등이 멸실 또는 훼손된 경우 </div>
                            <div>　(단, 상품을 확인하기 위해 포장 등을 훼손한 경우는 제외)</div>
                            <div>- 포장을 개봉하였으나 포장이 훼손되어 상품가치가 현저히 훼손된 경우 </div>
                            <div>　(예:화장품,식품,향수류,음반 등) </div>
                            <div>- 고객님의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 </div>
                            <div>　단, 화장품등의 경우 시용제품을 제공한 경우에 한 합니다.</div>
                            <div>- 시간의 경과에 의하여 재판매가 곤란할 정도로 상품등의 가치가 현저히 감소한 경우</div>
                            <div>- 복제가 가능한 상품등의 포장을 훼손한 경우</div>
                            <div><br></div><div>* 넥카라 특성상 착용 제품은 교환, 반품 불가합니다. (미착용 상품 가능)</div>
                            <div>* 넥카라의 경우 교환, 반품된 제품은 전량 폐기되어 재판매 불가합니다. (위생 문제)</div>
                            <div>* 고객의 단순 변심으로 반품을 하실 경우 상품 반송 비용은 고객님께서 부담하셔야 합니다.</div></div>						                        </div>
                    </li>
                    <li>
                        <div class="accordionButton on">서비스문의</div>
                        <div class="accordionContent" >
                            홈페이지 내 게시판문의로 문의 해주시면 빠르게 답변 드리겠습니다. 
                        </div>
                    </li>
                </ul>
    </div>

</body>
</html>
