package com.adp.S2B3T4_expense_system.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmployeeDtoTest {

	@Test
	public void testEmployeeDto() {
		String empIdString = "E1MNF0AE01F0TRNB";
		String empNameString = "Zeta CaNov";
		String emailString = "HEY@USTEST.COM";
		String passwordString = "ZetaN@123";
		String designationString = "Senior Payroll Specialist";
		String managerIdString = "E17NH5C0CBD8F2FG";
		String roleString = "Associate";
		EmployeeDto employeeDto = new EmployeeDto(empIdString, empNameString,emailString,passwordString,designationString,managerIdString,roleString);
		
		assertEquals(empIdString, employeeDto.getEmpId());
		assertEquals(empNameString, employeeDto.getEmpName());
		assertEquals(emailString, employeeDto.getEmail());
		assertEquals(passwordString, employeeDto.getPassword());
		assertEquals(designationString, employeeDto.getDesignation());
		assertEquals(managerIdString, employeeDto.getManagerId());
		assertEquals(roleString, employeeDto.getRole());
	}
	
	
	@Test
	public void testEqualsAndHashCode() {
		EmployeeDto employeeDto1 = new EmployeeDto("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");              
		EmployeeDto employeeDto2 = new EmployeeDto("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
		assertEquals(employeeDto1, employeeDto2);
		assertEquals(employeeDto1.hashCode(),employeeDto2.hashCode());
	}
	
	@Test
	public void testToString() {
		EmployeeDto employeeDto = new EmployeeDto("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
		String toStringResult = employeeDto.toString();
		assertNotNull(toStringResult);
		
	}
	
	@Test
	public void testGettersAndSetters() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId("E1MNF0AE01F0TRNB");
		employeeDto.setEmpName("Zeta CaNov");
		employeeDto.setEmail("HEY@USTEST.COM");
		employeeDto.setPassword("ZetaN@123");
		employeeDto.setDesignation("Senior Payroll Specialist");
		employeeDto.setManagerId("E17NH5C0CBD8F2FG");
		employeeDto.setRole("Associate");
		
		assertEquals("E1MNF0AE01F0TRNB", employeeDto.getEmpId());
		assertEquals("Zeta CaNov", employeeDto.getEmpName());
		assertEquals("HEY@USTEST.COM", employeeDto.getEmail());
		assertEquals("ZetaN@123", employeeDto.getPassword());
		assertEquals("Senior Payroll Specialist", employeeDto.getDesignation());
		assertEquals("E17NH5C0CBD8F2FG", employeeDto.getManagerId());
		assertEquals("Associate", employeeDto.getRole());
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
		
		EmployeeDto employeeDto = new EmployeeDto(empIdString, empNameString,emailString,passwordString,designationString,managerIdString,roleString);
		assertNotNull(employeeDto);
		assertEquals(empIdString, employeeDto.getEmpId());
		assertEquals(empNameString, employeeDto.getEmpName());
		assertEquals(emailString, employeeDto.getEmail());
		assertEquals(passwordString, employeeDto.getPassword());
		assertEquals(designationString, employeeDto.getDesignation());
		assertEquals(managerIdString, employeeDto.getManagerId());
		assertEquals(roleString, employeeDto.getRole());
		
	}
	
	@Test
	public void testAllArgsConstructorWithNulls() {
		EmployeeDto employeeDto = new EmployeeDto(null,null,null,null,null,null,null);
		assertNotNull(employeeDto);
		assertNull( employeeDto.getEmpId());
		assertNull( employeeDto.getEmpName());
		assertNull( employeeDto.getEmail());
		assertNull( employeeDto.getPassword());
		assertNull( employeeDto.getDesignation());
		assertNull( employeeDto.getManagerId());
		assertNull( employeeDto.getRole());
	}
	
	
	@Test
	public void testNoArgsConstructorWithNulls() {
		EmployeeDto employeeDto = new EmployeeDto();
		
		assertNotNull(employeeDto);
		assertNull( employeeDto.getEmpId());
		assertNull( employeeDto.getEmpName());
		assertNull( employeeDto.getEmail());
		assertNull( employeeDto.getPassword());
		assertNull( employeeDto.getDesignation());
		assertNull( employeeDto.getManagerId());
		assertNull( employeeDto.getRole());
	}
	

}
