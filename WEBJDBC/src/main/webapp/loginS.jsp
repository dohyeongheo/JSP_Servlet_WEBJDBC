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
	String nick = request.getParameter("nick");
	%>
	<h1>
		<%=nick%>��, �α��ο� �����Ͽ����ϴ�. :)
	</h1>
</body>
</html>