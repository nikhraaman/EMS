package com.adp.S2B3T4_expense_system.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.adp.S2B3T4_expense_system.dto.EmployeeDto;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
 
		@Autowired
		private MockMvc mockMvc;
		
		@MockBean
		private EmployeeService employeeService;
		
		@Autowired
		EmployeeController employeeController;
		
		
		@Test
		public void testLoginSuccess() throws Exception{
			String empId = "E1MNF0AE01F0TRNB";
			String password = "ZetaN@123";
			EmployeeDto employeeDto = new EmployeeDto(empId,"Zeta CaNov","HEY@USTEST.COM",password,"Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
			
			when(employeeService.loginService(any())).thenReturn(employeeDto);
			mockMvc.perform(get("/employee/login/{empID}/{password}",empId,password).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.empId").value(employeeDto.getEmpId())) 
			.andExpect(jsonPath("$.password").value(employeeDto.getPassword()));
		}
		
		@Test
		public void testLoginFailure() throws Exception{
			String empId = "E1MNF0AE01F0TRNB";
			String password = "ZetaN@124";
			
			when(employeeService.loginService(any())).thenReturn(null);
			mockMvc.perform(get("/login/{empID}/{password}",empId,password)).andExpect(status().isNotFound());
		}
		
		
		@Test
		void testGetAllExpense() {    
			String empId = "E1H0A4TVX77D109D";
			ExpenseDto expenseDto = new ExpenseDto();
			expenseDto.setExpId(78212);
			expenseDto.setEmpId("E1H0A4TVX77D109D");
			expenseDto.setExpenseCategory("Internet");
			expenseDto.setStartDate(Date.valueOf("2023-10-08"));
			expenseDto.setEndDate(Date.valueOf("2023-11-08"));
			expenseDto.setAmount(1100);
			expenseDto.setReceipt(new byte[] {1,2,3});
			expenseDto.setStatus("Pending");
			expenseDto.setManagerRemarks(null);
			expenseDto.setEmployeeDescription("wifi");
			expenseDto.setAppliedDate(Date.valueOf("2023-11-27"));
			expenseDto.setApprovedDate(null);
			List<ExpenseDto> expenseList = new ArrayList<>();
			expenseList.add(expenseDto);
			when(employeeService.getAllExpense(empId)).thenReturn(expenseList);
			ResponseEntity<?> responseEntity = employeeController.getAllExepnse(empId);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			assertEquals(expenseList ,responseEntity.getBody());
		}
		

		@Test
		 void testGetAllStatusAssociate() {
		        String empId = "someEmpId";
		        ResponseEntity<?> response = employeeController.getAllStatusAssociate(empId);
		        assertNotNull(response);
		    }
		
		   @Test
		    void testAddExpense() throws IOException {
		        MockMultipartFile mockMultipartFile = new MockMultipartFile("receipt", "receipt.txt", "text/plain", "Receipt content".getBytes());
		        when(employeeService.addExpenseService(Mockito.any(Expense.class))).thenReturn(null);
		        ResponseEntity<Expense> responseEntity = employeeController.addExpense(
		                "sampleEmpId",
		                "sampleExpenseCategory",
		                Date.valueOf("2023-01-01"),
		               Date.valueOf("2023-01-01"),
		               100,
		                "sampleEmployeeDescription",
		                mockMultipartFile
		        );
		        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		    }
}




		
