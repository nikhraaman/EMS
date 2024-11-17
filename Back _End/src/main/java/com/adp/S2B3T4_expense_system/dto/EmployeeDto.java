package com.adp.S2B3T4_expense_system.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDto 
{
	private String empId;
	private String empName;
	private String email;
	private String password;
	private String designation;
	private String managerId;
	private String role;
}