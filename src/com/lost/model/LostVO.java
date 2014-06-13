package com.lost.model;

import java.io.Serializable;
import java.sql.Date;

public class LostVO implements Serializable {
	private Integer lostno;
	private String losttitle;
	private byte[] lostpic;
	private String lostcontent;
	private Date losttime;
	private Integer loststate;
	private Integer memno;
	
	public Integer getLostno() {
		return lostno;
	}
	public void setLostno(Integer lostno) {
		this.lostno = lostno;
	}
	public String getLosttitle() {
		return losttitle;
	}
	public void setLosttitle(String losttitle) {
		this.losttitle = losttitle;
	}
	public byte[] getLostpic() {
		return lostpic;
	}
	public void setLostpic(byte[] lostpic) {
		this.lostpic = lostpic;
	}
	public String getLostcontent() {
		return lostcontent;
	}
	public void setLostcontent(String lostcontent) {
		this.lostcontent = lostcontent;
	}
	public Date getLosttime() {
		return losttime;
	}
	public void setLosttime(Date losttime) {
		this.losttime = losttime;
	}
	public Integer getLoststate() {
		return loststate;
	}
	public void setLoststate(Integer loststate) {
		this.loststate = loststate;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	
	
}
