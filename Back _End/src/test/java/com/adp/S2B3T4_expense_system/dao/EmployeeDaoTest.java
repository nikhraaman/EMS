package com.adp.S2B3T4_expense_system.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.adp.S2B3T4_expense_system.entity.Employee;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

class EmployeeDaoTest {
	
	@Mock
	private EmployeeDao employeeDao;
	
	@Test
	public void testFindByManagerId() {
		
	String managerIdString = "E17NH5C0CBD8F2FG";
		
		Employee employee1 = new Employee("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM","ZetaN@123",
				"Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");
 		Employee employee2 = new Employee("E1MNF0AE01F0TRNB","Zeta CaNov","HEY@USTEST.COM",
				"ZetaN@123","Senior Payroll Specialist","E17NH5C0CBD8F2FG","Associate");

		when(employeeDao.findByManagerId(managerIdString)).thenReturn(List.of(employee1,employee2));
		
		//when
		Iterable<Employee> result = employeeDao.findByManagerId(managerIdString);
		//then
		assertThat(result).containsExactlyInAnyOrder(employee1,employee2);
		
	}
	

}
