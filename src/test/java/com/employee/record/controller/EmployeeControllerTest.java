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
import org.springframework.http.ResponseEntity;

import com.employee.record.model.Employee;
import com.employee.record.repository.EmployeeRepository;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeeControllerTest {
	
	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	EmployeeController employeeController;
	private Date startDate;
	private Date startDatelater;
	private Employee employee1;
	private Employee employee2;
	
	 
	 @Before
	    public void init() throws ParseException {
	        MockitoAnnotations.initMocks(this);    
	        startDate =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee(new Long(2), "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
	    }

	@Test
	void getAllEmployeesTest() {
	        List<Employee> employees = new ArrayList<>();
	        employees.add(employee1);
	        employees.add(employee2);  
	 
	        when(repo.findAll()).thenReturn(employees);
	 
	        // when
	        List<Employee>  result = (List<Employee>) employeeController.getAllEmployees();
	 
	        // then
	        assertThat(result.size()).isEqualTo(2);
	         
	        assertThat(result.get(0).getEmployeeFirstName())
	                        .isEqualTo(employee1.getEmployeeFirstName());
	         
	        assertThat(result.get(1).getEmployeeFirstName())
            .isEqualTo(employee2.getEmployeeFirstName());
	}
	
	@Test
    public void testAddEmployee() 
    {
        
        when(repo.save(employee1)).thenReturn(employee1);
         
        ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employee1);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(employee1);
    }
	
	@SuppressWarnings("unchecked")
	@Test
    public void testUpdateEmployee() 
    {
        when(repo.save(employee1)).thenReturn(employee1);
        when(repo.findById((long) 2).isPresent()).thenReturn(false);
        when(repo.findById((long) 1).isPresent()).thenReturn(true);

         
        ResponseEntity<Employee> responseEntity = (ResponseEntity<Employee>) employeeController.updateEmployee((long) 2, employee2);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
      		ResponseEntity<Employee> responseEntity1 = (ResponseEntity<Employee>) employeeController.updateEmployee((long) 2, employee2);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(202);

        assertThat(responseEntity1.getBody()).isEqualTo(employee1);
    }
	
	@SuppressWarnings("unchecked")
	@Test
    public void testdeleteEmployee() 
    {
        when(repo.findById((long) 2).isPresent()).thenReturn(false);
        when(repo.findById((long) 1).isPresent()).thenReturn(true);
 
        ResponseEntity<Employee> responseEntity = (ResponseEntity<Employee>) employeeController.deleteEmployee((long) 2);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        
        ResponseEntity<Employee> responseEntity1 = (ResponseEntity<Employee>) employeeController.deleteEmployee((long) 1);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(202);

    }
	
	@Test 
	public void testSearchEmployees() {
		
		
	}


}
