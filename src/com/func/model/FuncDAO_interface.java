package com.func.model;

import java.util.List;
import java.util.Set;

import com.pow.model.PowVO;

public interface FuncDAO_interface {
	public int insert(FuncVO funcVO);
	public int update(FuncVO funcVO);
	public int delete(Integer funcno);
	public FuncVO findByPrimaryKey(Integer funcno);
	public List<FuncVO> getAll();
	
}
