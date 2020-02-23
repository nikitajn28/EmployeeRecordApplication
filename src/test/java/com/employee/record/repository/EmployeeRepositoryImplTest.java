package com.employee.record.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.employee.record.model.Employee;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeeRepositoryImplTest {
	
    @InjectMocks
	 private EmployeeRepository employeeRepositoryCustom;
    
    @Mock
    EntityManager em;
    
    @Mock
    Query q;
    	 
	 private Date startDate;
	 private Date startDatelater;
	 private Employee employee1;
		private Employee employee2;
	 @Before
	    public void init() throws ParseException {
	        MockitoAnnotations.initMocks(this);   
	        when( em.createQuery(anyString())).thenReturn(q);

	        startDate =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-11T00:00:00.000+0000");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
			employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee(new Long(2), "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
	    }
	
	@Test
	void testGetEmployees() throws ParseException {
		
		List<Employee> employess =new ArrayList<>();
		employess.add(employee2);
		when(q.getResultList()).thenReturn(employess);
		 List<Employee> list = employeeRepositoryCustom.getEmployees(startDate, new BigDecimal(4000));
		Assert.assertTrue(list.size()==1);
		assertThat(list.get(0)).isEqualTo(employess.get(0));

	} 
	
	@Test
	void testUpdateLocation() {		
		when(q.executeUpdate()).thenReturn(1);
		int recordUpdated = employeeRepositoryCustom.updateLocation("Mumbai", "IT");
		Assert.assertTrue(recordUpdated==1);
		
		
	} 
		

}
