package com.blockbuster.dto;

public class GetUserResponse {

	private String email;
	
	public GetUserResponse() {};
	public GetUserResponse(String email) {
		this.setEmail(email);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
