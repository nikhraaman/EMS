package com.adp.S2B3T4_expense_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.adp.S2B3T4_expense_system.dao.EmployeeDao;
import com.adp.S2B3T4_expense_system.dao.ExpenseDao;
import com.adp.S2B3T4_expense_system.dto.EmployeeDto;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.dto.LoginFormDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.exception.EmployeeClassException;

class EmployeeServiceImplTest {
	
    @InjectMocks
    private EmployeeServiceImpl EmployeeServiceImpl;

    @Mock
    private ExpenseDao expenseDao;
    
    @Mock
    private EmployeeDao employeeDao;
    

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        public void testGetAllExpense() {
            String empId = "123";
            Expense expense = new Expense(); // Assuming you have an Expense entity
            ExpenseDto expenseDto = new ExpenseDto(); // Assuming ExpenseDto is correctly implemented
            Optional<Employee> employee = Optional.ofNullable(new Employee());
            when(employeeDao.findById(anyString())).thenReturn(employee);
            when(expenseDao.findByEmpId(anyString())).thenReturn(Arrays.asList(expense));
            when(modelMapper.map(expense, ExpenseDto.class)).thenReturn(expenseDto);
            List<ExpenseDto> result = EmployeeServiceImpl.getAllExpense(empId);
            assertEquals(1, result.size());
            assertEquals(expenseDto, result.get(0));
        }
        
        @Test
        public void testLoginService() throws EmployeeClassException 
        {
            LoginFormDto loginDTO = new LoginFormDto();
            loginDTO.setEmpId("EMP001");
            loginDTO.setPassword("password");
            
            Employee employee = new Employee();
            
            employee.setEmpId("EMP001");
            employee.setEmpName("John Doe");
            employee.setEmail("john.doe@example.com");
            employee.setPassword("password");
            employee.setDesignation("Developer");
            employee.setManagerId("MAN001");
            employee.setRole("ROLE_USER");
            
            EmployeeDto employeeDto=new EmployeeDto();
            
            employeeDto.setEmpId("EMP001");
            employeeDto.setEmpName("John Doe");
            employeeDto.setEmail("john.doe@example.com");
            employeeDto.setDesignation("Developer");
            employeeDto.setManagerId("MAN001");
            employeeDto.setRole("ROLE_USER");

            when(employeeDao.findById(anyString())).thenReturn(Optional.of(employee));
            when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);
            EmployeeDto result = EmployeeServiceImpl.loginService(loginDTO);
            assertEquals(employeeDto, result);
        }

        @Test
        public void testLoginServiceInvalidEmployee() throws EmployeeClassException {
            LoginFormDto loginDTO = new LoginFormDto();
            loginDTO.setEmpId("EMP002");
            loginDTO.setPassword("password");
            when(employeeDao.findById(anyString())).thenReturn(Optional.empty());
            EmployeeDto result = EmployeeServiceImpl.loginService(loginDTO);
            assertNull(result);
        	
        }

        @Test
        public void testLoginServiceIncorrectPassword() throws EmployeeClassException {
            LoginFormDto loginDTO = new LoginFormDto();
            loginDTO.setEmpId("EMP001");
            loginDTO.setPassword("wrongPassword");
            Employee employee = new Employee();
            when(employeeDao.findById(anyString())).thenReturn(Optional.of(employee));
            EmployeeDto result = EmployeeServiceImpl.loginService(loginDTO);
            assertNull(result);
        }
        
        
        @Test
        public void testAddExpenseService() 
        {
        	Expense expense = new Expense(); 
        	expense.setExpId(1); 
        	expense.setEmpId("EMP001"); 
        	expense.setExpenseCategory("Travel"); 
        	expense.setStartDate(Date.valueOf(LocalDate.now())); 
        	expense.setEndDate(Date.valueOf(LocalDate.now().plusDays(5))); 
        	expense.setAmount(500); expense.setReceipt(new byte[]{1, 2, 3}); 
        	expense.setStatus("Pending"); 
        	expense.setManagerRemarks("Manager's remarks"); 
        	expense.setEmployeeDescription("Employee's description"); 
        	expense.setAppliedDate(Date.valueOf(LocalDate.now())); 
        	expense.setApprovedDate(null);
        	
        	 expenseDao.save(expense);

        }


        
}
