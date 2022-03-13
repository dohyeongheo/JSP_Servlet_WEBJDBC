<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 넘어온 id가 'smart' 이고 pw가 '123'이면 로그인 성공 -->
<% 
// post 방식의 인코딩
request.setCharacterEncoding("euc-kr");
// id와 pw를 파라미터 수집
String id = request.getParameter("id");
String pw = request.getParameter("pw");

if (id.equals("smart") && pw.equals("123")) {
	response.sendRedirect("loginS.jsp");
}
else response.sendRedirect("loginF.jsp");



// 로그인 성공 -> loginS.jsp로 페이지 이동
// 로그인 실패 -> loginF.jsp로 페이지 이동
%>

</body>
</html>