package com.ashish.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="employer")
public class Employer 
{
	@Id
	@Column(name="company_username")
	private String username;
	
	@Column(name="company_name")
	@NotEmpty(message = "Company should not be blank.")
	private String company_name;
	
	@Column(name="company_email")
	@NotEmpty(message = "Email should not be blank.")
	@Pattern(message="Invalid Email",regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Column(name="company_webisite")
	@NotEmpty(message = "Website should not be blank.")
	private String website;
	
	@Column(name="company_contact_person")
	@NotEmpty(message = "Name should not be blank.")
	private String contact_person_name;
	
	@Column(name="contact_person_designation")
	@NotEmpty(message = "Designation should not be blank.")
	private String designation;
	
	@Column(name="company_contact_number")
	@NotEmpty(message = "Phone should not be blank.")
    @Size(min = 10,max = 10, message="length must be 10")
	private String contact;
	
	@Column(name="industry_type")
	@NotEmpty(message = "It should not be blank.")
	private String industry;
	
	@NotEmpty(message = "address should not be blank.")
	@Column(name="company_address")
	private String office_address;
	
	@Temporal(TemporalType.DATE)
	@Column(name="company_reg_date")
	private Date reg_date;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="company_username",referencedColumnName="username",nullable=false)
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContact_person_name() {
		return contact_person_name;
	}
	public void setContact_person_name(String contact_person_name) {
		this.contact_person_name = contact_person_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getOffice_address() {
		return office_address;
	}
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
