package com.adp.S2B3T4_expense_system.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.adp.S2B3T4_expense_system.S2B3T4ExpenseSystemApplication;
import com.adp.S2B3T4_expense_system.dto.EmployeeDto;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.dto.LoginFormDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController 
{
	public static final Log LOGGER = LogFactory.getLog(S2B3T4ExpenseSystemApplication.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    Environment environment;
	
	
	@GetMapping("/login/{empID}/{password}")
    public ResponseEntity<?> login(@PathVariable String empID, @PathVariable String password) 
	{
			LoginFormDto loginFormDto = new LoginFormDto();
			loginFormDto.setEmpId(empID);
			loginFormDto.setPassword(password);
			try {
				EmployeeDto empDto = employeeService.loginService(loginFormDto);
	            LOGGER.info(environment.getProperty("Service.EMPLOYEE_LOGIN_SUCCESS"));
	            return ResponseEntity.ok(empDto);
				
			}catch(Exception e) {
				if(e.getMessage()!=null) {
					LOGGER.info(environment.getProperty(e.getMessage()));
					return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
				}
				
				return ResponseEntity.badRequest().body(e.getStackTrace());
				
			}
			
	}
	

	@GetMapping("/getallexpensebyid/{empId}")
	public ResponseEntity<?> getAllExepnse(@PathVariable(value="empId") String empId)
	{
		try {
			 List<ExpenseDto> expenseDto = employeeService.getAllExpense(empId);
	         LOGGER.info(environment.getProperty("Service.EXPENSE_SHOWN"));
	         return ResponseEntity.ok(expenseDto);
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			
			return ResponseEntity.badRequest().body(e.getStackTrace());
		}
           
	}
	
	@PostMapping(value="/addExpense",headers = "content-type=multipart/*")
	@CrossOrigin("*")
	public ResponseEntity<Expense> addExpense(
			
		@RequestParam("empId") String empId,
		@RequestParam("expenseCategory") String expenseCategory,
		@RequestParam("startDate") Date startDate,
		@RequestParam("endDate") Date endDate,
		@RequestParam("amount") int amount,
		@RequestParam("employeeDescription") String employeeDescription,
		@RequestParam("receipt")MultipartFile receipt){
		
			Expense expense=new Expense();
			expense.setEmpId(empId);
			expense.setExpenseCategory(expenseCategory);
			expense.setStartDate(startDate);
			expense.setEndDate(endDate);
			expense.setAmount(amount);
			expense.setEmployeeDescription(employeeDescription);
			
		try {
			expense.setReceipt(receipt.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(employeeService.addExpenseService(expense),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllStatusAssociate/{empId}")
    public ResponseEntity<?> getAllStatusAssociate(@PathVariable(value="empId") String empId)
    {
		try {
			List<Integer> ansIntegers = employeeService.getAllStatusAssociateService(empId);
	        return ResponseEntity.ok(ansIntegers);
	        
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			return ResponseEntity.badRequest().body(e.getStackTrace());
		}
		
    }
	
	@GetMapping("/getAmountChartEmpolyeeAllMonth/{emp_id}")
	@CrossOrigin("*")
	public ResponseEntity<List<Map<String, String>>> getAmountChartEmpolyeeAllMonth(@PathVariable String emp_id) {
		List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= 12; i++) {
			Map<String, String> mp = new LinkedHashMap<>();
			Employee associates= employeeService.getByIdCon(emp_id);
			float pendingSum = 0, approvedSum = 0, rejectedSum = 0;
			System.out.println(associates.toString());
			pendingSum += employeeService.getAmount("Pending", associates.getEmpId(), i).stream().reduce(0f, Float::sum);
			approvedSum += employeeService.getAmount("Approved", associates.getEmpId(), i).stream().reduce(0f, Float::sum);
			rejectedSum += employeeService.getAmount("Rejected", associates.getEmpId(), i).stream().reduce(0f, Float::sum);
			System.out.println(pendingSum + " " + approvedSum + " " + rejectedSum);
			String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
					"Sept", "Oct", "Nov", "Dec" };
			mp.put("Month", months[i - 1]);
			mp.put("Pending", Float.toString(pendingSum));
			mp.put("Approved", Float.toString(approvedSum));
			mp.put("Rejected", Float.toString(rejectedSum));
			ret.add(mp);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
