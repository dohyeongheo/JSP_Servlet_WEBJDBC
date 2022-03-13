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

		// post��� ���ڵ�
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// �������� ����
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Connection conn = null;
		String nick = null;
		String uid = null;
		String upw = null;

		try {
			// driver ��� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";

			conn = DriverManager.getConnection(url, dbid, dbpw);

			if (conn != null) {
				System.out.println("DB ���� ����!");
			} else {
				System.out.println("DB ���� ����!");
			}

			// Sql���� ���ؼ� id, pw ��ȸ
			String sql = "select * from web_member where id = ? and pw =?";

			// �ۼ��� sql���� db�� ����
			// ����� ������ �Ǹ� PreparedStatement ��ü�� ��ȯ
			psmt = conn.prepareStatement(sql);

			// ?�� �� ä���(���ε� ����)
			psmt.setString(1, id);
			psmt.setString(2, pw);

			// sql�� ����
			// executeUpdate -> select ������ ������ ��� ��� (db�� ��ȭ�� ���� ���)
			// int ���·� ���� (��ȭ�� ���� ���� ���� ����)
			// executeQuery -> select (db�� ��ȭ x)
			// resultSet ���·� ����
			rs = psmt.executeQuery();

			if (rs.next()) {
				uid = rs.getString("id");
				upw = rs.getString("pw");
				nick = rs.getString("nick");
				System.out.println(nick);

				// ������ ����ϱ� ���ؼ� Servlet������
				// session ��ü ����
				// ����Ÿ���� httpSession
				HttpSession session = request.getSession();

				// MemberVO��ü�� ������ �̵� �Ǵ� ���ǿ� �����ϱ�
				MemberVO vo = new MemberVO(uid, upw);
				session.setAttribute("vo", vo);

				// session.setAttribute("id", uid);
				// session.setAttribute("pw", upw);

				// ���ǿ� id�� ��� ������ �̵��ϱ�
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

		// �α��� ���� ������ �̵�
		// �α��� ���� ������ �̵�

//		if (nick != null) {
//			System.out.println("�α��� ����");
//			response.sendRedirect("loginS.jsp?nick=" + URLEncoder.encode(nick, "EUC-KR"));
//		} else {
//			System.out.println("�α��� ����");
//			response.sendRedirect("loginF.jsp?nick=" + nick);
//		}

	}
}