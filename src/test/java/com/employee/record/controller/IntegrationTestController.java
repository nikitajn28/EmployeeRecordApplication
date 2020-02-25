package com.employee.record.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.record.model.Employee;
import com.employee.record.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterUseCaseIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;
  
  @MockBean
  Employee repo;

  @Autowired
  private EmployeeRepository employeeRepo;
  private Date startDate;
	private Date startDatelater;
	private Employee employee1;
	private Employee employee2;
	
	@Before
    public void init() throws ParseException {
		
		 startDate =new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
			startDatelater =new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2009");
			employee1 = new Employee( "Arpit","Gangwal","IT",new BigDecimal(5000),startDate, "London");
		    employee2 = new Employee( "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee1));
        
    }


  @Test
  void registrationWorksThroughAllLayers() throws Exception {
	 
			
    mockMvc.perform(post("/employee/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(employee1)))
            .andExpect(status().isCreated());

    Optional<Employee> employee = employeeRepo.findById((long)01);
	assertNotNull(employee);
  }

}