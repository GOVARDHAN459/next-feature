package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.pjo.CrediData;
import com.example.demo.pjo.SavingData;

@Component
public interface Service 
{
	void saveData(CrediData creditData);
	void saveData2(SavingData savingData);
	
	
}
