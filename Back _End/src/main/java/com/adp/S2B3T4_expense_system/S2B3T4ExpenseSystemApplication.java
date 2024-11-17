package com.adp.S2B3T4_expense_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S2B3T4ExpenseSystemApplication 
{
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	public static void main(String[] args) 
	{
		SpringApplication.run(S2B3T4ExpenseSystemApplication.class, args);
	}
}
