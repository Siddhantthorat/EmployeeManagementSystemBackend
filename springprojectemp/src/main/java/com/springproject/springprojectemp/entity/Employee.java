package com.springproject.springprojectemp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

@Entity
@Table(name="employeesid")
public class Employee {
			
	@Id
	@GeneratedValue
	private int id;
	
	@Column
//	@NotBlank
//	@NotNull(message="FirstName Should not be Null")
	private String firstName;
	
	@Column
//	@NotBlank
	//@NotNull(message="LastName Should not be Null")
	private String lastName;
	
	@Column
//	@NotBlank
	private String department;
	
	@Column
//	@NotBlank
	//@NotNull(message="Designation Should not be Null")
	private String designation;
	
	@Column
//	@Pattern(regexp="^\\d{10}$",message="Invalid Phone no...")
	
	private long phoneNo ;
	
	@Column
	//@Email(message="Invalid Email")
	private String email;
	
	@Column
	//@NotBlank
	//@NotNull(message="Address Should not be Null")
	private String address;
	
	@Column
	private float amount;
	private float total;
	public Employee()
	{}
	
	
	public Employee(int id, String firstName, String lastName, String department, String designation, long phoneNo,
			String email, String address, float amount, float total) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.amount = amount;
		this.total = total;
	}


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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
	
	
}