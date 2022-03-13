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
	// (자식클래스타입)부모타입의 데이터
	// 자식클래스 변수 String으로 다운캐스팅
	String id = (String) session.getAttribute("id");
	
	// 자식클래스 변수 MemberVO로 다운캐스팅
	MemberVO vo = (MemberVO)session.getAttribute("vo");
	
	if(vo != null){
		System.out.print("현재 로그인 된 사람의 정보는 : " + vo.getId() + vo.getPw() + vo.getNick());
	} else {
		System.out.print("로그인 상태가 아닙니다.");
	}

	if (id != null) {
		out.print(id + "님 환영합니다.");
	} else {
		out.print("로그인 상태가 아닙니다.");
	}
	%>

	<h1>메인 페이지</h1>
	<a href="join.html">회원가입 페이지</a>
	<br>
	<a href="login.html">회원로그인 페이지</a>
	<br>
	<a href="logout.jsp">로그아웃 페이지</a>
	<br>
	<a href="delete.html">회원삭제 페이지</a>
	<br>
	<a href="update.html">회원수정 페이지</a>
	<br>
	<a href="selectAll.jsp">회원전체검색 페이지</a>
</body>
</html>