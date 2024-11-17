package com.adp.S2B3T4_expense_system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.adp.S2B3T4_expense_system.entity.Expense;

@Repository
public interface ExpenseDao extends JpaRepository<Expense,Integer>{

	 Iterable<Expense> findByEmpId(String empId);
	
	 @Query(nativeQuery = true, value = "select sum(amount) from S2_B3_T4_Expense group by emp_id, status,extract(month from applied_date),extract(year from applied_date) having status = ?1 and emp_id = ?2 and extract(month from applied_date)= ?3 and extract(year from applied_date)= ?4")
	 List<Float> getAmountByStatusByMonth(@Param("status") String status, @Param("emp_id") String emp_id,@Param("extract(month from applied_date)")int month,@Param("extract(year from applied_date)")int year);
}
