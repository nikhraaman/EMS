package com.adp.S2B3T4_expense_system.dto;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class LoginFormDtoTest {

	@Test
	public void testExpenseDto() {
		String empId = "E1MNF0AE01F0TRNB";
		String password = "ZetaN@123";
		
		// when
		LoginFormDto loginFormDto = new LoginFormDto(empId,password);
		
		// then
		assertEquals(empId, loginFormDto.getEmpId());
		assertEquals(password, loginFormDto.getPassword());
		
	}
	
	
	@Test
	public void testEqualsAndHashCode() {
		//given
		LoginFormDto loginFormDto1 = new LoginFormDto("E1MNF0AE01F0TRNB","ZetaN@123");              
		LoginFormDto loginFormDto2 = new LoginFormDto("E1MNF0AE01F0TRNB", "ZetaN@123");
		
		
		assertEquals(loginFormDto1, loginFormDto2);
		assertEquals(loginFormDto1.hashCode(), loginFormDto2.hashCode());
	}
	
	@Test
	public void testToString() {
		// given
		LoginFormDto loginFormDto1 = new LoginFormDto("E1MNF0AE01F0TRNB","ZetaN@123");
		
		
		// when
		String toStringResult = loginFormDto1.toString();
		
		// then
		
		assertNotNull(toStringResult);
	}
	
	@Test
	public void testGettersAndSetters() {
		LoginFormDto loginFormDto = new LoginFormDto();
		
		// when
		loginFormDto.setEmpId("E1MNF0AE01F0TRNB");
		loginFormDto.setPassword("ZetaN@123");
		
		//then
		assertEquals("E1MNF0AE01F0TRNB", loginFormDto.getEmpId());
		assertEquals("ZetaN@123", loginFormDto.getPassword());
	}
	
	@Test
	public void testAllArgsConstructor() {
		
		String empId = "E1MNF0AE01F0TRNB";
		String password = "ZetaN@123";
		
		
		LoginFormDto loginFormDto = new LoginFormDto(empId,password);
		
		
		assertNotNull(loginFormDto);
		assertEquals(empId, loginFormDto.getEmpId());
		assertEquals(password, loginFormDto.getPassword());
		
	}
	
	
	@Test
	public void testAllArgsConstructorWithNulls() {
		LoginFormDto loginFormDto = new LoginFormDto(null,null);
		
		assertNotNull(loginFormDto);
		assertNull( loginFormDto.getEmpId());
		assertNull( loginFormDto.getPassword());
	}
	
	@Test
	public void testNoArgsConstructorWithNulls() {
		LoginFormDto loginFormDto = new LoginFormDto();
		
		assertNotNull(loginFormDto);
		assertNull( loginFormDto.getEmpId());
		assertNull( loginFormDto.getPassword());
	}

}

