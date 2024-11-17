package com.adp.S2B3T4_expense_system.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adp.S2B3T4_expense_system.dao.EmployeeDao;
import com.adp.S2B3T4_expense_system.dao.ExpenseDao;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.exception.EmployeeClassException;
import com.adp.S2B3T4_expense_system.exception.ExpenseClassException;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	ExpenseDao expenseDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void updateStatusService(Integer expId, String status, String managerRemarks) {
		
		Optional<Expense> expenseOptional = expenseDao.findById(expId);

		Expense expense = expenseOptional.get();
		String statusPresent = expense.getStatus();

		if (statusPresent.equals(status)) {
			if (status.equals("Approved"))
				throw new ExpenseClassException("Service.EXPENSE_ALREADY_APPROVED");
			else
				throw new ExpenseClassException("Service.EXPENSE_ALREADY_REJECTED");
		}
		else {
			System.out.println(status);
			expense.setStatus(status);
			expense.setManagerRemarks(managerRemarks);
		}
		expense.setApprovedDate(Date.valueOf(LocalDate.now()));
		expenseDao.save(expense);
	}

	@Override
	public List<ExpenseDto> getAllExpenseByMgId(String empId) {
		
		Optional<Employee> employeeOptional = employeeDao.findById(empId);
		
		if(employeeOptional.isEmpty()) {
        	throw new EmployeeClassException("Service.EMPLOYEE_ID_NOT_MATCH");
        }
		
		
		Iterable<Employee> itrEmployee = employeeDao.findByManagerId(empId);

		List<ExpenseDto> expenseDTO = new ArrayList<>();
		List<Expense> expenseOrg = new ArrayList<>();

		itrEmployee.forEach(employee -> {

			Iterable<Expense> itrExpense = expenseDao.findByEmpId(employee.getEmpId());
			itrExpense.forEach(expense -> {
				expenseOrg.add(expense);
				expenseDTO.add(modelMapper.map(expense, ExpenseDto.class));
			});

		});
		
		
		return expenseDTO;
	}

	@Override
	public List<ExpenseDto> getAllExpenseByMgIdPending(String empId) {
		
		Optional<Employee> employeeOptional = employeeDao.findById(empId);
		
		if(employeeOptional.isEmpty()) {
        	throw new EmployeeClassException("Service.EMPLOYEE_ID_NOT_MATCH");
        }
		
		Iterable<Employee> itrEmployee = employeeDao.findByManagerId(empId);

		List<ExpenseDto> expenseDTO = new ArrayList<>();
		List<Expense> expenseOrg = new ArrayList<>();

		itrEmployee.forEach(employee -> {

			Iterable<Expense> itrExpense = expenseDao.findByEmpId(employee.getEmpId());
			itrExpense.forEach(expense -> {
				if (expense.getStatus().equals("Pending")) {
					expenseOrg.add(expense);
					expenseDTO.add(modelMapper.map(expense, ExpenseDto.class));
				}

			});
			
		});

		return expenseDTO;
	}

	@Override
	public Optional<List<Employee>> getEmpByManagerId(String manager_id) {
		// TODO Auto-generated method stub
		return Optional.of(employeeDao.findEmpByManagerId(manager_id));
	}

	@Override
	public List<Float> getAmount(String status, String associate_id, int month) {
		List<Float> ans = expenseDao.getAmountByStatusByMonth(status, associate_id, month, LocalDate.now().getYear());
		if (ans.size() == 0) {
			ans.add((float) 0);
		}
		return ans;
	}
	
	@Override
	public Optional<Expense> getExpensebyId(int expId) {
		return expenseDao.findById(expId);
	}

	@Override
	public List<Integer> getAllStatusManagerService(String empId) {
		Optional<Employee> employeeOptional = employeeDao.findById(empId);
        if(employeeOptional.isEmpty()) {
        	throw new EmployeeClassException("Service.EMPLOYEE_ID_NOT_MATCH");
        }
        List<Integer> ansIntegers = new ArrayList<>();
		for(int i=0;i<3;i++) {
			ansIntegers.add(0);
		}
		
		
        Iterable<Employee> itrEmployee = employeeDao.findByManagerId(empId);
		itrEmployee.forEach(employee -> {
	        Iterable<Expense> itrExpenseIterable =  expenseDao.findByEmpId(employee.getEmpId());
			itrExpenseIterable.forEach(expense -> {
				if(expense.getStatus().equals("Pending")) {
					
					Integer pendingInteger = ansIntegers.get(0);
					pendingInteger++; 
					ansIntegers.set(0, pendingInteger);
				}
				else if(expense.getStatus().equals("Approved")) {
	
					Integer approvedInteger = ansIntegers.get(1);
					approvedInteger++; 
					ansIntegers.set(1, approvedInteger);
				}
				else if(expense.getStatus().equals("Rejected")){
					Integer rejectInteger = ansIntegers.get(2);
					rejectInteger++; 
					ansIntegers.set(2, rejectInteger);
				}
	        });
		});
		
		return ansIntegers;
	}

}
