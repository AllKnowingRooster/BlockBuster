package com.blockbuster.authenticationService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@IdClass(UserRolesId.class)
@Table(name="userroles",uniqueConstraints= {
		@UniqueConstraint(columnNames= {"userId","roleId"})
})
public class UserRoles {

	@Id
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="userid",nullable=false,unique=false)
	private Users user;
	
	@Id
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="roleid",nullable=false,unique=false)
	private Roles role;
	
	public void setUser(Users user) {
		this.user=user;
	}
	
	public Users getUser() {
		return this.user;
	}
	
	public void setRoles(Roles role) {
		this.role=role;
	}
	
	public Roles getRoles() {
		return this.role;
	}
	
}
