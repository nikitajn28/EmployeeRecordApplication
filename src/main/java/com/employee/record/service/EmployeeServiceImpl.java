package com.employee.record.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.record.controller.EmployeeController;
import com.employee.record.model.Employee;
import com.employee.record.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository repo;

	@Override
	public List<Employee> getAllEmployees() {
		LOG.info("getEmployees/list service called");

		List<Employee> empList = (List<Employee>) repo.findAll();
		LOG.info("sucessfully fetched all the employee list");
		LOG.debug("sucessfully fetched all the employee list:", empList);

		return empList;
	}

	@Override
	public Employee addEmployee(Employee emp) {
		LOG.info("add Employee service called");
		LOG.debug(emp.toString());

		Employee e = repo.save(emp);

		LOG.info("Employee added successfully");
		LOG.debug("Employee added successfully", e);
		return e;

	}

	@Override
	public Employee updateEmployee(Long id, Employee emp) {
		if (repo.findById(id).isPresent()) {
			Employee e =repo.save(emp);
			LOG.info("Employee updated succesfully");
			return e;
		} else {
			LOG.error("Employee by %s id not found", id);
			return null;
		}
		
	}

	@Override
	public String deleteEmployee(Long id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			LOG.info("Employee deleted succesfully");
			return ("Employee deleted succesfully");
	}else
		LOG.info("Employee not found");
		return ("Employee not found");
	}

	@Override
	public List<Employee> searchEmployees(String startDate, BigDecimal salary) throws ParseException {
		LOG.info("searchEmployees service called");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		LOG.debug("searchEmployees on basis of %s startdate and %s salary", startDate, salary);
		return (repo.getEmployees(date, salary));
	}

	@Override
	public String updateEmployees(String officeLocation, String department) {
		LOG.info("updateEmployees service called");
		int recordsUpdate = repo.updateLocation(officeLocation, department);
		LOG.debug("update location to %s of employees of %s  department", officeLocation, department);
		return "Number of records updated :" + recordsUpdate;
	}
	
	
}
