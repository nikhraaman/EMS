package com.adp.S2B3T4_expense_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adp.S2B3T4_expense_system.entity.Employee;

public interface ManagerDao extends JpaRepository<Employee,String>{
	
	
	
}
