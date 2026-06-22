package com.blockbuster.authenticationService.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false,unique=true,name="email")
	private String email;
	
	@Column(nullable=false,unique=false,name="password")
	private String password;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="user")
	@JsonManagedReference
	private Set<UserRoles> listRoles;
	
	
	public long getId() {
		return this.id;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setListRoles(Set<UserRoles> listRoles){
		this.listRoles=listRoles;
	}
	
	public Set<UserRoles> getListRoles() {
		return this.listRoles;
	}
	
}
