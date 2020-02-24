package com.employee.record.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.record.luckydraw.service.LuckyDrawService;
import com.employee.record.model.Employee;
import com.employee.record.repository.EmployeeRepository;
import com.employee.record.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	/** Create LoggerFactory for UpdateAddressCommandHandler. */
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	
	@GetMapping(value = "/list" ,  produces="application/json")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		LOG.info("getEmployees/list service called");
		return new ResponseEntity<List<Employee>>(service.getAllEmployees(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) {
		LOG.info("Employee added successfully");
		return new ResponseEntity<Employee>(service.addEmployee(emp), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable Long id, @RequestBody Employee emp) {
			LOG.info("Employee updated succesfully");
			return new ResponseEntity<Employee>(service.updateEmployee(id, emp),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		LOG.info("Employee delete service called");
	   return new ResponseEntity<String>(service.deleteEmployee(id),HttpStatus.OK);
	}
    
	@GetMapping(value = "/search")
	public ResponseEntity<List<Employee>> searchEmployees(@RequestParam("startDate") String startDate,
			@RequestParam("salary") BigDecimal salary) throws ParseException {
		LOG.info("searchEmployees service called");
		return new ResponseEntity<List<Employee>>(service.searchEmployees(startDate, salary), HttpStatus.OK);

	}
	
	@PutMapping(value = "/update/employees")
	public ResponseEntity<String> updateEmployees(@RequestParam("officeLocation") String officeLocation,
			@RequestParam("department") String department) {
		LOG.info("updateEmployees service called");
		return new ResponseEntity<String>(service.updateEmployees(officeLocation, department), HttpStatus.OK);
	}
	
}
