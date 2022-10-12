package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.Daodetails;
import com.example.demo.pojo.Details;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service
{
	@Autowired
	private Daodetails daodetails;
	
	
	
	
	public Details savePerson(Details details) {
		
		
		return daodetails.SaveData(details);
		
	}

	
}
