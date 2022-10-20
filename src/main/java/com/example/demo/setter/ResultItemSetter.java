package com.example.demo.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class ResultItemSetter implements ItemPreparedStatementSetter<String> 
{
	public static String delimeter;

	@Override
	public void setValues(String result, PreparedStatement ps) throws SQLException 
	{
		String[] res=result.split(delimeter,-1);
		for(int i=1;i<=res.length;i++) {
			ps.setString(i, res[i+1]);
		}
	}

}
