package com.example.demo.cofig;

import java.beans.BeanProperty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.example.demo.pjo.CrediData;
import com.example.demo.pjo.SavingData;

import antlr.collections.List;
@Configuration
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = {"com.example.demo.repository","com.example.demo.repository"})
public class Batchconfig
{
	@Autowired
	private StepBuilderFactory stepbuilderFactory;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Value("${file.path}")
	private  String resource;
	@Value("${file.path2}")
	private String resource2;
	@Value("${file.list1}")
	private String[] list1;
	@Value("${file.list2}")
	private int[] list2;
	@Value("${file.list3}")
	private String[] list3;
	@Value("${file.list4}")
	private int[] list4;
	
	
	@Bean
	public FlatFileItemReader<CrediData>fileItemReader(){
		FlatFileItemReader<CrediData> itemReader=new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource(resource));
		itemReader.setLineMapper(getLineMapper());
		itemReader.setLinesToSkip(1);
		return itemReader;
	}

	@Bean
	public LineMapper<CrediData> getLineMapper() {
		DefaultLineMapper defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		
	    lineTokenizer.setNames(list1);//attr
	    lineTokenizer.setIncludedFields(list2);  
	  
	
		BeanWrapperFieldSetMapper<CrediData> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(CrediData.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		defaultLineMapper.setFieldSetMapper( fieldSetMapper);
		
		return defaultLineMapper;
	}
	
	@Bean
	public com.example.demo.process.Creditprocess creditDataprocess() {
		return new com.example.demo.process.Creditprocess();
	}
	@Bean
	public JpaItemWriter<CrediData>writer(){
		JpaItemWriter<CrediData> itemWriter=new JpaItemWriter<>();
		itemWriter.setEntityManagerFactory(entityManagerFactory);
		return itemWriter;
	}
	
	
	@Bean
	public FlatFileItemReader<SavingData>fileItemReader2(){
		FlatFileItemReader<SavingData> itemReader=new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource(resource2));
		itemReader.setLineMapper(getLineMapper2());
		itemReader.setLinesToSkip(1);
		return itemReader;
	}
	@Bean
	public LineMapper<SavingData> getLineMapper2() {
		DefaultLineMapper defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		
	    lineTokenizer.setNames(list3);//attr
	    lineTokenizer.setIncludedFields(list4);  
	  
	
		BeanWrapperFieldSetMapper<SavingData> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(SavingData.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		defaultLineMapper.setFieldSetMapper( fieldSetMapper);
		
		return defaultLineMapper;
	}
	@Bean
	public com.example.demo.process.SavingDataprocess savingDataProc() {
		return new com.example.demo.process.SavingDataprocess();
	}
	@Bean
	public JpaItemWriter<SavingData> writer2(){
		JpaItemWriter<SavingData> itemWriter=new JpaItemWriter<>();
		itemWriter.setEntityManagerFactory(entityManagerFactory);
		return itemWriter;
	}
	
	@Primary
	@Bean
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource);
	 		return jpaTransactionManager;
	 		
	 	}
	
	//@Bean
	
	
	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("data")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.next(step2())
				.end()
				.build();
	}

	@Bean
	public Step step2() {
		
		return this.stepbuilderFactory.get("step2")
				.<SavingData,SavingData>chunk(50)
				.reader(fileItemReader2())
				.processor(savingDataProc())
				.writer(writer2())
				.build();
	}

	@Bean
	public Step step1() {
		return this.stepbuilderFactory.get("step1")
				.<CrediData,CrediData>chunk(51)
				.reader(fileItemReader())
				.processor(creditDataprocess())
				.writer(writer())
				.build();
		
	}
}
