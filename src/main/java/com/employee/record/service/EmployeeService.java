package com.employee.record.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.record.model.Employee;

public interface EmployeeService {
	
	
	public List<Employee> getAllEmployees();
	public Employee addEmployee(Employee emp);
	public Employee updateEmployee(Long id,Employee emp);
	public String deleteEmployee(Long id);
	public List<Employee> searchEmployees(String startDate,BigDecimal salary) throws ParseException;
	public String updateEmployees(String officeLocation, String department);





}
