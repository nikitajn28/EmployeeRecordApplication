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
@Column(name="LUCKYDRAWDATE")
private Date luckyDrawDate;

@Column(name="WINNER_EMPID")
private long id;

public Date getLuckyDrawDate() {
	return luckyDrawDate;
}

public void setLuckyDrawDate(Date luckyDrawDate) {
	this.luckyDrawDate = luckyDrawDate;
}


public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}


}
