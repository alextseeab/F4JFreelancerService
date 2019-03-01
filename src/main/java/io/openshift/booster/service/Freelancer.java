package io.openshift.booster.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="freelancers")
public class Freelancer {
	@Id
//	@GeneratedValue
	@Column(name="freelancer_id")
	private Integer freelancerId;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email_address")
	private String emailAddress;
	@Column(name="list_of_skills")
	private String listOfSkills;
	
	public Freelancer() {
		
	}
	public Freelancer(Integer freelancerId, String firstName, String lastName, String emailAddress,
			String listOfSkills) {
		super();
		this.freelancerId = freelancerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.listOfSkills = listOfSkills;
	}
	public Integer getFreelancerId() {
		return freelancerId;
	}
	public void setFreelancerId(Integer freelancerId) {
		this.freelancerId = freelancerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getListOfSkills() {
		return listOfSkills;
	}
	public void setListOfSkills(String listOfSkills) {
		this.listOfSkills = listOfSkills;
	}

}
