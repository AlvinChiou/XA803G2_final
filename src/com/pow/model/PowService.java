package com.pow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.func.model.FuncVO;

public class PowService {
	
	private PowDAO_interface dao;
	
	public PowService(){
		dao = new PowDAO();
	}
	
	public PowVO addPow(Integer empno, Integer funcno){
		PowVO powVO = new PowVO();
		powVO.setEmpno(empno);
		powVO.setFuncno(funcno);
		dao.insert(powVO);
		return powVO;
	}
	
	public List<PowVO> updatePow(Integer empno, Integer funcno){
		PowVO powVO = new PowVO();
		powVO.setEmpno(empno);
		powVO.setFuncno(funcno);
		dao.update(powVO);
		return dao.findByPrimaryKey(empno);
	}
	
	public void deletePow(Integer empno){
		dao.delete(empno);
	}
	
	public List<PowVO> getOnePow(Integer empno){
		return dao.findByPrimaryKey(empno);
	}
	
	public List<PowVO> getAll(){
		return dao.getAll();
	}
	
	public PowVO getOnePowByPKs(Integer empno, Integer funcno){
		return dao.getOnePowByPKs(empno, funcno);
	}
	
	public List<PowVO> listPow_Byfuncname(Integer funcno){
		return dao.listPow_Byfuncname(funcno);
	}
	
}
