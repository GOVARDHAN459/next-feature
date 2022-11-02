package com.example.demo.listner;

import java.text.ParseException;

import org.springframework.batch.core.ItemReadListener;

import com.example.demo.service.MatchedService;

public class CheckData implements ItemReadListener<MatchedService> 
{

	@Override
	public void beforeRead() {
	System.out.println("data is being read");
		
	}

	@Override
	public void afterRead(MatchedService item) {
		try {
			item.checkVal();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
