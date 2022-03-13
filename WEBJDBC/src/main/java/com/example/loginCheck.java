package com.example;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;
import org.eclipse.jdt.internal.compiler.batch.Main;

@WebServlet("/loginCheck22")
public class loginCheck extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post방식 인코딩
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 전역변수 선언
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Connection conn = null;
		String nick = null;
		String uid = null;
		String upw = null;

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
			String sql = "select * from web_member where id = ? and pw =?";

			// 작성한 sql문을 db에 전달
			// 제대로 전달이 되면 PreparedStatement 객체로 반환
			psmt = conn.prepareStatement(sql);

			// ?에 값 채우기(바인드 변수)
			psmt.setString(1, id);
			psmt.setString(2, pw);

			// sql문 실행
			// executeUpdate -> select 구문을 제외한 모든 경우 (db에 변화가 생길 경우)
			// int 형태로 리턴 (변화가 생긴 행의 개수 리턴)
			// executeQuery -> select (db에 변화 x)
			// resultSet 형태로 리턴
			rs = psmt.executeQuery();

			if (rs.next()) {
				uid = rs.getString("id");
				upw = rs.getString("pw");
				nick = rs.getString("nick");
				System.out.println(nick);

				// 세션을 사용하기 위해서 Servlet에서는
				// session 객체 생성
				// 리턴타입이 httpSession
				HttpSession session = request.getSession();

				// MemberVO객체로 데이터 이동 또는 세션에 저장하기
				MemberVO vo = new MemberVO(uid, upw);
				session.setAttribute("vo", vo);

				// session.setAttribute("id", uid);
				// session.setAttribute("pw", upw);

				// 세션에 id를 담고 페이지 이동하기
				response.sendRedirect("Main.jsp");

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

		// 로그인 성공 페이지 이동
		// 로그인 실패 페이지 이동

//		if (nick != null) {
//			System.out.println("로그인 성공");
//			response.sendRedirect("loginS.jsp?nick=" + URLEncoder.encode(nick, "EUC-KR"));
//		} else {
//			System.out.println("로그인 실패");
//			response.sendRedirect("loginF.jsp?nick=" + nick);
//		}

	}
}