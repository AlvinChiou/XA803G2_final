package com.pet.model;


import java.sql.Date;


public class PetVO implements java.io.Serializable{
	private  Integer petNo;
	private  String petName;
	private  String petSex;
	private  String petType;
	private  byte[] petPic;
	private  Double petAge;
	private  Integer memNo;
	
	public Integer getPetNo() {
		return petNo;
	}
	public void setPetNo(Integer petNo) {
		this.petNo = petNo;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetSex() {
		return petSex;
	}
	public void setPetSex(String petSex) {
		this.petSex = petSex;
	}
	public String getPetType() {
		return petType;
	}
	public void setPetType(String petType) {
		this.petType = petType;
	}
	public byte[] getPetPic() {
		return petPic;
	}
	public void setPetPic(byte[] petPic) {
		this.petPic = petPic;
	}
	public Double getPetAge() {
		return petAge;
	}
	public void setPetAge(Double petAge) {
		this.petAge = petAge;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	
	
	
	
	
}
