package com.shift.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class ShiftVO implements Serializable {
	private String shiftNo;
	private Date shiftDate;
	private Integer shiftMaximum;
	private String shiftPeriod;
	private String drNo;
	public String getShiftNo() {
		return shiftNo;
	}
	public void setShiftNo(String shiftNo) {
		this.shiftNo = shiftNo;
	}
	public Date getShiftDate() {
		return shiftDate;
		
	}
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	public Integer getShiftMaximum() {
		return shiftMaximum;
	}
	public void setShiftMaximum(Integer shiftMaximum) {
		this.shiftMaximum = shiftMaximum;
	}
	public String getShiftPeriod() {
		return shiftPeriod;
	}
	public void setShiftPeriod(String shiftPeriod) {
		this.shiftPeriod = shiftPeriod;
	}
	public String getDrNo() {
		return drNo;
	}
	public void setDrNo(String drNo) {
		this.drNo = drNo;
	}
	
}
