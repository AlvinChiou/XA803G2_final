package com.func.model;

import java.util.List;
import java.util.Set;

public class FuncService {
	
	private FuncDAO_interface dao;
	
	public FuncService(){
		dao = new FuncDAO(); 
	}
	
	public List<FuncVO> getAll(){
		return dao.getAll();
	}
	
	public FuncVO getOneFunc(Integer funcno){
		return dao.findByPrimaryKey(funcno);
	}
	
//	public Set<MemVO> getMemsByFunc(Integer funcno){
//		return dao.;
//	} 
	
	
	
}
