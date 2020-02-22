package com.employee.record.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="LUCKYDRAW")
public class LuckyDraw {

	
@Id
@Column(name = "SEQUENCENO")
@GeneratedValue(strategy = GenerationType.AUTO)
private Long LuckyDrawNumber;

@Column(name="LUCKYDRAWDATE")
private Date luckyDrawDate;

@Column(name="ID")
private long id;

public Long getLuckyDrawNumber() {
	return LuckyDrawNumber;
}

public void setLuckyDrawNumber(Long luckyDrawNumber) {
	LuckyDrawNumber = luckyDrawNumber;
}

public Date getLuckyDrawDate() {
	return luckyDrawDate;
}

public void setLuckyDrawDate(Date luckyDrawDate) {
	this.luckyDrawDate = luckyDrawDate;
}

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "ID")
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}


}
