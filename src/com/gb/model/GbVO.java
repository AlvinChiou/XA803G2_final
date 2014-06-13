package com.gb.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class GbVO implements Serializable  {
	private Integer gbno;
	private String gbtitle;
	private String gbcontent;
	private Timestamp gbtime;
	private Integer lostno;
	private Integer memno;
	
	public Integer getGbno() {
		return gbno;
	}
	public void setGbno(Integer gbno) {
		this.gbno = gbno;
	}
	public String getGbtitle() {
		return gbtitle;
	}
	public void setGbtitle(String gbtitle) {
		this.gbtitle = gbtitle;
	}
	public String getGbcontent() {
		return gbcontent;
	}
	public void setGbcontent(String gbcontent) {
		this.gbcontent = gbcontent;
	}
	public Timestamp getGbtime() {
		return gbtime;
	}
	public void setGbtime(Timestamp gbtime) {
		this.gbtime = gbtime;
	}
	public Integer getLostno() {
		return lostno;
	}
	public void setLostno(Integer lostno) {
		this.lostno = lostno;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	
}
