package com.employee.record.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.employee.record.model.Employee;

public interface EmployeeRepositoryCustom {
	
    List<Employee> getEmployees(Date startDate, BigDecimal salary);
	
	// * List<Employee> getEmployees(Date startDate, BigDecimal salary); void
	 //* updateLocation(String Location, String Department); Employee getWinner();
	

	int updateLocation(String officeLocation, String department);

	//Optional<Employee> findOne(Specification<Employee> spec);
}
