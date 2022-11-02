package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pjo.CrediData;
import com.example.demo.pjo.MatchedRecord;
import com.example.demo.pjo.SavingData;
import com.example.demo.repository.Matchrepo;
import com.example.demo.repository.Savingrepo;
import com.example.demo.repository.creditrepo;
@Service
public class MatchedService 
{
	@Autowired
	private creditrepo creditrepo;
	@Autowired
	private Savingrepo savingrepo;
	@Autowired
	private Matchrepo matchrepo;
	
	public  double checkVal() throws ParseException{
		double namematcher=0,adressmacther=0,dobmatcher=0,gendermatcher=0,allmatch=0;
		
		
		
	List<CrediData> crd=creditrepo.findAll();
	List<SavingData> svd=savingrepo.findAll();
	
	
	for(int i=0;i<crd.size();i++) {
		for(int j=0;j<svd.size();j++) {
			namematcher=NameMatch(crd.get(i).getCustomer_name(), svd.get(j).getCustomer_name());
			adressmacther=addressMatcher(crd.get(i).getAddress(), svd.get(j).getAddress());
			dobmatcher=dobMatch(crd.get(i).getDOB(), svd.get(j).getDOB());
			gendermatcher=genderMatch(crd.get(i).getGender(), svd.get(j).getGender());
			allmatch=namematcher+adressmacther+dobmatcher+gendermatcher;
			//System.out.println("tortal matrch" +allmatch);
			if(allmatch>=100) {
				List<MatchedRecord> mt=new ArrayList<>();
				MatchedRecord record=new MatchedRecord();
				record.setCredid(crd.get(i).getCUST_KEY());
				record.setSavingid(svd.get(j).getCust_ID());
				record.setMatchpercentage(allmatch);
				mt.add(record);
				matchrepo.save(record);
			}
		}
	}
	return allmatch;
	
	}
	
	
	
	//adress
	public static double addressMatcher(String add1,String add2) {
		double address_weight =20;
	
		
	  List<String> arrOfAddress1 = wordSeparator(add1);
	  System.out.println(arrOfAddress1);
	  List<String> arrOfAddress2 =wordSeparator(add2);

	  return address_weight*(wordMatching(arrOfAddress1, arrOfAddress2));
		
	}
	
	public static List<String>  wordSeparator(String str1) {
		
		Set<String> s1=new TreeSet();
	List<String> l1 = new ArrayList<String>(Arrays.asList(str1.split(",")));
//	List<String> l2 = new ArrayList<String>(Arrays.asList(str2.split(" ")));
	 
	return removeWhitespace(l1);
	}
	
	public static List<String> removeWhitespace(List<String> s1){
		for(int i=0;i<s1.size();i++) {
			s1.set(i, s1.get(i).trim());
		}
		return s1;
	}
	
	
	
	public static double  wordMatching(List<String> l1,List<String> l2) {
		int  match_words=0;
		int l1_size=l1.size();
		int l2_size =l2.size();
		int total_words=l1_size>l2_size?l1_size:l2_size;
	
	
	 if(l1_size>=l2_size) 
		{	
			for(String str : l2) {
					if(l1.contains(str)) { match_words++; }
				}
			return Math.round(((double)match_words/total_words)*100.0)/100.00;
			
		}
		
		else if(l1_size<l2_size)
			{
				for(String str:l1) {

					if(l2.contains(str)) {
						match_words++; }
				}
				return Math.round(((double)match_words/total_words)*100.0)/100.00;
			
			}
		
		else  return 0;
		

}
	//name
	public static double NameMatch(String name1, String name2)
	{
		double name_weight =30;
		
		
	  name1=  name1.toUpperCase();
	   name2= name2.toUpperCase();
		
	  
	    
	  List<String> arrOfName1 = new ArrayList<String>();
	  arrOfName1=Arrays.asList(name1.split(" "));
	  
	  
	  //System.out.println(arrOfName1);
	  List<String> arrOfName2 = new ArrayList<String>();
	  arrOfName2=Arrays.asList(name2.split(" "));
	  
	  return name_weight*(wordMatching(arrOfName1, arrOfName2));
	
	}
	
	//dob
	public static double dobMatch(String date1, String date2) throws ParseException
	{
		double date_weight =30.0;
		double No_match_date_Weight=0;
		
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = sdformat.parse(date1);
        Date d2 = sdformat.parse(date2);
        
       // System.out.println("The date 1 is: " + sdformat.format(d1));
       // System.out.println("The date 2 is: " + sdformat.format(d2));
         
        
       
		if (d1.compareTo(d2) == 0)

			return date_weight;

		else

			return No_match_date_Weight; 
	
	}
	
	//gender
	public static double genderMatch(String gender1,String gender2 ) {
		double gender_weight=20;
		
		gender1=gender1.toUpperCase();
		gender2=gender2.toUpperCase();
		if(gender1.charAt(0)==gender2.charAt(0))
		{	
			return gender_weight;
		}
		
		else return 0;
	}
	
	
}
