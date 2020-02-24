package com.employee.record.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Employee")
public class Employee {

@Id
@Column(name = "ID")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;

@Column(name="FIRSTNAME")
private String firstName;

@Column(name="LASTNAME")
private String lastName;

@Column(name="DEPARTMENT")
private String department;

@Column(name="SALARY")

private BigDecimal salary;

@Column(name="STARTDATE")
private Date startDate;

@Column(name="OFFICELOCATION")

private String officeLocation;

public Employee() {
	super();
}


public Employee(Long id, String firstName, String lastName, String department, BigDecimal salary, Date startDate,
		String officeLocation) {
	super();
	Id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.department = department;
	this.salary = salary;
	this.startDate = startDate;
	this.officeLocation = officeLocation;
}




public Employee( String firstName, String lastName, String department, BigDecimal salary, Date startDate,
		String officeLocation) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.department = department;
	this.salary = salary;
	this.startDate = startDate;
	this.officeLocation = officeLocation;
}


public Long getId() {
	return Id;
}




public void setId(Long id) {
	Id = id;
}




public String getFirstName() {
	return firstName;
}




public void setFirstName(String firstName) {
	this.firstName = firstName;
}




public String getLastName() {
	return lastName;
}




public void setLastName(String lastName) {
	this.lastName = lastName;
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
	return "Employee [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", department=" + department
			+ ", salary=" + salary + ", startDate=" + startDate + ", officeLocation=" + officeLocation + "]";
}




@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Id == null) ? 0 : Id.hashCode());
	result = prime * result + ((department == null) ? 0 : department.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((officeLocation == null) ? 0 : officeLocation.hashCode());
	result = prime * result + ((salary == null) ? 0 : salary.hashCode());
	result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
	return result;
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
	if (Id == null) {
		if (other.Id != null)
			return false;
	} else if (!Id.equals(other.Id))
		return false;
	if (department == null) {
		if (other.department != null)
			return false;
	} else if (!department.equals(other.department))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	if (officeLocation == null) {
		if (other.officeLocation != null)
			return false;
	} else if (!officeLocation.equals(other.officeLocation))
		return false;
	if (salary == null) {
		if (other.salary != null)
			return false;
	} else if (!salary.equals(other.salary))
		return false;
	if (startDate == null) {
		if (other.startDate != null)
			return false;
	} else if (!startDate.equals(other.startDate))
		return false;
	return true;
}











}
