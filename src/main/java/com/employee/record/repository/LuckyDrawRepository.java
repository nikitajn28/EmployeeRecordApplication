package com.employee.record.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.record.model.LuckyDraw;

public interface LuckyDrawRepository extends JpaRepository<LuckyDraw, Date>{

}
