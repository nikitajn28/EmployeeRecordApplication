package com.employee.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.record.model.LuckyDraw;

public interface LuckyDrawRepository extends JpaRepository<LuckyDraw, Long>{

}
