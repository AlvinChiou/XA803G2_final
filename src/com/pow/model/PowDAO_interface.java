package com.pow.model;

import java.util.List;
import java.util.Set;

import com.func.model.FuncVO;
import com.lost.model.LostVO;

public interface PowDAO_interface {
	public int insert(PowVO powVO);
	public int update(PowVO powVO);
	public int delete(Integer empno);
	public List<PowVO> findByPrimaryKey(Integer empno);
	public List<PowVO> getAll();
	
	public PowVO getOnePowByPKs(Integer empno, Integer funcno);
	public List<PowVO> listPow_Byfuncname(Integer funcno);
}
