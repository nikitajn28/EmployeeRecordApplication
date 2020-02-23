package com.employee.record.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="Employee")
@ApiModel("Employee")
public class Employee {

@Id
@Column(name = "ID")
@GeneratedValue(strategy = GenerationType.AUTO)
@ApiModelProperty("The database generated product ID")
private Long Id;

@Column(name="FIRSTNAME")
@ApiModelProperty("First Name of Employee")
private String firstName;

@Column(name="LASTNAME")
@ApiModelProperty("Last Name of Employee")
private String lastName;

@Column(name="DEPARTMENT")
@ApiModelProperty("Deparment Name of Employee")
private String department;

@Column(name="SALARY")
@ApiModelProperty("Salary Name of Employee")

private BigDecimal salary;

@Column(name="STARTDATE")
@ApiModelProperty("Start date of Employee")
private Date startDate;

@Column(name="OFFICELOCATION")
@ApiModelProperty("Office location of Employee")

private String officeLocation;


@Override
public int hashCode() {
	return Objects.hash(Id, department, firstName, lastName, officeLocation, salary, startDate);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;
	return Objects.equals(Id, other.Id) && Objects.equals(department, other.department)
			&& Objects.equals(firstName, other.firstName)
			&& Objects.equals(lastName, other.lastName)
			&& Objects.equals(officeLocation, other.officeLocation) && Objects.equals(salary, other.salary)
			&& Objects.equals(startDate, other.startDate);
}

public Employee() {
	super();
}

public Employee(Long employeeId, String employeeFirstName, String employeeLastName, String department,
		BigDecimal salary, Date startDate, String officeLocaion) {
	super();
	this.Id = employeeId;
	this.firstName = employeeFirstName;
	this.lastName = employeeLastName;
	this.department = department;
	this.salary = salary;
	this.startDate=startDate;
	this.officeLocation=officeLocaion;
}

public Long getEmployeeId() {
	return Id;
}

public void setEmployeeId(Long employeeId) {
	this.Id = employeeId;
}

public String getEmployeeFirstName() {
	return firstName;
}

public void setEmployeeFirstName(String employeeFirstName) {
	this.firstName = employeeFirstName;
}

public String getEmployeeLastName() {
	return lastName;
}

public void setEmployeeLastName(String employeeLastName) {
	this.lastName = employeeLastName;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public BigDecimal getSalary() {
	return salary;
}

public void setSalary(BigDecimal salary) {
	this.salary = salary;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public String getOfficeLocation() {
	return officeLocation;
}

public void setOfficeLocation(String officeLocation) {
	this.officeLocation = officeLocation;
}

@Override
public String toString() {
	return "Employee [employeeId=" + Id + ", name=" + firstName
			+ lastName + ", department=" + department + ", salary=" + salary + ", startDate=" + startDate
			+ ", officeLocation=" + officeLocation + "]";
}





}
