package com.employee.record.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.record.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
class EmployeeRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository repository;
	
	 private Date startDate;
	 private Date startDatelater;
	 private Employee employee1;
	private Employee employee2;
	
	@Test
	void testGetEmployees() throws ParseException {
		
		    startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			employee1 = new Employee( "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee( "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
		    entityManager.persist(employee1);
		    entityManager.persist(employee2);
		    entityManager.flush();
		    startDate =new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2018");

		  List<Employee> list=  repository.getEmployees(startDate, new BigDecimal(6000));
		  assertThat(list.size()).isEqualTo(3);
		  
			}
	
	@Test
	void testUpdateLocation() {		
		int recordUpdated = repository.updateLocation("Mumbai", "IT");
		Assert.assertTrue(recordUpdated==2);
		
		
	} 

}
