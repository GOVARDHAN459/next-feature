package com.example.demo.process;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.pojo.CreditData;

public class CreditDataProcess implements ItemProcessor<CreditData, CreditData>{

	@Override
	public CreditData process(CreditData item) throws Exception {
		
		return item;
	}

}
