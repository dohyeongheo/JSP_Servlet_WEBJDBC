<%@page import="com.example.MemberVO"%>
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
	// (�ڽ�Ŭ����Ÿ��)�θ�Ÿ���� ������
	// �ڽ�Ŭ���� ���� String���� �ٿ�ĳ����
	String id = (String) session.getAttribute("id");
	
	// �ڽ�Ŭ���� ���� MemberVO�� �ٿ�ĳ����
	MemberVO vo = (MemberVO)session.getAttribute("vo");
	
	if(vo != null){
		System.out.print("���� �α��� �� ����� ������ : " + vo.getId() + vo.getPw() + vo.getNick());
	} else {
		System.out.print("�α��� ���°� �ƴմϴ�.");
	}

	if (id != null) {
		out.print(id + "�� ȯ���մϴ�.");
	} else {
		out.print("�α��� ���°� �ƴմϴ�.");
	}
	%>

	<h1>���� ������</h1>
	<a href="join.html">ȸ������ ������</a>
	<br>
	<a href="login.html">ȸ���α��� ������</a>
	<br>
	<a href="logout.jsp">�α׾ƿ� ������</a>
	<br>
	<a href="delete.html">ȸ������ ������</a>
	<br>
	<a href="update.html">ȸ������ ������</a>
	<br>
	<a href="selectAll.jsp">ȸ����ü�˻� ������</a>
</body>
</html>