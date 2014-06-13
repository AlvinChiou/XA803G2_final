package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
	public int insert(MemVO memVO);
	public int update(MemVO memVO);
	public MemVO findByPrimaryKey(Integer memno);
	public List<MemVO> getAll();
	
	public Set<MemVO> listMem_ByState(Integer memstate);
	public MemVO findByMemid(String memid);
	
}
