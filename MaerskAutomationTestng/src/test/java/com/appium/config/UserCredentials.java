package com.appium.config;

public class UserCredentials {
	private String _username;
	private String _password;

	public UserCredentials(String username, String password) {
		this.set_username(username);
		this.set_password(password);
	}

	public String get_username() {
		return _username;
	}

	private void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	private void set_password(String _password) {
		this._password = _password;
	}

}
