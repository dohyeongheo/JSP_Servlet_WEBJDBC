package com.example;

public class MemberVO {

	// �ܺο��� �ʵ尪 ������ ��
	// getter, setter ���ؼ� �����ϵ���
	// private ���������� ���

	private String id;
	private String pw;
	private String nick;

	// id, pw�� ���� �� �ִ� MemberVO ��ü ����
	// Constructor using Fields �̿� ��ü ����
	public MemberVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	// id, pw, nick�� ���� �� �ִ� MemberVO ��ü ����
	// Constructor using Fields �̿� ��ü ����

	public MemberVO(String id, String pw, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}

	// getter , setter �޼ҵ� ����

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	// ��ü�� ����� �ʵ尪���� ����ؼ� Ȯ���ϴ� �뵵
	// to string
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", nick=" + nick + "]";
	}

}
