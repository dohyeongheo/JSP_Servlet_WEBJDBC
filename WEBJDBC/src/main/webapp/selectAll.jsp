<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>회원전체검색 페이지</h1>
<table border=1, width=300px;>
	<%
	// 전역변수 선언
	ResultSet rs = null;
	PreparedStatement psmt = null;
	Connection conn = null;

	try {
		// driver 경로 지정
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String dbid = "hr";
		String dbpw = "hr";

		conn = DriverManager.getConnection(url, dbid, dbpw);

		if (conn != null) {
			System.out.println("DB 연결 성공!");
		} else {
			System.out.println("DB 연결 실패!");
		}

		// Sql문을 통해서 id, pw 조회
		String sql = "select * from web_member";

		// 작성한 sql문을 db에 전달
		// 제대로 전달이 되면 PreparedStatement 객체로 반환
		psmt = conn.prepareStatement(sql);

		// 작성한 sql문을 db에 전달
		
		rs = psmt.executeQuery();

		// 받아온 값도 x -> 바인드 변수에 넣어줄 값도 필요 X
		// psmt.setString(int번째, "값") -> 필요 X
		
		
		// rs.next()는 T,F로 반환을 하기때문에 WHILE문 안에 집에 넣음 
		while (rs.next()) {
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String nick = rs.getString("nick");
			
			// jsp에서 출력할때 -> out 객체, 표현식
			out.print("<tr>");
			out.print("<td>"+ id +"</td>");
			out.print("<td>"+ pw +"</td>");
			out.print("<td>"+ nick +"</td>");
			out.print("</tr>");
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
		rs.close();
			if (psmt != null)
		psmt.close();
			if (conn != null)
		conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	%>
	</table>
</body>
</html>