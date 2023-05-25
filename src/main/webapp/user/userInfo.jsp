<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<style>
.containerInfo {
  text-align: center;
}

.tableInfo {
  text-align: center;
}

.yst {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.column {
  margin: 10px;
}

.label {
  font-weight: bold;
}

.value {
  margin-left: 5px;
}

.link {
  margin-top: 10px;
}

.link a {
  margin-right: 10px;
}

.info-table {

  border-radius: 5px;
  padding: 20px;
}

.info-table .label {
  font-weight: bold;
  margin-right: 5px;
}

.info-table .value {
  color: #495057;
}
td{
border: 1px solid gray;
padding: 10px;
border-radius: 10px;
background-color: aliceblue;
}
</style>
</head>
<body>
<div class="containerInfo">
  <div class="tableInfo">
    <div class="yst">
      <div class="column">
      <h5>회원 정보</h5>
        <table class="info-table">
          <tr>
            <td><span class="label">아이디</span></td>
            <td><span class="value">${user.id}</span></td>
          </tr>
          <tr>
            <td><span class="label">비밀번호</span></td>
            <td><span class="value">${user.user_pass}</span></td>
          </tr>
          <tr>
            <td><span class="label">이름</span></td>
            <td><span class="value">${user.user_name}</span></td>
          </tr>
          <tr>
            <td><span class="label">우편번호</span></td>
            <td><span class="value">${user.user_zipcode}</span></td>
          </tr>
          <tr>
            <td><span class="label">주소</span></td>
            <td><span class="value">${user.user_addr}</span></td>
          </tr>
          <tr>
            <td><span class="label">상세주소</span></td>
            <td><span class="value">${user.user_addr2}</span></td>
          </tr>
          <tr>
            <td><span class="label">전화번호</span></td>
            <td><span class="value">${user.user_phone}</span></td>
          </tr>
        </table>
      </div>
    </div>
    <div class="yst">
      <div class="column link">
        <a href="userModifyForm.u?id=${user.id}" class="btn btn-primary">수정</a>
        <a href="deliveryListAction.del?id=${user.id}" class="btn btn-primary">배송지관리</a>
        <a href="#" class="btn btn-danger" onclick="confirmDelete('${user.id}', event)">탈퇴</a>
      </div>
    </div>
  </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
function confirmDelete(userId, event) {
  event.preventDefault(); // 이벤트의 기본 동작을 취소

  if (confirm("정말 탈퇴하시겠습니까?")) {
    location.href = "userByeAction.u?id=" + userId;
  }
}
</script>
</body>
</html>