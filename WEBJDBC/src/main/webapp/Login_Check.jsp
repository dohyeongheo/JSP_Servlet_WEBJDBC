<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- �Ѿ�� id�� 'smart' �̰� pw�� '123'�̸� �α��� ���� -->
<% 
// post ����� ���ڵ�
request.setCharacterEncoding("euc-kr");
// id�� pw�� �Ķ���� ����
String id = request.getParameter("id");
String pw = request.getParameter("pw");

if (id.equals("smart") && pw.equals("123")) {
	response.sendRedirect("loginS.jsp");
}
else response.sendRedirect("loginF.jsp");



// �α��� ���� -> loginS.jsp�� ������ �̵�
// �α��� ���� -> loginF.jsp�� ������ �̵�
%>

</body>
</html>