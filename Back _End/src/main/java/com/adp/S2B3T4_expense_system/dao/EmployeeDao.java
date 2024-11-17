package com.adp.S2B3T4_expense_system.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.adp.S2B3T4_expense_system.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,String>
{
	Iterable<Employee> findByManagerId(String empId);
	
	@Query(nativeQuery = true, value = "select * from S2_B3_T4_Employee where manager_Id = ?1")
	    public List<Employee> findEmpByManagerId(@Param("managerId") String managerId);
}

