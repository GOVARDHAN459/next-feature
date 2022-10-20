package com.example.demo.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;

@Configuration
public class InputFileMapper implements FieldSetMapper<String>
{

	@Override
	public String mapFieldSet(FieldSet fieldSet) throws BindException {
		String result=fieldSet.readString(0);
		return result;
	}

}
