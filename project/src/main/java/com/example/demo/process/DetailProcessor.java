package com.example.demo.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.DaoImpl;
import com.example.demo.dao.Daodetails;
import com.example.demo.pojo.Details;
import com.example.demo.service.Service;

public class DetailProcessor implements ItemProcessor<Details , Details>{

	@Autowired
	//private Daodetails daodetails;
	Service service;
	@Override
	public Details process(Details Details) throws Exception {
//		Details d=new Details(); // class obj create 
//		d.setCust_ID(Details.getCust_ID());
//		d.setAddress(Details.getAddress());
//		d.setCity(Details.getCity());
//		d.setCountry(Details.getCountry());
//		d.setCustomer_name(Details.getCustomer_name());
//		d.setDOB(Details.getDOB());
//		d.setGender(Details.getGender());
//		d.setDomicile_cntry_cd(Details.getDomicile_cntry_cd());
//		d.setDomicile_state(Details.getDomicile_state());
//		d.setEmailid(Details.getEmailid());
//		d.setFlat_bldg(Details.getFlat_bldg());
//		d.setZip(Details.getZip());
//		d.setPb_ind(Details.getPb_ind());
//		d.setInt_cust_risk_rtng(Details.getInt_cust_risk_rtng());
//		d.setMobile(Details.getMobile());
		
		return service.savePerson(Details);
	}

}
