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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="personaldetails")
public class PersonalDetails 
{
	@Id
	@Column(name="applicant_username")
	private String username;
	
	@Column(name="applicant_name")
	@NotBlank(message = "Name should not be blank.")
	private String name;
	
	@Column(name="e_mail")
	@NotBlank(message = "Email should not be blank.")
	@Pattern(message="Invalid Email",regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Column(name="contact_no")
	@NotBlank(message = "Phone should not be blank.")
    @Size(min = 10,max = 10, message="length must be 10")
	private String contact;
	
	@Column(name="gender")
	@NotBlank(message = "Gender should not be blank.")
	private String gender;
	
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "It should not be blank.")
	@Column(name="date_of_birth")
	private Date dob;
	
	@Column(name="address_line1")
	@NotBlank(message = "Address should not be blank.")
	private String address1;
	
	@Column(name="address_line2")
	@NotBlank(message = "Address should not be blank.")
	private String address2;
	
	@Column(name="pincode")
	@NotBlank(message = "pincode should not be blank.")
	@Size(min=6,max=6,message="length must be 6")
	private String pincode;
	
	@Column(name="city")
	@NotBlank(message = "City should not be blank.")
	private String city;
	
	@Column(name="state")
	@NotBlank(message = "State should not be blank.")
	private String state;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return name + "," + email + "," + contact + "," + gender
				+ "," + dob + "," + address1 + "," + address2 + "," + pincode
				+ "," + city + "," + state;
	}
	
	
}
