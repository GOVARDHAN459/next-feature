package com.example.demo.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.activation.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.CompositeItemReadListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.demo.mapper.InputFileMapper;
import com.example.demo.pojo.Bank;
import com.example.demo.pojo.CreditData;
import com.example.demo.process.CreditDataProcess;
import com.example.demo.setter.ResultItemSetter;



@Configuration
@EnableBatchProcessing
public class Confiurgation
{	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderFactory;
	
	@Value("$(file.path)")
	private Resource[] inputResources;
	
	
	@Value("$(SavingData)")
	private String xyzbank;
	
	@Value("$(CreditData)")
	private String abcbank;
	
//	@Value("$(fileTableConfigPath)")
//	private String fileconfig;
	
	@Value("$(tableschema)")
	private String tableschema;
	
	@Value("$(tablename)")
	private String tablename;
	
	@Autowired
	private javax.sql.DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;

	
	
	
	@Bean
	public FlatFileItemReader<String> itemReader(){
		FlatFileItemReader reader=new FlatFileItemReader<>();
		reader.setLineMapper(getlineMapper());
		reader.setLinesToSkip(1);
		return reader;
	}
	@Bean
	public MultiResourceItemReader multiResourceItemReader() {
		MultiResourceItemReader multiResourceItemReader=new MultiResourceItemReader<>();
		multiResourceItemReader.setResources(inputResources);
		multiResourceItemReader.setDelegate(itemReader());
		return multiResourceItemReader;
	}
	
	@Bean
	public LineMapper getlineMapper()  {
		DefaultLineMapper defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setNames(getcolumnName());
	
		System.out.println(getcolumnName());
		BeanWrapperFieldSetMapper fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(new InputFileMapper() );
		
		return defaultLineMapper;
	}
	List<String> attr;
	List<Integer> index;
	@Bean
	public String[] getcolumnName()
	{
		
 //      List<Bank> query = jdbcTemplate.queryForList("select * from bank where bankname='abcbank' order by Indexs", Bank.class);
       
		List<Bank> query=entityManager.createQuery("select p from Bank p where bankname='abcbank' order by Indexs").getResultList();
		List<String> s=new ArrayList<String>();
		//String[] arr= {" "};
		int count=0;
		for(Bank ab:query) {
		
			//System.out.println(ab.getAttributes());
			
		//	s.addAll(ab.getAttributes());
			count++;
			s.add(ab.getAttributes());
			
		//	attr.add(ab.getAttributes());
		//	s.add(ab.getIndexs());
		//	System.out.println(s);
		//	System.out.println(ab.getIndexs());
				
		}
	//	System.out.println(s);
		
	//arr=new String[count];
	String[] arr=s.toArray(new String[s.size()]);
		
		
	
		return arr;
		
		
		
	//	System.out.println(query);
   
       //  query.add(null)
//       for(Bank ab:query) {
//    	System.out.println(ab.getAttributes());   
//    	  System.out.println(ab.getAttributes()); 
//    	  
//    	  
//    	  
//       }
       
       
       
       
//       for(Map<String, Object> ab:query) {
//    	   for(Map.Entry<String,Object> entry:ab.entrySet())
//    	   {
//    		  System.out.println(entry.getValue()); 
//    	   }
//       }
//      
		
		
	}
	
	
	
	
	
//	
//	@Bean
//	 public String[] getcolumnName() {
//		String query="select * from bank where bank=abcbank order by index ";
//		return query;
//	}
	@Bean
	 public CreditDataProcess creditDataProcess() {
		 return new CreditDataProcess();
	 }
	
//	@Bean
//	public JdbcBatchItemWriter<String> batchItemWriter(){
//		ItemPreparedStatementSetter<String> setter=new ResultItemSetter();
//		return new JdbcBatchItemWriterBuilder<String>().itemPreparedStatementSetter(setter).sql(getQuery()).dataSource(dataSource).build();
//	}
	
//	 @Bean
//	 public String getQuery() {
//		HashMap<String, String> map=new HashMap<>();
//		map.put(fname, ftablename);
//		HashMap<String , List<String>> map2=new HashMap<>();
//		map2.put(fname, Arrays.asList("fheader"));
//		String[] columns=map.get(fname).split(delimeter);
//		ResultItemSetter.delimeter=delimeter;
//		String query="";
//		String first="";
//		String second="";
//		for(int i=0;i<columns.length;i++) {	
//			if(i+1==columns.length) {
//				first=first+columns[i];
//				second=second+"?";
//			}else {
//				first=first+columns[i]+","; //""+0
//				second=second+"?"+",";
//			}
//		}
//		query="INSERT INTO" +ftablename+"("+first+")VALUES("+second+")";
//		
//		return query;
//	}
	 
	@Bean
	 public Job job() {
		 return this.jobbuilderFactory.get("Get-Data")
				 .incrementer(new RunIdIncrementer())
				 .flow(step1())
				 .end()
				 .build();
	 }
	 
	 @Bean
	 public Step step1() {
//		 ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
//		 executor.setCorePoolSize(4);
//		 executor.setMaxPoolSize(4);
//		 executor.afterPropertiesSet();
		
		 
		 return this.stepbuilderFactory.get("step1")
				 .chunk(50)
				 .reader(itemReader())
				// .writer( batchItemWriter())
			//	 .taskExecutor(executor)
				 .build();
	 }
	 
	 
	
	 

	
}
