package com.emp.model;

import java.sql.Date;

public class EmployeeVO implements java.io.Serializable {
	private Integer empNo;
	private String empName;
	private Date empBirth;
	private String empTel;
	private String empSex;
	private String empPos;
	private Double empSalary;
	private Date empArrDate;
	private Date empOff;
	private String empID;
	private String empAdd;
	private byte[] empPic;
	private String empPassword;
	private String empEmail;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEmpBirth() {
		return empBirth;
	}

	public void setEmpBirth(Date empBirth) {
		this.empBirth = empBirth;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpPos() {
		return empPos;
	}

	public void setEmpPos(String empPos) {
		this.empPos = empPos;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double i) {
		this.empSalary = i;
	}

	public Date getEmpArrDate() {
		return empArrDate;
	}

	public void setEmpArrDate(Date empArrDate) {
		this.empArrDate = empArrDate;
	}

	public Date getEmpOff() {
		return empOff;
	}

	public void setEmpOff(Date empOff) {
		this.empOff = empOff;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpAdd() {
		return empAdd;
	}

	public void setEmpAdd(String empAdd) {
		this.empAdd = empAdd;
	}

	public byte[] getEmpPic() {
		return empPic;
	}

	public void setEmpPic(byte[] empPic) {
		this.empPic = empPic;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

}
