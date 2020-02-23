package com.employee.record.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.record.model.Employee;


@Transactional
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	private static final Logger LOG= LoggerFactory.getLogger(EmployeeRepositoryImpl.class);


	@Override
	public List<Employee> getEmployees(Date startDate, BigDecimal salary) {		
		String hql = "FROM Employee as er WHERE er.startDate > :startDate and er.salary> :salary";
		Query q = entityManager.createQuery(hql);
		q.setParameter("startDate", startDate);
		q.setParameter("salary", salary);
		LOG.debug("getEmployees whose startdate greater than %s and salary greater than %s", startDate, salary);
		return (List<Employee>)q.getResultList();
	}

	@Override
	public int updateLocation(String officeLocation, String department) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("update Employee set officeLocation = :officeLocation" +
				" where lower(department) = :department");	
		query.setParameter("officeLocation", officeLocation);
		query.setParameter("department", department.toLowerCase());
		LOG.debug("update location to %s of employees of %s  department", officeLocation, department);
		return query.executeUpdate();

	}

	
	
}
