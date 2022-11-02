package com.example.demo.pjo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MatchedRecord
{	
	
	private String customername;
	private String credid;
	
	private int savingid;
	@Id
	private double Matchpercentage;
	public double getMatchpercentage() {
		return Matchpercentage;
	}
	public void setMatchpercentage(double matchpercentage) {
		Matchpercentage = matchpercentage;
	}
	public String getCredid() {
		return credid;
	}
	public void setCredid(String credid) {
		this.credid = credid;
	}
	public int getSavingid() {
		return savingid;
	}
	public void setSavingid(int i) {
		this.savingid = i;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	private String dob;
	private String Adress ;
	private String gender;
}
