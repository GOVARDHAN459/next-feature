package com.example.demo.controller;

import java.text.ParseException;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pjo.MatchedRecord;
import com.example.demo.service.MatchedService;


@RestController
public class Controller 
{
	@Autowired
	private MatchedService matchedService;
	
	@GetMapping("/getcheckeddata")
	public double get() throws ParseException {
		
	//	System.out.println(matchedService.checkVal());
		return matchedService.checkVal();
	}
	
//	public String Saved()
//	{
//		
//	}
}
