package com.blockbuster.authenticationService.entity;

import java.util.Set;

import com.blockbuster.enumerated.RoleName;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false,unique=true,name="rolename")
	@Enumerated(value=EnumType.STRING)
	private RoleName roleName;

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="role")
	@JsonManagedReference
	private Set<UserRoles> listUser;
	
	
	public void setRoleName(RoleName roleName) {
		this.roleName=roleName;
	}
	
	public RoleName getRoleName() {
		return this.roleName;
	}
	
	public void setListUser(Set<UserRoles> listUser) {
		this.listUser=listUser;
	}
	
	public Set<UserRoles> getListUser(){
		return this.listUser;
	}
	
}
