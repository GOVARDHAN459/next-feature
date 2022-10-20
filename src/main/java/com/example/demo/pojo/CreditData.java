package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CreditData
{	
	@Id
	private String CUST_KEY;
	private String Customer_name;
	private String DOB;
	private String Address ;
	private String email_id;
	private String Mobile_no;
	private String Cust_rating;
	private String Gender;
	private String S_n_P_rating;
	public String getCUST_KEY() {
		return CUST_KEY;
	}
	public void setCUST_KEY(String cUST_KEY) {
		CUST_KEY = cUST_KEY;
	}
	public String getCustomer_name() {
		return Customer_name;
	}
	public void setCustomer_name(String customer_name) {
		Customer_name = customer_name;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMobile_no() {
		return Mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		Mobile_no = mobile_no;
	}
	public String getCust_rating() {
		return Cust_rating;
	}
	public void setCust_rating(String cust_rating) {
		Cust_rating = cust_rating;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getS_n_P_rating() {
		return S_n_P_rating;
	}
	public void setS_n_P_rating(String s_n_P_rating) {
		S_n_P_rating = s_n_P_rating;
	}
}
