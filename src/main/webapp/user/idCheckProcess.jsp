<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	String chk_id = request.getParameter("id");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select * from user where id=?";
	
	try{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/sdt22kp");
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, chk_id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=no");
		}else{
			response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=yes");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>