package com.ashish.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="educationaldetails")
public class EducationalDetails 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int row_no;
	
	@Column(name="applicant_username")
	private String username;
	
	@Column(name="degree_name")
	@NotBlank(message="degree should not be empty")
	private String degree;
	
	@Column(name="institute_name")
	@NotBlank(message="institute should not be empty")
	private String institute;
	
	@Column(name="year_of_passing")
	@NotBlank(message="It should not be empty")
	private String yop;
	
	@Column(name="marks_or_cgpa")
	@DecimalMin("0.00")
	@DecimalMax("100.00")
	private double marks;
	
	public int getRow_no() {
		return row_no;
	}
	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getYop() {
		return yop;
	}
	public void setYop(String yop) {
		this.yop = yop;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	
}
