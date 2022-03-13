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

		// post��� ���ڵ�
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nick = request.getParameter("nick");

		// conn�� psmt ���������� ����
		Connection conn = null;
		PreparedStatement psmt = null;

		// �����ͺ��̽� �����ϱ� ���� �ʿ��� �� : ����ó�� try ~ catch
		try {
			// �����ͺ��̽� ���� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ������Ʈ�� ���� �ּ� �Է�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";

			conn = DriverManager.getConnection(url, dbid, dbpw);

			if (conn != null) {
				System.out.println("�����ͺ��̽� ���� ����");
			} else {
				System.out.println("���� ����");
			}

			// sql�� �غ� �� ����
			// ����ǥ = ���ε� ����
			String sql = "insert into web_member values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			// 0���� ������ �ƴ϶� 1���� ������� ����
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);

//    	  psmt.executeUpdate();  : insert, update, delete�� ���, ���̺� ��ȭ�� ����
//    	  psmt.executeQuery() : select : ���̺� ��ȭ�� ����
			int cnt = psmt.executeUpdate(); // ó���� ���� ���� int������ ����
			// cnt : ó���� row���� ���ϵ� ��

			// ������ �����Ͽ��ٸ� ȸ������ ���� �޽��� ���
			if (cnt > 0) {
				System.out.println("ȸ������ ����");
			} else {
				System.out.println("ȸ������ ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// �����ڵ� ���

		} finally {
			// �׻� �����ؾ� �� ��
			// ���� ���� close()
			// ������ ������ �ݴ�� �������� ������ ���� ���� ����
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