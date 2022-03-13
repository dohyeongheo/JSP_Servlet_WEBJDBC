package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/joinCon22")
public class joinCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post방식 인코딩
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nick = request.getParameter("nick");

		// conn과 psmt 전역변수로 선언
		Connection conn = null;
		PreparedStatement psmt = null;

		// 데이터베이스 연결하기 전에 필요한 것 : 예외처리 try ~ catch
		try {
			// 데이터베이스 연결 과정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 프로젝트때 받은 주소 입력
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";

			conn = DriverManager.getConnection(url, dbid, dbpw);

			if (conn != null) {
				System.out.println("데이터베이스 연결 성공");
			} else {
				System.out.println("연결 실패");
			}

			// sql문 준비 및 실행
			// 물음표 = 바인드 변수
			String sql = "insert into web_member values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			// 0부터 시작이 아니라 1부터 순서대로 들어간다
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);

//    	  psmt.executeUpdate();  : insert, update, delete문 사용, 테이블 변화가 있음
//    	  psmt.executeQuery() : select : 테이블 변화가 없음
			int cnt = psmt.executeUpdate(); // 처리된 행의 값이 int형으로 리턴
			// cnt : 처리된 row값이 리턴된 값

			// 실행이 성공하였다면 회원가입 성공 메시지 출력
			if (cnt > 0) {
				System.out.println("회원가입 성공");
			} else {
				System.out.println("회원가입 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// 오류코드 출력

		} finally {
			// 항상 수행해야 할 것
			// 연결 끊기 close()
			// 열었던 순서와 반대로 마지막에 열었던 것을 먼저 닫음
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}