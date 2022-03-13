<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
// 세션 삭제
session.removeAttribute("id");

// 페이지 이동
response.sendRedirect("Main.jsp");


%>

</body>
</html>