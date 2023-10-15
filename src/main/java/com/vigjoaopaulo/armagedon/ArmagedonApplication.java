package com.vigjoaopaulo.armagedon;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vigjoaopaulo.armagedon.service.DepositosService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ArmagedonApplication {
	@Autowired
	private DepositosService depositosService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ArmagedonApplication.class, args);
	}
	
	
	@PostConstruct
	public void init() throws FileNotFoundException, ParseException, SQLException {
		depositosService.processFile();
		
		
	}

}
