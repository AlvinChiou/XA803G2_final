package com.lost.model;

import java.util.*;

import com.mem.model.MemVO;

public interface LostDAO_interface {
	public int insert(LostVO lostVO);
	public int update(LostVO lostVO);
	public int delete(Integer lostno);
	public LostVO findByPrimaryKey(Integer lostno);
	public List<LostVO> getAll();
	
	public Set<LostVO> listLost_ByMemno(Integer memno);
	public Set<LostVO> listLost_ByState(Integer loststate);
	
}
