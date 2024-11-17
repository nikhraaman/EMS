package com.adp.S2B3T4_expense_system.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adp.S2B3T4_expense_system.S2B3T4ExpenseSystemApplication;
import com.adp.S2B3T4_expense_system.dao.EmployeeDao;
import com.adp.S2B3T4_expense_system.dao.ExpenseDao;
import com.adp.S2B3T4_expense_system.dto.EmployeeDto;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.dto.LoginFormDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.exception.EmployeeClassException;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
    public static final Log LOGGER = LogFactory.getLog(S2B3T4ExpenseSystemApplication.class);
    
    
    
    @Override
    public EmployeeDto loginService(LoginFormDto loginDTO) throws EmployeeClassException
    {
    	if(loginDTO.getEmpId()==null || loginDTO.getPassword()==null) {
    		throw new EmployeeClassException("Service.EMPLOYEE_INVALID_INPUT");
    	}
    	
        Optional<Employee> employeeOptional = employeeDao.findById(loginDTO.getEmpId());
        
        if(employeeOptional.isEmpty()) {
        	return null;
        }
        
        String password = loginDTO.getPassword();
        String originalPassword = employeeOptional.get().getPassword();
        Boolean isPwdRight = password.equals(originalPassword);
        
        if (!isPwdRight)
        	return null;
        
        EmployeeDto empDto = modelMapper.map(employeeOptional.get(),EmployeeDto.class);
        return empDto;
    }
    
	@Override
	public List<ExpenseDto> getAllExpense(String empId) 
	{   
		Optional<Employee> employeeOptional = employeeDao.findById(empId);
        
        if(employeeOptional.isEmpty()) {
        	throw new EmployeeClassException("Service.EMPLOYEE_ID_NOT_MATCH");
        }
        
		Iterable<Expense> itrExpense = expenseDao.findByEmpId(empId);
        List<ExpenseDto> expenseDTO = new ArrayList<>();
        List<Expense> expenseOrg = new ArrayList<>();
        itrExpense.forEach(expense -> {
        	expenseOrg.add(expense);
        	expenseDTO.add(modelMapper.map(expense, ExpenseDto.class));
        });
        
        return expenseDTO;
	}

	
	@Override
	public Expense addExpenseService(Expense expense) {
		Random random=new Random();
        int eid=random.nextInt((100000-10000)+ 10000);
        expense.setExpId(eid);
        expense.setAppliedDate(Date.valueOf(LocalDate.now()));
        return expenseDao.save(expense);
	}

	
	@Override
	public List<Integer> getAllStatusAssociateService(String empId) {
				
		Optional<Employee> employeeOptional = employeeDao.findById(empId);
        
        if(employeeOptional.isEmpty()) {
        	throw new EmployeeClassException("Service.EMPLOYEE_ID_NOT_MATCH");
        }
		
		Iterable<Expense> itrExpenseIterable =  expenseDao.findByEmpId(empId);
		List<Integer> ansIntegers = new ArrayList<>();
		for(int i=0;i<3;i++) {
			ansIntegers.add(0);
		}
		
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
		
		return ansIntegers;
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
	public Employee getByIdCon(String emp_id) {
		
		Optional<Employee> employeeOptional = employeeDao.findById(emp_id);
		return employeeOptional.get();
	}
}