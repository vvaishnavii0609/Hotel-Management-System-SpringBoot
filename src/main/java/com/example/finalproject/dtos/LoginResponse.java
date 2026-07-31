package com.example.finalproject.dtos;

public class LoginResponse {
	
	private String token;
	
	private String name;
	private String role;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginResponse(String token, String name, String role) {
		super();
		this.token = token;
		this.name=name;
		this.role=role;
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
		return "LoginResponse [token=" + token + ", name=" + name + ", role=" + role + "]";
	}


}