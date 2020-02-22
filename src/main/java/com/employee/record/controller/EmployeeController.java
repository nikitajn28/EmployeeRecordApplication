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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
@Api(value="employeemanagement", description="Operations to manage employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@Autowired 
	private LuckyDrawService service;
	/** Create LoggerFactory for UpdateAddressCommandHandler. */
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	  @ApiOperation(value = "View a list of available employees",response = List.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping(value = "/list")
	public ResponseEntity<List<Employee>> readAllRecord() {
		LOG.info("getEmployees/list service called");

		List<Employee> empList = (List<Employee>) repo.findAll();
		LOG.info("sucessfully fetched all the employee list");
		LOG.debug("sucessfully fetched all the employee list:", empList);

		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	    @ApiOperation(value = "Add a Employee")
	@PostMapping(value = "/add")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) {
		LOG.info("add Employee service called");
		LOG.debug(emp.toString());

		Employee e = repo.save(emp);

		LOG.info("Employee added successfully");
		LOG.debug("Employee added successfully", e);

		return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
	}
	    @ApiOperation(value = "Update employee")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> addEmployee(@Valid @PathVariable Long id, @RequestBody Employee emp) {
		if (repo.findById(id).isPresent()) {
			repo.save(emp);
			LOG.info("Employee updated succesfully");
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		} else {
			LOG.error("Employee by %Sid not found", id);
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);

		}
	}
	    @ApiOperation(value = "Delete employee")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			LOG.info("Employee deleted succesfully");
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else
			LOG.error("Employee by %Sid not found");
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	    @ApiOperation(value = "Search employee on basis of startdate and salary")
	@GetMapping(value = "/search")
	public ResponseEntity<List<Employee>> searchRecord(@RequestParam("startDate") String startDate,
			@RequestParam("salary") BigDecimal salary) throws ParseException {
		LOG.info("searchRecord service called");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		return new ResponseEntity<List<Employee>>(repo.getEmployees(date, salary), HttpStatus.OK);

	}
	    @ApiOperation(value = "update employee of particular department & update their location")
	@PutMapping(value = "/update/employees")
	public ResponseEntity<String> searchRecord(@RequestParam("officeLocation") String officeLocation,
			@RequestParam("department") String department) {
		int recordsUpdate = repo.updateLocation(officeLocation, department);

		return new ResponseEntity<String>("Numbe of records updated :" + recordsUpdate, HttpStatus.OK);

	}
	
	@GetMapping(value= "/luckyDraw")
	public ResponseEntity<Employee> luckyEmployee(){
		return new ResponseEntity<Employee>( service.getWinner(),HttpStatus.OK);
		
	}
}
