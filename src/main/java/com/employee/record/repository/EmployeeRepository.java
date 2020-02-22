package com.employee.record.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.employee.record.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,EmployeeRepositoryCustom {
	/*
	
 */
}
