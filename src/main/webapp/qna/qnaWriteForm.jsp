<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<form action="qnaWriteAction.q">
  <input type="hidden" id="qna_num" name="qna_num">
  <input type="hidden" id="id" name="id">
  <input type="hidden" id="qna_date" name="qna_date">
  <input type="hidden" id="qna_answer" name="qna_answer">
  <input type="hidden" id="qna_reply" name="qna_reply">
  <input type="hidden" id="p_num" name="p_num" value="${p_num }">
  
  <label for="qna_subject">제목:</label>
  <input type="text" id="qna_subject" name="qna_subject" required><br>

 
  

  <label for="qna_content">내용:</label><br>
  <textarea id="qna_content" name="qna_content" rows="4" cols="50" required></textarea><br>


  <input type="submit" value="문의 작성" >
</form>
</body>
</html>