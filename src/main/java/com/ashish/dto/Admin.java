package com.ashish.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="admin")
public class Admin 
{
	@Id
	@Column(name="admin_username")
	@NotEmpty(message = "username should not be blank.")
	private String username;
	
	@Column(name="admin_password")
	@NotEmpty(message = "Password should not be blank.")
	@Pattern(message="Password is not in the requested format",regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" )
	private String password;
	
	@Column(name="isfirstlogin")
	private boolean firstlogin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getFirstlogin() {
		return firstlogin;
	}
	public void setFirstlogin(boolean firstlogin) {
		this.firstlogin = firstlogin;
	}
	
}
