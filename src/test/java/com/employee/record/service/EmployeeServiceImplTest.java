package com.employee.record.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.employee.record.model.Employee;
import com.employee.record.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository repo;

	@InjectMocks
	private EmployeeServiceImpl employeeService;
	private Date startDate;
	private Date startDatelater;
	private Employee employee1;
	private Employee employee2;

	@Before
	public void init() throws ParseException {
		MockitoAnnotations.initMocks(this);

		/*
		 * startDate =new
		 * SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
		 * startDatelater =new
		 * SimpleDateFormat("dd/MM/yyyy").parse("2018-11-12T00:00:00.000+0000");
		 * employee1 = new Employee(new Long(1), "Arpit","Gangwal","IT",new
		 * BigDecimal(5000),startDate, "London"); employee2 = new Employee(new Long(2),
		 * "Nikita","Gangwal","HR",new BigDecimal(7000),startDatelater, "Paris");
		 */
	}

	@Test
	void getAllEmployeesTest() throws ParseException {
		startDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		startDatelater = new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2009");
		employee1 = new Employee("Arpit", "Gangwal", "IT", new BigDecimal(5000), startDate, "London");
		employee2 = new Employee("Nikita", "Gangwal", "HR", new BigDecimal(7000), startDatelater, "Paris");

		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);

		when(repo.findAll()).thenReturn(employees);

		// when
		List<Employee> list = employeeService.getAllEmployees();
		// then
		assertThat(list.size()).isEqualTo(2);

		assertThat(list.get(0).getFirstName()).isEqualTo(employee1.getFirstName());

		assertThat(list.get(1).getFirstName()).isEqualTo(employee2.getFirstName());
	}

	@Test
	public void testAddEmployee() throws ParseException {
		startDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		employee1 = new Employee(new Long(1), "Arpit", "Gangwal", "IT", new BigDecimal(5000), startDate, "London");

		when(repo.save(employee1)).thenReturn(employee1);

		Employee responseEntity = employeeService.addEmployee(employee1);

		assertThat(responseEntity.getId()).isEqualTo(1);
		assertThat(responseEntity.getFirstName()).isEqualTo("Arpit");
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Test public void testUpdateEmployee() throws ParseException { startDate =
	 * new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018"); employee1 = new
	 * Employee(new Long(1), "Arpit", "Gangwal", "IT", new BigDecimal(5000),
	 * startDate, "London");
	 * 
	 * when(repo.save(employee1)).thenReturn(employee1);
	 * 
	 * Employee responseEntity1 = employeeService.updateEmployee((long) 1,
	 * employee1); assertThat(responseEntity1.getId()).isEqualTo((long) 01);
	 * 
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void testdeleteEmployee() throws ParseException {

		String responseEntity = employeeService.deleteEmployee((long) 1);

		assertThat(!responseEntity.isEmpty());

	}

	@Test
	public void testSearchEmployees() throws ParseException {
		startDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		startDatelater = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);

		employee1 = new Employee(new Long(1), "Arpit", "Gangwal", "IT", new BigDecimal(5000), startDate, "London");
		employee2 = new Employee(new Long(2), "Nikita", "Gangwal", "HR", new BigDecimal(7000), startDatelater, "Paris");

		when(repo.getEmployees(startDate, new BigDecimal(20000))).thenReturn(employeeList);
		List<Employee> list = employeeService.searchEmployees("12/11/2018", new BigDecimal(20000));

		assertThat(list.size()).isEqualTo(2);
		assertNotNull(list);

	}

	@Test
	public void testUpdateEmployees() throws ParseException {
		startDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		startDatelater = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2018");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);

		employee1 = new Employee(new Long(1), "Arpit", "Gangwal", "IT", new BigDecimal(5000), startDate, "London");
		employee2 = new Employee(new Long(2), "Nikita", "Gangwal", "HR", new BigDecimal(7000), startDatelater, "Paris");

		when(repo.updateLocation("London", "IT")).thenReturn(2);
		String responseEntity = employeeService.updateEmployees("London", "IT");

		assertNotNull(responseEntity);

	}

}
