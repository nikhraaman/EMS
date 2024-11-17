package com.adp.S2B3T4_expense_system.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.adp.S2B3T4_expense_system.entity.Expense;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExpenseDaoTest {


    @Mock
    private ExpenseDao expenseDao;
    
    @Test
    public void testFindByEmpId() {
        // Mock data
        String empId = "E1H0A4TVX77D109D";
        Expense expense1 = new Expense(90299,"E1H0A4TVX77D109D","Relocation",Date.valueOf("2023-10-19"),Date.valueOf("2023-10-30"),
                18000,new byte[]{1,2,3},"Pending",null,"all expense",Date.valueOf("2023-11-27"),null);
        Expense expense2 = new Expense(98918,"E1H0A4TVX77D109D","Cab",Date.valueOf("2023-10-25"),Date.valueOf("2023-11-25"),
                2700,new byte[]{1,2,3},"Pending",null,"regular cab",Date.valueOf("2023-11-27"),null);
        
        when(expenseDao.findByEmpId(empId)).thenReturn(List.of(expense1,expense2));
        Iterable<Expense> result = expenseDao.findByEmpId(empId);

        assertThat(result).containsExactlyInAnyOrder(expense1,expense2 );
    }
}
