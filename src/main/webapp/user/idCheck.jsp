<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import= "javax.naming.Context" %>
<%@ page import= "javax.naming.InitialContext" %>
<%@ page import= "javax.naming.NamingException" %>
<%@ page import= "javax.sql.DataSource" %>

<%
String id = request.getParameter("id");
String useble = "yes";

try {
// JDBC를 이용해 데이터베이스에서 아이디 중복여부 검사
Context initContext = new InitialContext();
Context envContext = (Context)initContext.lookup("java:/comp/env");
DataSource ds = (DataSource)envContext.lookup("jdbc/sdt22kp");
Connection conn = ds.getConnection();
String sql = "SELECT COUNT(*) FROM user WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, id);
ResultSet rs = pstmt.executeQuery();
rs.next();
int count = rs.getInt(1);
if (count > 0) {
useble = "no";
}
} catch (Exception e) {
e.printStackTrace();
useble = "error";
}

// JSON 형태로 결과를 반환
String result = "{\"chk_id\":\"" + id + "\", \"useble\":\"" + useble + "\"}";
response.setContentType("application/json; charset=UTF-8");
response.getWriter().write(result);
%>