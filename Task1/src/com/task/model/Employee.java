package com.task.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="FIRST_NAME")
	@NotEmpty(message = "Please enter your first name")
	private String firstName;
	
	@Column(name="LAST_NAME")
	@NotEmpty(message = "Please enter your last name")
	private String lastName;
	
	@Column(name="DOB")
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@NotNull(message="Please enter date")
//	@Pattern(regexp = "^\\d{2}-[a-zA-Z]{3}-\\d{4}$",message="Invalid date format")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="EMAIL",unique=true)
	@NotEmpty(message = "Please enter your email")
//	@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",message="Invalid email")
//	^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message="Invalid email")
	private String email;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private Department department;

	@Transient
	private String dobAsString;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setDobAsString(String dateAsDDMMYYYY) {
		this.dobAsString = dateAsDDMMYYYY;
	}
	public String getDobAsString() {
		return dobAsString;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "id : "+this.id+" ,name "+this.firstName+" "+this.lastName+" ,Address "+this.address;
	}

	
	
	
}
