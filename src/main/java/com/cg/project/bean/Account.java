package com.cg.project.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accnumber;
	@Pattern(regexp="[A-Z][a-z]*([ ][A-Z][a-z]*)*")
	private String name;
	@Pattern(regexp="(\\+91(-)?|91(-)?|0(-)?)[9876][0-9]{9}")
	private String phone;
	//@Pattern(regexp="^([0-2][0-9]|(3)[0-1])(\\\\/)(((0)[0-9])|((1)[0-2]))(\\\\/)\\\\d{2}$")

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String address;
	@Pattern(regexp="[0-9]{13}")
	private String aadharcard;
	@Min(1)
	private double balance;
	
	private String username;
	private String password;
	
	
	public int getAccnumber() {
		return accnumber;
	}
	public void setAccnumber(int accnumber) {
		this.accnumber = accnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAadharcard() {
		return aadharcard;
	}
	public void setAadharcard(String aadharcard) {
		this.aadharcard = aadharcard;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
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
	
	
	
	
	
}
