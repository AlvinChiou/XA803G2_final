package com.drRecord.model;


import java.sql.Date;


public class DrRecordVO implements java.io.Serializable{
	private  Integer drRecNo;
	private  Date drRecTime;
	private  String drRecPres;
	private  Integer drNo;
	private  Integer petNo;
	
	public Integer getDrRecNo() {
		return drRecNo;
	}
	public void setDrRecNo(Integer drRecNo) {
		this.drRecNo = drRecNo;
	}
	public Date getDrRecTime() {
		return drRecTime;
	}
	public void setDrRecTime(Date drRecTime) {
		this.drRecTime = drRecTime;
	}
	public String getDrRecPres() {
		return drRecPres;
	}
	public void setDrRecPres(String drRecPres) {
		this.drRecPres = drRecPres;
	}
	public Integer getDrNo() {
		return drNo;
	}
	public void setDrNo(Integer drNo) {
		this.drNo = drNo;
	}
	public Integer getPetNo() {
		return petNo;
	}
	public void setPetNo(Integer petNo) {
		this.petNo = petNo;
	}
	
	
	
	
	
}
