package com.func.model;

import java.io.Serializable;

public class FuncVO implements Serializable {
	private Integer funcno;
	private String funcname;
	
	public Integer getFuncno() {
		return funcno;
	}
	public void setFuncno(Integer funcno) {
		this.funcno = funcno;
	}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	
}
