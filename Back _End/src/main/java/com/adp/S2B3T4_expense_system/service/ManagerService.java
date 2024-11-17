package com.adp.S2B3T4_expense_system.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;

@Service
public interface ManagerService {
    void updateStatusService(Integer expId, String status, String managerRemarks);
    List<ExpenseDto> getAllExpenseByMgId(String empId);
    public Optional<List<Employee>> getEmpByManagerId(String manager_id);
	public List<Float> getAmount(String status, String associate_id,int month);
	List<ExpenseDto> getAllExpenseByMgIdPending(String empId);
	public Optional<Expense> getExpensebyId(int expId);
	List<Integer> getAllStatusManagerService(String empId);
	
}