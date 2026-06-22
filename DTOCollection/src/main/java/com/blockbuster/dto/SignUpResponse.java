package com.blockbuster.dto;

public class SignUpResponse {
	private String email;

	public SignUpResponse() {};
	public SignUpResponse(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
