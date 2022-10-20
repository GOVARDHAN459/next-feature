package com.example.demo.Service;

import com.example.demo.pojo.CreditData;
import com.example.demo.pojo.SavingAccount;

public interface DataService 
{
	public CreditData saveCreditData(CreditData creditData);
	public SavingAccount saveSavingData(SavingAccount account);
}
