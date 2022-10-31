package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.pjo.CrediData;
@Repository
public interface creditrepo extends JpaRepository<CrediData, String> {

}
