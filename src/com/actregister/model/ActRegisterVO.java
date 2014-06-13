package com.actregister.model;


import java.sql.Timestamp;


public class ActRegisterVO implements java.io.Serializable{
	
	private  Integer actRegNo;
	private  Timestamp actRegDate;
	private  Timestamp actRegTime;
	private  String actRegPayState;
	private  String actRegName;
	private  Integer memNo;
	private  Integer actNo;
	
	public Integer getActRegNo() {
		return actRegNo;
	}
	public void setActRegNo(Integer actRegNo) {
		this.actRegNo = actRegNo;
	}
	public Timestamp getActRegDate() {
		return actRegDate;
	}
	public void setActRegDate(Timestamp actRegDate) {
		this.actRegDate = actRegDate;
	}
	public Timestamp getActRegTime() {
		return actRegTime;
	}
	public void setActRegTime(Timestamp actRegTime) {
		this.actRegTime = actRegTime;
	}
	public String getActRegPayState() {
		return actRegPayState;
	}
	public void setActRegPayState(String actRegPayState) {
		this.actRegPayState = actRegPayState;
	}
	public String getActRegName() {
		return actRegName;
	}
	public void setActRegName(String actRegName) {
		this.actRegName = actRegName;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getActNo() {
		return actNo;
	}
	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}
	
	
}
