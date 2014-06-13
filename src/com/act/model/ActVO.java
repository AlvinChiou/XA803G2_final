package com.act.model;


import java.sql.Date;
import java.sql.Timestamp;


public class ActVO implements java.io.Serializable{
	private  Integer actNo;
	private  String actName;
	private  String actContent;
	private  Timestamp actStartTime;
	private  Timestamp actEndTime;
	private  byte[] actPic;
	private  String actEquipment;
	private  double actDeposit;
	private  double actHostFee;
	private  double actRegFee;
	private  String actStatus;
	private  Integer memNo;
	private  Integer empNo;
	
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public Integer getActNo() {
		return actNo;
	}
	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActContent() {
		return actContent;
	}
	public void setActContent(String actContent) {
		this.actContent = actContent;
	}
	public Timestamp getActStartTime() {
		return actStartTime;
	}
	public void setActStartTime(Timestamp actStartTime) {
		this.actStartTime = actStartTime;
	}
	public Timestamp getActEndTime() {
		return actEndTime;
	}
	public void setActEndTime(Timestamp actEndTime) {
		this.actEndTime = actEndTime;
	}
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
	public String getActEquipment() {
		return actEquipment;
	}
	public void setActEquipment(String actEquipment) {
		this.actEquipment = actEquipment;
	}
	public double getActDeposit() {
		return actDeposit;
	}
	public void setActDeposit(double actDeposit) {
		this.actDeposit = actDeposit;
	}
	public double getActHostFee() {
		return actHostFee;
	}
	public void setActHostFee(double actHostFee) {
		this.actHostFee = actHostFee;
	}
	public double getActRegFee() {
		return actRegFee;
	}
	public void setActRegFee(double actRegFee) {
		this.actRegFee = actRegFee;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getActStatus() {
		return actStatus;
	}
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}
	
	
	
	
}
