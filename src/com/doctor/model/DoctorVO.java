package com.doctor.model;

import java.sql.Date;

public class DoctorVO implements java.io.Serializable {
	private Integer drNo;
	private String drName;
	private String drExp;
	private String drSex;
	private byte[] drPic;
	private Date drBirth;
	private String drAdd;
	private String drTel;

	public Integer getDrNo() {
		return drNo;
	}

	public void setDrNo(Integer drNo) {
		this.drNo = drNo;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getDrExp() {
		return drExp;
	}

	public void setDrExp(String drExp) {
		this.drExp = drExp;
	}

	public String getDrSex() {
		return drSex;
	}

	public void setDrSex(String drSex) {
		this.drSex = drSex;
	}

	public byte[] getDrPic() {
		return drPic;
	}

	public void setDrPic(byte[] drPic) {
		this.drPic = drPic;
	}

	public Date getDrBirth() {
		return drBirth;
	}

	public void setDrBirth(Date drBirth) {
		this.drBirth = drBirth;
	}

	public String getDrAdd() {
		return drAdd;
	}

	public void setDrAdd(String drAdd) {
		this.drAdd = drAdd;
	}

	public String getDrTel() {
		return drTel;
	}

	public void setDrTel(String drTel) {
		this.drTel = drTel;
	}
}
