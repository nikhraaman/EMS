package com.adp.S2B3T4_expense_system.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ExpenseDto 
{
	    private int expId;
	    private String empId;
	    private String expenseCategory;
	    private Date startDate;
	    private Date endDate;
	    private int amount;
	    private byte[] receipt;
	    private String status = "Pending";
	    private String managerRemarks;
	    private String employeeDescription;
	    private Date appliedDate;
	    private Date approvedDate;
		public int getExpId() {
			return expId;
		}
		public void setExpId(int expId) {
			this.expId = expId;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getExpenseCategory() {
			return expenseCategory;
		}
		public void setExpenseCategory(String expenseCategory) {
			this.expenseCategory = expenseCategory;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public byte[] getReceipt() {
			return receipt;
		}
		public void setReceipt(byte[] receipt) {
			this.receipt = receipt;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getManagerRemarks() {
			return managerRemarks;
		}
		public void setManagerRemarks(String managerRemarks) {
			this.managerRemarks = managerRemarks;
		}
		public String getEmployeeDescription() {
			return employeeDescription;
		}
		public void setEmployeeDescription(String employeeDescription) {
			this.employeeDescription = employeeDescription;
		}
		public Date getAppliedDate() {
			return appliedDate;
		}
		public void setAppliedDate(Date appliedDate) {
			this.appliedDate = appliedDate;
		}
		public Date getApprovedDate() {
			return approvedDate;
		}
		public void setApprovedDate(Date approvedDate) {
			this.approvedDate = approvedDate;
		}
	    
	    
}
