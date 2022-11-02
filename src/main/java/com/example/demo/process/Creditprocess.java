package com.example.demo.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pjo.CrediData;
import com.example.demo.service.MatchedService;
import com.example.demo.service.Service;

public class Creditprocess implements ItemProcessor<CrediData, CrediData>{
@Autowired
private Service service;


	@Override
	public CrediData process(CrediData crediData) throws Exception {
		service.saveData(crediData);
		
		
		return null;
	}

}
