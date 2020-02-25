package com.employee.record.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.record.model.Employee;
import com.employee.record.service.EmployeeService;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeeControllerTest {
	
	@Mock
	private EmployeeService service;
	
	
	@InjectMocks
	EmployeeController employeeController;
	private Date startDate;
	private Date startDatelater;
	private Employee employee1;
	private Employee employee2;
	
	 
	 @Before
	    public void init() throws ParseException {
	        MockitoAnnotations.initMocks(this);
	        
	     /*   startDate =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee(new Long(2), "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris"); */
	    }

	@Test
	void getAllEmployeesTest() throws ParseException {
        startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2009");
		employee1 = new Employee( "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
	    employee2 = new Employee( "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
		
	        List<Employee> employees = new ArrayList<>();
	        employees.add(employee1);
	        employees.add(employee2);  
	 
	        when(service.getAllEmployees()).thenReturn(employees);
	 
	        // when
	        ResponseEntity<List<Employee>> responseEntity =  employeeController.getAllEmployees();	 
	        // then
	        assertThat(responseEntity.getBody().size()).isEqualTo(2);
	         
	        assertThat(responseEntity.getBody().get(0).getFirstName())
	                        .isEqualTo(employee1.getFirstName());
	         
	        assertThat(responseEntity.getBody().get(1).getFirstName())
            .isEqualTo(employee2.getFirstName());
	}
	
	@Test
    public void testAddEmployee() throws ParseException 
    {
		startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
	   
        when(service.addEmployee(employee1)).thenReturn(employee1);
         
        ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employee1);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(employee1);
    }
	
	@SuppressWarnings("unchecked")
	@Test
    public void testUpdateEmployee() throws ParseException 
    {
		 startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
			
        when(service.updateEmployee((long)1,employee1)).thenReturn(employee1);
     
      	ResponseEntity<Employee> responseEntity1 = (ResponseEntity<Employee>) employeeController.updateEmployee((long) 1, employee1);
        assertThat(responseEntity1.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
	
	@SuppressWarnings("unchecked")
	@Test
    public void testdeleteEmployee() throws ParseException 
    {
		 
      
        when(service.deleteEmployee((long)1)).thenReturn("Employee deleted succesfully");

 
        ResponseEntity<String> responseEntity = (ResponseEntity<String>) employeeController.deleteEmployee((long) 1);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        

    }
	
	@Test 
	public void testSearchEmployees() throws ParseException {
		 startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee1);
			employeeList.add(employee2);

			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee(new Long(2), "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
   
     when(service.searchEmployees("12/11/2018", new BigDecimal(20000))).thenReturn(employeeList);
     ResponseEntity<List<Employee>> responseEntity =  employeeController.searchEmployees("12/11/2018", new BigDecimal(20000));

     assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

		
	}
	
	@Test 
	public void testUpdateEmployees() throws ParseException {
		 startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee1);
			employeeList.add(employee2);

			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee(new Long(2), "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
   
     when(service.updateEmployees("London", "IT")).thenReturn("Number of records updated");
     ResponseEntity<String> responseEntity = (ResponseEntity<String>) employeeController.updateEmployees("London", "IT");

     assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

		
	}


}
