package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Details
{
	@Id
	private int Cust_ID;
	private String Customer_name;
	private String DOB;
	private String Gender;
	private String Flat_bldg;
	private String Address;
	private String Emailid;
	private String City;
	private String  country;
	private int Zip;
	private String  Mobile;
	private String pb_ind;
	private String int_cust_risk_rtng;
	private String domicile_cntry_cd;
	private String domicile_state;
	public int getCust_ID() {
		return Cust_ID;
	}
	public void setCust_ID(int cust_ID) {
		Cust_ID = cust_ID;
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
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getFlat_bldg() {
		return Flat_bldg;
	}
	public void setFlat_bldg(String flat_bldg) {
		Flat_bldg = flat_bldg;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmailid() {
		return Emailid;
	}
	public void setEmailid(String emailid) {
		Emailid = emailid;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getZip() {
		return Zip;
	}
	public void setZip(int zip) {
		Zip = zip;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getPb_ind() {
		return pb_ind;
	}
	public void setPb_ind(String pb_ind) {
		this.pb_ind = pb_ind;
	}
	public String getInt_cust_risk_rtng() {
		return int_cust_risk_rtng;
	}
	public void setInt_cust_risk_rtng(String int_cust_risk_rtng) {
		this.int_cust_risk_rtng = int_cust_risk_rtng;
	}
	public String getDomicile_cntry_cd() {
		return domicile_cntry_cd;
	}
	public void setDomicile_cntry_cd(String domicile_cntry_cd) {
		this.domicile_cntry_cd = domicile_cntry_cd;
	}
	public String getDomicile_state() {
		return domicile_state;
	}
	public void setDomicile_state(String domicile_state) {
		this.domicile_state = domicile_state;
	}
}
