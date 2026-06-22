package com.blockbuster.authenticationService.entity;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class UserRolesId implements Serializable {
	
	private long user;
	private long role;
	
	public UserRolesId() {};
	
	@Override
	public boolean equals(Object o) {
		
		if(!(o instanceof UserRolesId)) {
			return false;
		}
		
		UserRolesId value=(UserRolesId)o;
		
		return Objects.equals(this.user, value.user)&&Objects.equals(this.role, value.role);
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(this.user,this.role);
	}
}
