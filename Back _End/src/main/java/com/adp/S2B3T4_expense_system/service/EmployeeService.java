package com.adp.S2B3T4_expense_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.adp.S2B3T4_expense_system.dto.EmployeeDto;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.dto.LoginFormDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;

@Service
public interface EmployeeService 
{
	EmployeeDto loginService(LoginFormDto loginDTO);
	
	List<ExpenseDto> getAllExpense(String empId);

	Expense addExpenseService(Expense expense);

	List<Integer> getAllStatusAssociateService(String empId);

	Employee getByIdCon(String emp_id);

	public List<Float> getAmount(String status, String associate_id,int month);

}
