<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
.container{
	text-align: center
}
.table{
	border: 1px solid gray;
	width: 400px;
	height: 150px;
	margin: auto;
}
</style>
</head>
<body>
<div class="container">
<h2>LOGIN</h2>	
<form class="loginform" action="userLoginAction.u" method="post">
	<div class="table">
		<div class="row">
			<div class="열1"style="padding: 7px"><b>아이디</b></div> <div class="열1"><input type="text" id="id" name="id"></div>
			<div class="열2"style="padding: 7px"><b>비밀번호</b></div> <div class="열2"><input type="password" id="user_pass" name="user_pass"></div>
			<div class="열3" style="padding: 7px"><input type="submit" value="로그인">&nbsp;&nbsp;<input type="button" value="회원가입" onclick="window.location.href='userJoinForm.u'"></div>			
		</div>
	</div>
</form>
</div>
</body>
</html>