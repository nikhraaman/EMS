package com.adp.S2B3T4_expense_system.entity;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpenseTest {


	@Test
	public void testExpense() {
		 int expId = 78212;
	     String empId = "E1H0A4TVX77D109D";
	     String expenseCategory = "Internet";
	     Date startDate = Date.valueOf("2023-10-08");
	     Date endDate = Date.valueOf("2023-11-08");
	     byte[] receipt = new byte[] {1,2,3};
	     int amount = 1100;
	     String status = "Pending";
	     String managerRemarks = null;
	     String employeeDescription = "wifi";
	     Date appliedDate = Date.valueOf("2023-11-27");
	     Date approvedDate = null;
	     
		Expense expense = new Expense( expId,  empId,  expenseCategory,  startDate,  endDate,  amount,
				 receipt,  status,  managerRemarks,  employeeDescription,  appliedDate,
				 approvedDate);     
		
		assertEquals(expId, expense.getExpId());
		assertEquals(empId, expense.getEmpId());
		assertEquals(expenseCategory, expense.getExpenseCategory());
		assertEquals(startDate, expense.getStartDate());
		assertEquals(endDate, expense.getEndDate());
		assertEquals(receipt, expense.getReceipt());
		assertEquals(amount, expense.getAmount());
		assertEquals(status, expense.getStatus());
		assertEquals(managerRemarks, expense.getManagerRemarks());
		assertEquals(employeeDescription, expense.getEmployeeDescription());
		assertEquals(appliedDate, expense.getAppliedDate());
		assertEquals(approvedDate, expense.getApprovedDate());

	}
	
	
	@Test
	public void testEqualsAndHashCode() {
		Expense  expense1 = new Expense(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);
		Expense  expense2 = new Expense(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);
		
		// asserts
		
		assertEquals(expense1, expense2);
		assertEquals(expense1.hashCode(),expense2.hashCode());

	}
	
	@Test
	public void testToString() {
		Expense  expense1 = new Expense(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);

		String toStringResult = expense1.toString();
		
		assertNotNull(toStringResult);
	}
	

	
	@Test
	public void testAllArgsConstructor() {
		
		int expId = 78212;
	     String empId = "E1H0A4TVX77D109D";
	     String expenseCategory = "Internet";
	     Date startDate = Date.valueOf("2023-10-08");
	     Date endDate = Date.valueOf("2023-11-08");
	     byte[] receipt = new byte[] {1,2,3};
	     int amount = 1100;
	     String status = "Pending";
	     String managerRemarks = null;
	     String employeeDescription = "wifi";
	     Date appliedDate = Date.valueOf("2023-11-27");
	     Date approvedDate = null;
	     
		Expense expense = new Expense(expId,  empId,  expenseCategory,  startDate,  endDate,  amount,
				 receipt,  status,  managerRemarks,  employeeDescription,  appliedDate,
				 approvedDate);     

		assertNotNull(expense);
		assertEquals(expId, expense.getExpId());
		assertEquals(empId, expense.getEmpId());
		assertEquals(expenseCategory, expense.getExpenseCategory());
		assertEquals(startDate, expense.getStartDate());
		assertEquals(endDate, expense.getEndDate());
		assertEquals(receipt, expense.getReceipt());
		assertEquals(amount, expense.getAmount());
		assertEquals(status, expense.getStatus());
		assertEquals(managerRemarks, expense.getManagerRemarks());
		assertEquals(employeeDescription, expense.getEmployeeDescription());
		assertEquals(appliedDate, expense.getAppliedDate());
		assertEquals(approvedDate, expense.getApprovedDate());
		
	     
	     
	}
	
//	@Test
//	public void testGettersAndSetters() {
//		Expense expense = new Expense();
//		
//		expense.setExpId(78212);
//		expense.setEmpId("E1H0A4TVX77D109D");
//		expense.setExpenseCategory("Internet");
//		expense.setStartDate(Date.valueOf("2023-10-08"));
//		expense.setEndDate(Date.valueOf("2023-11-08"));
//		expense.setAmount(1100);
//		expense.setReceipt(new byte[] {1,2,3});
//		expense.setStatus("Pending");
//		expense.setManagerRemarks(null);
//		expense.setEmployeeDescription("wifi");
//		expense.setAppliedDate(Date.valueOf("2023-11-27"));
//		expense.setApprovedDate(null);
//		
//		assertEquals(78212, expense.getExpId());
//		assertEquals("E1H0A4TVX77D109D", expense.getEmpId());
//		assertEquals("Internet", expense.getExpenseCategory());
//		assertEquals(Date.valueOf("2023-10-08"), expense.getStartDate());
//		assertEquals(Date.valueOf("2023-11-08"), expense.getEndDate());
//		assertEquals(new byte[] {1,2,3}, expense.getReceipt());
//		assertEquals(1100.f, expense.getAmount());
//		assertEquals("Pending", expense.getStatus());
//		assertEquals(null, expense.getManagerRemarks());
//		assertEquals("wifi", expense.getEmployeeDescription());
//		assertEquals(Date.valueOf("2023-11-27"), expense.getAppliedDate());
//		assertEquals(null, expense.getApprovedDate());
//		
//	}
	
//	@Test
//	public void testAllArgsConstructorWithNulls() {
//		ExpenseDto expenseDto = new ExpenseDto();
//		
//				assertNotNull(expenseDto);
//				assertNull( expenseDto.getExpId());
//				assertNull( expenseDto.getEmpId());
//				assertNull( expenseDto.getExpenseCategory());
//				assertNull( expenseDto.getStartDate());
//				assertNull( expenseDto.getEndDate());
//				assertNull( expenseDto.getReceipt());
//				assertNull( expenseDto.getAmount());
//				assertNull(expenseDto.getStatus());
//				assertNull(expenseDto.getManagerRemarks());
//				assertNull(expenseDto.getEmployeeDescription());
//				assertNull(expenseDto.getAppliedDate());
//				assertNull(expenseDto.getApprovedDate());
//				
//	}
	
//	@Test
//	public void testNoArgsConstructorWithNulls() {
//		ExpenseDto expenseDto = new ExpenseDto();
//		
//				assertNotNull(expenseDto);
//				assertNotNull(expenseDto);
//				assertNull( expenseDto.getExpId());
//				assertNull( expenseDto.getEmpId());
//				assertNull( expenseDto.getExpenseCategory());
//				assertNull( expenseDto.getStartDate());
//				assertNull( expenseDto.getEndDate());
//				assertNull( expenseDto.getReceipt());
//				assertNull( expenseDto.getAmount());
//				assertNull(expenseDto.getStatus());
//				assertNull(expenseDto.getManagerRemarks());
//				assertNull(expenseDto.getEmployeeDescription());
//				assertNull(expenseDto.getAppliedDate());
//				assertNull(expenseDto.getApprovedDate());
//				
//	}
}
