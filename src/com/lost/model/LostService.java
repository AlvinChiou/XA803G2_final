package com.lost.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class LostService {

	private LostDAO_interface dao;
	
	public LostService(){
		dao = new LostDAO();
	}
	
	public LostVO addLost(String losttitle, byte[] lostpic, String lostcontent, Date losttime, Integer loststate, Integer memno){
		
		LostVO lostVO = new LostVO();
		
		lostVO.setLosttitle(losttitle);
		lostVO.setLostpic(lostpic);
		lostVO.setLostcontent(lostcontent);
		lostVO.setLosttime(losttime);
		lostVO.setLoststate(loststate);
		lostVO.setMemno(memno);
		dao.insert(lostVO);
		
		return lostVO;
	}
	
	public LostVO updateLost(Integer lostno, String losttitle, byte[] lostpic, String lostcontent, Date losttime, Integer loststate, Integer memno){
		
		LostVO lostVO = new LostVO();
		
		lostVO.setLostno(lostno);
		lostVO.setLosttitle(losttitle);
		lostVO.setLostpic(lostpic);
		lostVO.setLostcontent(lostcontent);
		lostVO.setLosttime(losttime);
		lostVO.setLoststate(loststate);
		lostVO.setMemno(memno);
		dao.update(lostVO);
		
		return lostVO;
	}
	
	public void deleteLost(Integer lostno){
		dao.delete(lostno);
	}
	
	public LostVO getOneLost(Integer lostno){
		return dao.findByPrimaryKey(lostno);
	}
	
	public List<LostVO> getAll(){
		return dao.getAll();
	}
	
	public Set<LostVO> listLost_ByMemno(Integer memno){
		return dao.listLost_ByMemno(memno);
	}
	
	public Set<LostVO> listLost_ByState(Integer loststate){
		return dao.listLost_ByState(loststate);
	}
}