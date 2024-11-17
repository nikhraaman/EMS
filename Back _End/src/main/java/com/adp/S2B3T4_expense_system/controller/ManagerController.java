package com.adp.S2B3T4_expense_system.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adp.S2B3T4_expense_system.S2B3T4ExpenseSystemApplication;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.service.ManagerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/manager")
public class ManagerController {
	public static final Log LOGGER = LogFactory.getLog(S2B3T4ExpenseSystemApplication.class);

	@Autowired
	private ManagerService managerService;
	@Autowired
	Environment environment;
	
	
	@GetMapping("/action/{expId}/{status}/{managerRemarks}")
	public ResponseEntity<String> updateStatus(@PathVariable(value = "expId") Integer expId,
			@PathVariable(value = "status") String status,
			@PathVariable(value = "managerRemarks") String managerRemarks) {

		try {
			managerService.updateStatusService(expId, status, managerRemarks);
			return ResponseEntity.ok(environment.getProperty("Service.EXPENSE_ACTION_DONE"));
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			return ResponseEntity.badRequest().build();
		}
		

	}

	@GetMapping("/getallexpensebymanagerid/{empId}")
	public ResponseEntity<?> getAllExepnse(@PathVariable(value = "empId") String empId) {
		try {
			List<ExpenseDto> expenseDto = managerService.getAllExpenseByMgId(empId);
			LOGGER.info(environment.getProperty("Service.EXPENSE_SHOWN"));
			return ResponseEntity.ok(expenseDto);
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
	@GetMapping("/getallexpensebymanageridpending/{empId}")
	public ResponseEntity<?> getAllExepnsePending(@PathVariable(value = "empId") String empId) 
	{
		try {
			List<ExpenseDto> expenseDto = managerService.getAllExpenseByMgIdPending(empId);
			LOGGER.info(environment.getProperty("Service.EXPENSE_SHOWN"));
			return ResponseEntity.ok(expenseDto);
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			return ResponseEntity.badRequest().build();
		}
			
	}

	@GetMapping("/getMngAmountChart/{emp_id}")
	@CrossOrigin("*")
	public ResponseEntity<List<Map<String, String>>> getAmountChartAllMonth(@PathVariable String emp_id) {
		List<Map<String, String>> ret = new ArrayList<Map<String, String>>();

		for (int i = 1; i <= 12; i++) {
			Map<String, String> mp = new LinkedHashMap<>();
			float pendingSum = 0, approvedSum = 0, rejectedSum = 0;
			List<Employee> associates = managerService.getEmpByManagerId(emp_id).get();
			for (Employee emp : associates) {
				System.out.println(associates.toString());
				pendingSum += managerService.getAmount("Pending", emp.getEmpId(), i).stream().reduce(0f, Float::sum);
				approvedSum += managerService.getAmount("Approved", emp.getEmpId(), i).stream().reduce(0f, Float::sum);
				rejectedSum += managerService.getAmount("Rejected", emp.getEmpId(), i).stream().reduce(0f, Float::sum);
				System.out.println(pendingSum + " " + approvedSum + " " + rejectedSum);
			}
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
	

	
	@GetMapping("/getExpenseById/{expId}")
	@CrossOrigin("*")
	public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable int expId){
		return new ResponseEntity<> (managerService.getExpensebyId(expId),HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/getAllStatusManager/{empId}")
    public ResponseEntity<?> getAllStatusManager(@PathVariable(value="empId") String empId)
    {
		try {
			List<Integer> ansIntegers = managerService.getAllStatusManagerService(empId);
	        return ResponseEntity.ok(ansIntegers);
	        
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				return ResponseEntity.status(400).body(environment.getProperty(e.getMessage()));
			}
			return ResponseEntity.badRequest().body(e.getStackTrace());
		}
		
    }
	
}