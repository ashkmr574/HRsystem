package com.ashish.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="jobdetails")
public class JobDetails
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int job_id;
	
	@Column(name="company_username")
	private String username;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="no_of_openings")
	@Min(value=1)
	private int openings;
	
	@Column(name="designation")
	@NotBlank(message="it should not be blank")
	private String post;
	
	@Column(name="min_experience")
	@Min(value=0)
	private int min_experience;
	
	@Column(name="salary")
	@DecimalMin("0.00")
	private float ctc;
	
	@Column(name="joblocation")
	@NotBlank(message="it should not be blank")
	private String job_location;
	
	@Column(name="required_skills")
	@NotBlank(message="it should not be blank")
	private String req_skills;
	
	@Column(name="job_description")
	@NotBlank(message="it should not be blank")
	private String job_description;
	
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "It should not be blank.")
	@Column(name="job_expiry_date")
	private Date last_apply_date;
	
	@Column(name="recruitment_process", length=65535,columnDefinition="TEXT")
	@NotBlank(message="it should not be blank")
	private String recruitment_process;
	
	public String getRecruitment_process() {
		return recruitment_process;
	}
	public void setRecruitment_process(String recruitment_process) {
		this.recruitment_process = recruitment_process;
	}
	public Date getLast_apply_date() {
		return last_apply_date;
	}
	public void setLast_apply_date(Date last_apply_date) {
		this.last_apply_date = last_apply_date;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getOpenings() {
		return openings;
	}
	public void setOpenings(int openings) {
		this.openings = openings;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public int getMin_experience() {
		return min_experience;
	}
	public void setMin_experience(int min_experience) {
		this.min_experience = min_experience;
	}
	public float getCtc() {
		return ctc;
	}
	public void setCtc(float ctc) {
		this.ctc = ctc;
	}
	public String getJob_location() {
		return job_location;
	}
	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}
	public String getReq_skills() {
		return req_skills;
	}
	public void setReq_skills(String req_skills) {
		this.req_skills = req_skills;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

}
