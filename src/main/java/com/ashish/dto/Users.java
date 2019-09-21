package com.ashish.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Users
{
	@Id
	@NotEmpty(message = "username should not be blank.")
	private String username;
	
	@NotEmpty(message = "Password should not be blank.")
	@Pattern(message="Password is not in the requested format",regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" )
	private String password;
	
	private Integer roleId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="users_education",joinColumns= { @JoinColumn(name="username")},inverseJoinColumns= {@JoinColumn(name="row_no")}
	)
	private Set<EducationalDetails> education=new HashSet<EducationalDetails>(3);
	
	public Set<EducationalDetails> getEducation() {
		return education;
	}
	public void setEducation(Set<EducationalDetails> education) {
		this.education = education;
	}
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
