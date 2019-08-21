package com.ashish.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="admindetails")
public class AdminDetails 
{
	@Id
	@Column(name="admin_username")
	private String username;
	
	@Column(name="admin_name")
	@NotEmpty(message = "Name should not be blank.")
	private String name;
	
	@Column(name="admin_contact")
	@NotEmpty(message = "Phone should not be blank.")
    @Size(min = 10,max = 10, message="length must be 10")
	private String contact;
	
	@Column(name="admin_email")
	@NotEmpty(message = "Email should not be blank.")
	@Pattern(message="Invalid Email",regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
