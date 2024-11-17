
package com.adp.S2B3T4_expense_system.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ExpenseDtoTest {

	@Test
	public void testExpenseDto() {
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
	     
		ExpenseDto expenseDto = new ExpenseDto(expId,empId,expenseCategory,startDate, endDate,amount,receipt,status,managerRemarks,employeeDescription,appliedDate, approvedDate);
		
		assertEquals(expId, expenseDto.getExpId());
		assertEquals(empId, expenseDto.getEmpId());
		assertEquals(expenseCategory, expenseDto.getExpenseCategory());
		assertEquals(startDate, expenseDto.getStartDate());
		assertEquals(endDate, expenseDto.getEndDate());
		assertEquals(receipt, expenseDto.getReceipt());
		assertEquals(amount, expenseDto.getAmount());
		assertEquals(status, expenseDto.getStatus());
		assertEquals(managerRemarks, expenseDto.getManagerRemarks());
		assertEquals(employeeDescription, expenseDto.getEmployeeDescription());
		assertEquals(appliedDate, expenseDto.getAppliedDate());
		assertEquals(approvedDate, expenseDto.getApprovedDate());

	}
	
	
	@Test
	public void testEqualsAndHashCode() {
		ExpenseDto  expenseDto1 = new ExpenseDto(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);
		ExpenseDto  expenseDto2 = new ExpenseDto(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);
		
		assertEquals(expenseDto1, expenseDto2);
		assertEquals(expenseDto1.hashCode(),expenseDto2.hashCode());

	}
	
	@Test
	public void testToString() {
		ExpenseDto  expenseDto1 = new ExpenseDto(78212,"E1H0A4TVX77D109D","Internet",Date.valueOf("2023-10-08"),Date.valueOf("2023-11-08"),1100,new byte[] {1,2,3},"Pending",null, "wifi",Date.valueOf("2023-11-27"),null);

		String toStringResult = expenseDto1.toString();
		
		assertNotNull(toStringResult);
	}

	
	@Test
	public void testAllArgsConstructor() {
		
		Integer expId = 78212;
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
	   
		ExpenseDto expenseDto = new ExpenseDto(expId,empId,expenseCategory,startDate, endDate,amount,receipt,status,managerRemarks,employeeDescription,appliedDate, approvedDate);

		assertNotNull(expenseDto);
		assertEquals(expId, expenseDto.getExpId());
		assertEquals(empId, expenseDto.getEmpId());
		assertEquals(expenseCategory, expenseDto.getExpenseCategory());
		assertEquals(startDate, expenseDto.getStartDate());
		assertEquals(endDate, expenseDto.getEndDate());
		assertEquals(receipt, expenseDto.getReceipt());
		assertEquals(amount, expenseDto.getAmount());
		assertEquals(status, expenseDto.getStatus());
		assertEquals(managerRemarks, expenseDto.getManagerRemarks());
		assertEquals(employeeDescription, expenseDto.getEmployeeDescription());
		assertEquals(appliedDate, expenseDto.getAppliedDate());
		assertEquals(approvedDate, expenseDto.getApprovedDate());
		
	     
	     
	}
	
	
	
//	@Test
//	public void testGettersAndSetters() {
//		ExpenseDto expenseDto = new ExpenseDto();
//		
//		
//		// when
//		expenseDto.setExpId(78212);
//		expenseDto.setEmpId("E1H0A4TVX77D109D");
//		expenseDto.setExpenseCategory("Internet");
//		expenseDto.setStartDate(Date.valueOf("2023-10-08"));
//		expenseDto.setEndDate(Date.valueOf("2023-11-08"));
//		expenseDto.setAmount(1100);
//		expenseDto.setReceipt(new byte[] {1,2,3});
//		expenseDto.setStatus("Pending");
//		expenseDto.setManagerRemarks(null);
//		expenseDto.setEmployeeDescription("wifi");
//		expenseDto.setAppliedDate(Date.valueOf("2023-11-27"));
//		expenseDto.setApprovedDate(null);
//		
//		//then
//		
//		assertEquals(78212, expenseDto.getExpId());
//		assertEquals("E1H0A4TVX77D109D", expenseDto.getEmpId());
//		assertEquals("Internet", expenseDto.getExpenseCategory());
//		assertEquals(Date.valueOf("2023-10-08"), expenseDto.getStartDate());
//		assertEquals(Date.valueOf("2023-11-08"), expenseDto.getEndDate());
//		assertEquals(new byte[] {1,2,3}, expenseDto.getReceipt());
//		assertEquals(1100, expenseDto.getAmount());
//		assertEquals("Pending", expenseDto.getStatus());
//		assertEquals(null, expenseDto.getManagerRemarks());
//		assertEquals("wifi", expenseDto.getEmployeeDescription());
//		assertEquals(Date.valueOf("2023-11-27"), expenseDto.getAppliedDate());
//		assertEquals(null, expenseDto.getApprovedDate());
//		
//	}
//	@Test
//	public void testAllArgsConstructorWithNulls() {
//		ExpenseDto expenseDto = new ExpenseDto(null,null,null,null,null,null,null,null,null,null,null,null);
//		
//		//then
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
//				// then
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
