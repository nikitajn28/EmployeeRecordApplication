package com.employee.record.luckydraw.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.employee.record.model.Employee;
import com.employee.record.model.LuckyDraw;
import com.employee.record.repository.EmployeeRepository;
import com.employee.record.repository.LuckyDrawRepository;
@Component
public class LuckyDrawServiceImpl implements LuckyDrawService {
	
	private static final Logger LOG =LoggerFactory.getLogger(LuckyDrawServiceImpl.class);
	
	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private LuckyDrawRepository luckyDrawRepo;
	private Random rand;

	@Override
	//The following tasks are scheduled to be executed at 4:45 AM on the 10th day of every month:
	@Scheduled(cron = "0 45 4 10 * ?")
	public void getWinner() {
		LOG.info("******************Lucky Draw Started");
		List<Employee> employeeList = repo.findAll();
		rand = new Random();
		Employee e = employeeList.get(rand.nextInt(employeeList.size()));
		LuckyDraw luck = new LuckyDraw();
		Date date = new Date(System.currentTimeMillis());
		luck.setLuckyDrawDate(date); // Gets the current date and time
		luck.setId(e.getId());
		luckyDrawRepo.save(luck);
	LOG.debug("Inside getWinner ..Winner Employee is %s",e);
	}

}