package com.adp.S2B3T4_expense_system.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "S2_B3_T4_Employee")
public class Employee 
{
	@Id
    private String empId;
	private String empName;
	private String email;
	private String password;
	private String designation;
	private String managerId;
	private String role;
}