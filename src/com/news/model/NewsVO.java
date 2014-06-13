package com.news.model;

import java.io.Serializable;
import java.sql.Date;

public class NewsVO implements Serializable {
	private Integer newsno;
	private String newstitle;
	private Integer newstype;
	private String newscontent;
	private byte[] newspic;
	private Date newspotime;
	private Integer empno;
	
	public Integer getNewsno() {
		return newsno;
	}
	public void setNewsno(Integer newsno) {
		this.newsno = newsno;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public Integer getNewstype() {
		return newstype;
	}
	public void setNewstype(Integer newstype) {
		this.newstype = newstype;
	}
	public String getNewscontent() {
		return newscontent;
	}
	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}
	public byte[] getNewspic() {
		return newspic;
	}
	public void setNewspic(byte[] newspic) {
		this.newspic = newspic;
	}
	public Date getNewspotime() {
		return newspotime;
	}
	public void setNewspotime(Date newsPotime) {
		this.newspotime = newsPotime;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	} 
	
	
}
