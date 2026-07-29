package com.example.finalproject.dtos;

public class LoginResponse {
	
	private String token;

	public LoginResponse(String token) {
		super();
		this.token = token;
	}

	public LoginResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + "]";
	}
}