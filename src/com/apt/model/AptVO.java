package com.apt.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class AptVO implements Serializable {
	private String aptNo;
	private Date aptDate;
	private String aptPeriod;
	private Integer aptNoSlip;
	private Timestamp aptRegTime;
	private String petNo;
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public Date getAptDate() {
		return aptDate;
	}
	public void setAptDate(Date aptDate) {
		this.aptDate = aptDate;
	}
	public String getAptPeriod() {
		return aptPeriod;
	}
	public void setAptPeriod(String aptPeriod) {
		this.aptPeriod = aptPeriod;
	}
	public Integer getAptNoSlip() {
		return aptNoSlip;
	}
	public void setAptNoSlip(Integer aptNoSlip) {
		this.aptNoSlip = aptNoSlip;
	}
	public Timestamp getAptRegTime() {
		return aptRegTime;
	}
	public void setAptRegTime(Timestamp aptRegTime) {
		this.aptRegTime = aptRegTime;
	}
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}

}