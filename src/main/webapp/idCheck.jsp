<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="dao.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/cb777d7294.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
<style>
/* cdn import */
@font-face {
  font-family: 'Godo';
  font-style: normal;
  font-weight: 400;
  src: url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2') format('woff2'), url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff') format('woff');
}

@import url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2') format('woff2'), url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff') format('woff');
body {
    font-family: 'Godo';
    
    
}

.container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}
.button {
font-family: 'Godo';
padding: 4px 9px;
font-size: 13px;
border-radius: 5px;
background-color: #27737c;
color: #fff;
border: none;
cursor: pointer;
}

.button:hover {
background-color: #1b4e54;
}
</style>

</head>
<body>
<div class="container">

  <%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id"); 

	UserDAO userDAO = UserDAO.getInstance();

int result = userDAO.joinIdCheck(id);
if (result == 1) {
  out.print("사용 가능한 아이디입니다.");
%>

<%
} else if (result == 0) {
  out.print("중복된 아이디입니다.");
} else {
  out.print("에러(-1)");
}
%>
<br><br>
  <fieldset>
    <form action="idCheck.jsp" method="post" name="wfr">
    <br>
      ID : <input type="text" name="id" value="<%=id%>">
      <input type="submit" value="중복 확인" class="button">
      <br><br><hr>
<input type="button" value="아이디 사용하기" onclick="result();" class="button">
    </form>
  </fieldset>
</div>

<script type="text/javascript">
  function result() {
    opener.document.joinform.id.value = document.wfr.id.value;
    opener.document.joinform.id.readOnly = true;
    window.close();
  }
</script>
</body>
</html>