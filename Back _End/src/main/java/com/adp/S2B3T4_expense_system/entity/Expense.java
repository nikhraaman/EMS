package com.adp.S2B3T4_expense_system.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "S2_B3_T4_Expense")
public class Expense 
{
	
    @Id
    @Column(name="exp_id")
    private int expId;
    @Column(name="emp_id")
    private String empId;
    @Column(name="expense_Category")
    private String expenseCategory;
    @Column(name="start_Date")
    private Date startDate;
    @Column(name="end_Date")
    private Date endDate;
    @Column(name="amount")
    private int amount;
    @Lob
    private byte[] receipt;
    @Column(name="status")
    private String status = "Pending";
    @Column(name="manager_Remarks")
    private String managerRemarks;
    @Column(name="employee_Description")
    private String employeeDescription;
    @Column(name="applied_Date")
    private Date appliedDate;
    @Column(name="approved_Date")
    private Date approvedDate;
	public Expense(int expId, String empId, String expenseCategory, Date startDate, Date endDate, int amount,
			byte[] receipt, String status, String managerRemarks, String employeeDescription, Date appliedDate,
			Date approvedDate) {
		super();
		this.expId = expId;
		this.empId = empId;
		this.expenseCategory = expenseCategory;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.managerRemarks = managerRemarks;
		this.employeeDescription = employeeDescription;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
	}

    

    
}
