package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable{
	private Integer memno;
	private String memid;
	private String mempassword;
	private String memname;
	private String memidno;
	private String mememail;
	private Date membirth;
	private String memadd;
	private Integer memsex;
	private String memtel;
	private Integer memstate;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getMempassword() {
		return mempassword;
	}
	public void setMempassword(String mempassword) {
		this.mempassword = mempassword;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public String getMemidno() {
		return memidno;
	}
	public void setMemidno(String memidno) {
		this.memidno = memidno;
	}
	public String getMememail() {
		return mememail;
	}
	public void setMememail(String mememail) {
		this.mememail = mememail;
	}
	public Date getMembirth() {
		return membirth;
	}
	public void setMembirth(Date membirth) {
		this.membirth = membirth;
	}
	public String getMemadd() {
		return memadd;
	}
	public void setMemadd(String memadd) {
		this.memadd = memadd;
	}
	public Integer getMemsex() {
		return memsex;
	}
	public void setMemsex(Integer memsex) {
		this.memsex = memsex;
	}
	public String getMemtel() {
		return memtel;
	}
	public void setMemtel(String memtel) {
		this.memtel = memtel;
	}
	public Integer getMemstate() {
		return memstate;
	}
	public void setMemstate(Integer memstate) {
		this.memstate = memstate;
	}
	
	
	
}
