package me.develop_han.TDD_start.mock.stub.domain;

public class User {
	private String id;
	private String pw;
	private String email;

	public User(String id, String pw, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getEmail() {
		return email;
	}
}
