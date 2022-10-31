package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pjo.CrediData;
import com.example.demo.pjo.SavingData;
import com.example.demo.repository.Savingrepo;
import com.example.demo.repository.creditrepo;
@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

	@Autowired
	private creditrepo creditrepo;
	@Autowired
	private Savingrepo savingrepo;
	
	
	@Override
	public void saveData(CrediData creditData) {
		creditrepo.save(creditData);
		
	}

	@Override
	public void saveData2(SavingData savingData) {
		
		savingrepo.save(savingData);
	}

}
