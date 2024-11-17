package com.adp.S2B3T4_expense_system.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;


public class EmployeeTest {

	
	@Test
	public void testEmployee() {
		String empIdString = "E1MNF0AE01F0TRNB";
		String empNameString = "Zeta CaNov";
		String emailString = "HEY@USTEST.COM";
		String passwordString = "ZetaN@123";
		String designationString = "Senior Payroll Specialist";
		String managerIdString = "E17NH5C0CBD8F2FG";
		String roleString = "Associate";
		 
		Employee employee = new Employee(empIdString, empNameString,emailString,passwordString,designationString,managerIdString,roleString);
		
		assertEquals(empIdString, employee.getEmpId());
		assertEquals(empNameString, employee.getEmpName());
		assertEquals(emailString, employee.getEmail());
		assertEquals(passwordString, employee.getPassword());
		assertEquals(designationString, employee.getDesignation());
		assertEquals(managerIdString, employee.getManagerId());
		assertEquals(roleString, employee.getRole());
		
		
	}
	
	
	@Test
	public void testEqualsAndHashCode() {
		Employee employee1 = new Employee("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");              
		Employee employee2 = new Employee("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
		
		assertEquals(employee1, employee2);
		assertEquals(employee1.hashCode(),employee2.hashCode());
	}
	
	@Test
	public void testToString() {
		Employee employee = new Employee("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
		
		String toStringResult = employee.toString();
		
		assertNotNull(toStringResult);
		
	}
	
	
	@Test
	public void testGettersAndSetters() {
		Employee employee = new Employee();
		
		employee.setEmpId("E1MNF0AE01F0TRNB");
		employee.setEmpName("Zeta CaNov");
		employee.setEmail("HEY@USTEST.COM");
		employee.setPassword("ZetaN@123");
		employee.setDesignation("Senior Payroll Specialist");
		employee.setManagerId("E17NH5C0CBD8F2FG");
		employee.setRole("Associate");
		
		assertEquals("E1MNF0AE01F0TRNB", employee.getEmpId());
		assertEquals("Zeta CaNov", employee.getEmpName());
		assertEquals("HEY@USTEST.COM", employee.getEmail());
		assertEquals("ZetaN@123", employee.getPassword());
		assertEquals("Senior Payroll Specialist", employee.getDesignation());
		assertEquals("E17NH5C0CBD8F2FG", employee.getManagerId());
		assertEquals("Associate", employee.getRole());
	}
	
	
	@Test
	public void testAllArgsConstructor() {
		// given
		String empIdString = "E1MNF0AE01F0TRNB";
		String empNameString = "Zeta CaNov";
		String emailString = "HEY@USTEST.COM";
		String passwordString = "ZetaN@123";
		String designationString = "Senior Payroll Specialist";
		String managerIdString = "E17NH5C0CBD8F2FG";
		String roleString = "Associate";
		
		Employee employee = new Employee(empIdString, empNameString,emailString,passwordString,designationString,managerIdString,roleString);

		assertNotNull(employee);
		assertEquals(empIdString, employee.getEmpId());
		assertEquals(empNameString, employee.getEmpName());
		assertEquals(emailString, employee.getEmail());
		assertEquals(passwordString, employee.getPassword());
		assertEquals(designationString, employee.getDesignation());
		assertEquals(managerIdString, employee.getManagerId());
		assertEquals(roleString, employee.getRole());
		
	}
	
	@Test
	public void testAllArgsConstructorWithNulls() {
		Employee employee = new Employee(null,null,null,null,null,null,null);
		assertNotNull(employee);
		assertNull( employee.getEmpId());
		assertNull( employee.getEmpName());
		assertNull( employee.getEmail());
		assertNull( employee.getPassword());
		assertNull( employee.getDesignation());
		assertNull( employee.getManagerId());
		assertNull( employee.getRole());
	}
	
	
	@Test
	public void testNoArgsConstructorWithNulls() {
		Employee employee = new Employee();
		
		assertNotNull(employee);
		assertNull( employee.getEmpId());
		assertNull( employee.getEmpName());
		assertNull( employee.getEmail());
		assertNull( employee.getPassword());
		assertNull( employee.getDesignation());
		assertNull( employee.getManagerId());
		assertNull( employee.getRole());
	}
	
}



