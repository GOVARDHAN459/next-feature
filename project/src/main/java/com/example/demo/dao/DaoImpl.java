package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Details;
@Component
public class DaoImpl implements Daodetails
{
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Details SaveData(Details details) {
		
		return entityManager.merge(details);
	}

	

}
