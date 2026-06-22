package com.blockbuster.dto;

public class RefreshResponse {
	private String accessToken;
	private String refreshToken;
	
	public RefreshResponse() {};
	public RefreshResponse(String accessToken,String refreshToken) {
		this.setAccessToken(accessToken);
		this.setRefreshToken(refreshToken);
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
