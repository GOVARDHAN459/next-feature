package com.example.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.PathResource;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.example.demo.pojo.Details;
import com.example.demo.process.DetailProcessor;

@Configuration
@EnableBatchProcessing
public class Confirguation 
{
	@Autowired
	private JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	
	@Bean
	public FlatFileItemReader<Details> itemReader() {
		FlatFileItemReader<Details> reader=new FlatFileItemReader<>();
		reader.setResource(new PathResource("C:\\Users\\ROSHRIKA\\Desktop\\SpringBatch\\project\\src\\main\\resources\\Details.csv"));
		reader.setLineMapper(getlinMapper());
		reader.setLinesToSkip(1);
		return reader;
	}

	@Bean
	public LineMapper<Details> getlinMapper() {
		DefaultLineMapper<Details> defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] {"Cust_ID" ,"Customer_name","DOB","Gender","Flat_bldg","Address","Emailid","City","country","Zip","Mobile","pb_ind","int_cust_risk_rtng","domicile_cntry_cd","domicile_state"});
		lineTokenizer.setIncludedFields(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14});
	
		BeanWrapperFieldSetMapper<Details> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Details.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		
		return defaultLineMapper;
	}
	@Bean
	public DetailProcessor detailProcessor() {
		return new DetailProcessor();
	}
	 @Bean
	    public JpaItemWriter<Details > writer() {
	        JpaItemWriter writer = new JpaItemWriter();
	        writer.setEntityManagerFactory(entityManagerFactory);
	        return writer;
	    }
	 @Bean
		@Primary
		public JpaTransactionManager jpaTransactionManager2(){
			JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
			jpaTransactionManager.setDataSource(dataSource);
			return jpaTransactionManager;
		
		}
	 @Bean
		public Job importJob()  {
			return this.jobbuilderFactory.get("pass-data")
					.incrementer(new RunIdIncrementer())
					.flow(step1())
					.end()
					.build();
		}
		@Bean
		public Step step1() 
		{
			
			return this.stepbuilderFactory.get("step1")
					
					.<Details,Details>chunk(50)
					.reader(itemReader())
					.processor(detailProcessor())
					.writer(writer())
					.build();
		}

}
