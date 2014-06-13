package com.pow.model;

import java.io.Serializable;

public class PowVO implements Serializable {
	private Integer empno;
	private Integer funcno;
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Integer getFuncno() {
		return funcno;
	}
	public void setFuncno(Integer funcno) {
		this.funcno = funcno;
	}
	public int hashCode(){
		return new Integer(empno).hashCode()^new Integer(funcno).hashCode();
	}
	public boolean equals(Object obj){
		if(obj != null && obj instanceof PowVO){
			PowVO powVO = (PowVO)obj;
			if(this.empno.intValue() == powVO.empno.intValue() && this.funcno.intValue() == powVO.funcno.intValue()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
