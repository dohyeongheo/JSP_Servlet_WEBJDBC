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

import org.eclipse.jdt.internal.compiler.batch.Main;

@WebServlet("/updateCon")
public class updateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post��� ���ڵ�
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");
		String select = request.getParameter("select");
		String data = request.getParameter("data");

		// conn�� psmt ���������� ����
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

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

			// ���� : pw�̸� �Ǵ� nick �̸�
			// ���̺��� �÷����� ���ε庯���� �� �� ����.

			if (select.equals("pw")) {
				sql = "update web_member set pw=? where id =?";
			} else {
				sql = "update web_member set nick=? where id =?";
			}

			psmt = conn.prepareStatement(sql);
			// 0���� ������ �ƴ϶� 1���� ������� ����
			psmt.setString(1, data);
			psmt.setString(2, id);

//    	  psmt.executeUpdate();  : insert, update, delete�� ���, ���̺� ��ȭ�� ����
//    	  psmt.executeQuery() : select : ���̺� ��ȭ�� ����
			int cnt = psmt.executeUpdate(); // ó���� ���� ���� int������ ����
			// cnt : ó���� row���� ���ϵ� ��

			// ������ �����Ͽ��ٸ� ȸ������ ���� �޽��� ���
			if (cnt > 0) {
				System.out.println("ȸ������ ����");
				// ������ �����ϸ� Main.html�� �̵��ϱ�
				// ������ �̵� sendRedirect
				response.sendRedirect("main.html");
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
