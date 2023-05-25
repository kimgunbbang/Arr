<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://assets4.cre.ma/widgets/assets/pc-c9334d1331a67b88a5bbb28c4c16b01ef5184ab1c165983eb0e8207debdf3b2d.css" rel="stylesheet" type="text/css">
<html>
<style>
  .qna-board {
    display: flex;
    flex-direction: column;
  }
  
  .qna {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
  }
  
  .qna-summary {
    cursor: pointer;
    background-color: #ecfdf3;
    padding: 10px;
    margin-bottom: 5px;
    border-radius: 20px;
    border: 1px solid lavender;
  }
  
  .qna-content {
    display: none;
    background-color: #ecfdf3;
    padding: 10px;
  }
  
  .qna-summary-info {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  
  .qna-summary-info div {
    margin-right: 10px;
  }
  
  .answer-form textarea {
    width: 100%;
    height: 100px;
    resize: vertical;
    margin-bottom: 5px;
  }
  
  .answer-form button {
    padding: 5px 10px;
    background-color: #f33;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .answer-form button:hover {
    background-color: #d00;
  }
  
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/adminCheck.jsp"></jsp:include>
<body>
<div id="qnaSection" style="text-align: center;"> <h3><b>Q&A</b></h3> </div>
<div class="qna-board">
  <c:forEach var="qna" items="${qnaList}">
      <div class="qnaForm" style="width: 800px; margin: auto;">
        <div class="qna-summary" onclick="toggleqnaContent(this)">
          <div class="qna-summary-info">
            <div class="qna_num" style="width: 50px; text-align: center;">${qna.qna_num}</div>
            <div class="qna_subject" style="width: 400px; font-weight: bold; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${qna.qna_subject}</div>
            <div class="id" style="width: 100px; text-align: center;">${qna.id}</div>
            <div class="qna_date" style="width: 100px; text-align: center;">${qna.qna_date}</div>
            <c:choose>
              <c:when test="${qna.qna_answer == '0'}">
                <div class="qna_answer" style="width: 100px; text-align: center;">답변대기</div>
              </c:when>
              <c:when test="${qna.qna_answer == '1'}">
                <div class="qna_answer" style="width: 100px; text-align: center;">답변완료▼</div>
              </c:when>
              <c:otherwise>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="qna_content" style="display: none;">
          <div class="qna-text" style="height: 150px; padding: 10px; border: 1px solid #ccc; border-radius: 10px;">
          <div class="col" style="float:left; display: inline;">
          	<h6><b>${qna.qna_subject}</b></h6>
            <small>문의 내용</small>
            ${qna.qna_content}
          </div>
          <div class="col" style="text-align: right;">
            <c:forEach var="img" items="${imgList }">
            	<c:if test="${qna.p_num == img.p_num }">
            		<div style="float: right;">${img.p_name}</div><br>
	            	<img src="${pageContext.request.contextPath }/images/${img.p_image}" style="width:8%; float: right;">
	            	
	            </c:if>
            </c:forEach>
          </div>
	      <br>
          </div>
          <%-- 답변이 있는 경우에만 답변을 표시 --%>
          <c:if test="${not empty qna.qna_reply}">
            <div class="answer" style="margin-top: 10px; padding: 10px; border: 1px solid #ccc; border-radius: 10px;">
              <small>답변</small><br>
              <small>${qna.qna_reply}</small>
            </div>
            <br>
          </c:if>
          <%-- 답변을 작성할 수 있는 폼 --%>
          <c:if test="${sessionScope.id eq 'admin' and empty qna.qna_reply }">
            <div class="answer-form" style="margin-top: 10px;">
              <form action="qnaAnswerAction.q" method="post">
              	<input type="hidden" id="p_num" name="p_num" value="${qna.p_num }"> 
              	<input type="hidden" id="qna_answer" name="qna_answer" value="${qna.qna_answer }"> 
              	<input type="hidden" id="qna_num" name="qna_num" value="${qna.qna_num }"> 
              	
                <input type="text" id="qna_reply" name="qna_reply" value="${qna.qna_reply}" placeholder="답변을 입력하세요" style="width: 100%; height: 100px; border: 1px solid #ccc;">
                <!-- <textarea name="answerText" placeholder="답변을 입력하세요" style="width: 100%; height: 100px; border: 1px solid #ccc;"></textarea> -->
                <button type="submit" style="margin-top: 10px; padding: 5px 10px; border-radius: 5px; background-color: #007bff; color: #fff; border: none; cursor: pointer;">답변 작성</button>
              </form>
            </div>
          </c:if>
        </div>
      </div>
      <%-- 클릭하면 게시글의 내용과 답글이 보이게끔 설정 --%>
      <script>
        function toggleqnaContent(element) {
          element.classList.toggle("active");
          var content = element.nextElementSibling;
          if (content.style.display === "block") {
            content.style.display = "none";
          } else {
            content.style.display = "block";
          }
        }
      </script>
  </c:forEach>
<section id="pageList" style="text-align: center;">
	<c:choose>
	<c:when test="${pageinfo.page<=1 }">[이전]&nbsp;</c:when>
	<c:otherwise>
			<a href="adminQnaList.ad?page=${pageinfo.page-1 }">[이전]</a>&nbsp;
	</c:otherwise>
	</c:choose>
	<c:forEach var="a" begin="${pageinfo.startpage }" end="${pageinfo.endpage }" step="1">
		<c:choose>
			<c:when test="${a==pageinfo.page }">[${a }]</c:when>
		<c:otherwise>
			<a href="adminQnaList.ad?page=${a }">[${a }]</a>&nbsp;
		</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:choose>
		<c:when test="${pageinfo.page>=pageinfo.maxpage }">[다음]</c:when>
		<c:otherwise>
			<a href="inventoryList.in?page=${pageinfo.page+1 }">[다음]</a>&nbsp;
		</c:otherwise>
	</c:choose>
</section>
</div>
</body>
</html>