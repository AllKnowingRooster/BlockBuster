package com.blockbuster.dto;

import java.util.Date;

public class SignInResponse {
	private String email;
	private Date loginDate;
	private String accessToken;
	private String refreshToken;
	
	public SignInResponse() {};
	public SignInResponse(String email,Date loginDate,String accessToken,String refreshToken) {
		this.email=email;
		this.loginDate=loginDate;
		this.accessToken=accessToken;
		this.refreshToken=refreshToken;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
