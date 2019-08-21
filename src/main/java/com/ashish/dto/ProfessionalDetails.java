package com.ashish.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="professionaldetails")
public class ProfessionalDetails
{
	@Id
	@Column(name="applicant_username")
	private String username;
	
	@Column(name="current_salary")
	@DecimalMin("0.00")
	private double ctc;
	
	@Column(name="total_expericence")
	@DecimalMin("0.00")
	private double experience;
	
	@Column(name="skills")
	@NotBlank(message = "Skills should not be blank.")
	private String skills;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="applicant_username",referencedColumnName="username",nullable=false)
	Users user;
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getCtc() {
		return ctc;
	}
	public void setCtc(double ctc) {
		this.ctc = ctc;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
}
