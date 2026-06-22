package com.blockbuster.dto;

import java.util.Set;

public class SignUpRequest {

	private String email;
	private String password;
	private Set<String> listRoles;
	
	public SignUpRequest() {};
	public SignUpRequest(String email,String password,Set<String> listRoles) {
		this.email=email;
		this.password=password;
		this.listRoles=listRoles;
	}
	
	public Set<String> getListRoles() {
		return listRoles;
	}
	public void setListRoles(Set<String> listRoles) {
		this.listRoles = listRoles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
