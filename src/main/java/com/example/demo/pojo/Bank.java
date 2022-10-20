package com.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank")
public class Bank
{
	@Id
	private int Indexs;
	private String Bankname;
	private String Attributes;
	
	
	public String getBankname() {
		return Bankname;
	}
	public void setBankname(String bankname) {
		Bankname = bankname;
	}
	public String getAttributes() {
		return Attributes;
	}
	public void setAttributes(String attributes) {
		Attributes = attributes;
	}
	public int getIndexs() {
		return Indexs;
	}
	public void setIndexs(int indexs) {
		Indexs = indexs;
	}
	
	
}
