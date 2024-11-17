package com.adp.S2B3T4_expense_system.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.adp.S2B3T4_expense_system.dao.EmployeeDao;
import com.adp.S2B3T4_expense_system.dao.ExpenseDao;
import com.adp.S2B3T4_expense_system.dto.ExpenseDto;
import com.adp.S2B3T4_expense_system.entity.Employee;
import com.adp.S2B3T4_expense_system.entity.Expense;
import com.adp.S2B3T4_expense_system.exception.EmployeeClassException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ManagerServiceImplTest {

    @InjectMocks
    private ManagerServiceImpl managerServiceImpl;

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
    void testUpdateStatusService() {
        Integer expId = 1;
        String status = "Approved";
        String managerRemarks = "Some remarks";

        Expense expense = new Expense();
        expense.setStatus("Pending");

        Optional<Expense> expenseOptional = Optional.of(expense);
        Mockito.when(expenseDao.findById(anyInt())).thenReturn(expenseOptional);

        managerServiceImpl.updateStatusService(expId, status, managerRemarks);

        assertEquals(status, expense.getStatus());
        assertEquals(managerRemarks, expense.getManagerRemarks());
        assertEquals(Date.valueOf(LocalDate.now()), expense.getApprovedDate());
        Mockito.verify(expenseDao, Mockito.times(1)).save(expense);
    }
    
    @Test
    void testGetAllExpenseByMgId() {
   
        String managerId = "E17NH5C0CBD8F2FG";
        Employee employee1 = new Employee("E1MNF0AE01F0TRNB","Zeta CaNovm", "HEY@USTEST.COM","ZetaN@123","Senior Payroll Specialist",managerId,"Associate");
        
        List<Employee> employees = Arrays.asList(employee1);

        Expense expense1 = new Expense(90299,"E1MNF0AE01F0TRNB","Relocation",Date.valueOf("2023-10-19"),Date.valueOf("2023-10-30"),
                18000,new byte[]{1,2,3},"Pending",null,"all expense",Date.valueOf("2023-11-27"),null);
        
        List<Expense> expenses = Arrays.asList(expense1);
        

        Optional<Employee> employeeOpt = Optional.ofNullable(new Employee());
        Mockito.when(employeeDao.findById(anyString())).thenReturn(employeeOpt);
        

        Mockito.when(employeeDao.findByManagerId(anyString())).thenReturn(employees);
        Mockito.when(expenseDao.findByEmpId(anyString())).thenReturn(expenses);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(ExpenseDto.class)))
                .thenReturn(
                new ExpenseDto(90299,"E1MNF0AE01F0TRNB","Relocation",Date.valueOf("2023-10-19"),Date.valueOf("2023-10-30"),
                        18000,new byte[]{1,2,3},"Pending",null,"all expense",Date.valueOf("2023-11-27"),null));

        List<ExpenseDto> result = managerServiceImpl.getAllExpenseByMgId(managerId);
        
        assertEquals(1, result.size()); // adjust based on your expected result
    }
    
    
    
    @Test
    void testGetAllExpenseByMgIdPending() {
       
        Employee employee = new Employee();
        employee.setEmpId("emp1");

        Expense expense = new Expense();
        expense.setStatus("Pending");
        
        Optional<Employee> employeeOpt = Optional.ofNullable(new Employee());
        Mockito.when(employeeDao.findById(anyString())).thenReturn(employeeOpt);


        Mockito.when(employeeDao.findByManagerId("manager1")).thenReturn(Collections.singletonList(employee));
        Mockito.when(expenseDao.findByEmpId("emp1")).thenReturn(Collections.singletonList(expense));
        Mockito.when(modelMapper.map(expense, ExpenseDto.class)).thenReturn(new ExpenseDto());
        
        
        List<ExpenseDto> result = managerServiceImpl.getAllExpenseByMgIdPending("manager1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllExpenseByMgIdPendingEmployeeIdNotMatch() {
       
        Employee employee = new Employee();
        employee.setEmpId("emp1");

        Expense expense = new Expense();
        expense.setStatus("Approved");

        Mockito.when(employeeDao.findByManagerId("manager1")).thenReturn(Collections.singletonList(employee));
        Mockito.when(expenseDao.findByEmpId("emp1")).thenReturn(Collections.singletonList(expense));

        EmployeeClassException exception = assertThrows(EmployeeClassException.class, () -> {
        	managerServiceImpl.getAllExpenseByMgIdPending("manager1");
        });
        assertEquals("Service.EMPLOYEE_ID_NOT_MATCH", exception.getMessage());
    }
   
    @Test
    void testGetExpenseById() {
        int expId = 1;
        Expense expectedExpense = new Expense(90299,"E1MNF0AE01F0TRNB","Relocation",Date.valueOf("2023-10-19"),Date.valueOf("2023-10-30"),
                18000,new byte[]{1,2,3},"Pending",null,"all expense",Date.valueOf("2023-11-27"),null);
        Mockito.when(expenseDao.findById(anyInt())).thenReturn(Optional.of(expectedExpense));
        
        Optional<Expense> result = managerServiceImpl.getExpensebyId(expId);
        
        assertEquals(Optional.of(expectedExpense), result);
    }

    
}