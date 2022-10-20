package com.example.demo.Service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pojo.CreditData;
import com.example.demo.pojo.SavingAccount;

public class DataServiceImpl implements DataService
{
	@Autowired
	private EntityManager entityManager;
	
	
	
	public CreditData saveCreditData(CreditData creditData) {
		
		return entityManager.merge(creditData);
	}



	@Override
	public SavingAccount saveSavingData(SavingAccount account) {
		
		return entityManager.merge(account);
	}
	
}
