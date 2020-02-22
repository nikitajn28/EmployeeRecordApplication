package com.employee.record.luckydraw.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.record.model.Employee;
import com.employee.record.model.LuckyDraw;
import com.employee.record.repository.EmployeeRepository;
import com.employee.record.repository.LuckyDrawRepository;
@Service
public class LuckyDrawServiceImpl implements LuckyDrawService {
	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private LuckyDrawRepository luckyDrawRepo;
	private Random rand;

	@Override
	public Employee getWinner() {
		List<Employee> employeeList = repo.findAll();
		rand = new Random();
		Employee e = employeeList.get(rand.nextInt(employeeList.size()));
		LuckyDraw luck = new LuckyDraw();
		Date date = new Date(System.currentTimeMillis());
		luck.setLuckyDrawDate(date); // Gets the current date and time
		luck.setId(e.getEmployeeId());
		luckyDrawRepo.save(luck);
		return e;
	}

}