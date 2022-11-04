package com.example.demo.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pjo.SavingData;
import com.example.demo.service.Service;

public class SavingDataprocess implements ItemProcessor<SavingData, SavingData>{
@Autowired
private Service service;

@Override
public SavingData process(SavingData savingData) throws Exception {
	service.saveData2(savingData);
	return null;
}
	

}
