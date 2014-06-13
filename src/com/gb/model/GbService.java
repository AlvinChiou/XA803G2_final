package com.gb.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

 
public class GbService {

	private GbDAO_interface dao;
	
	public GbService(){
		dao = new GbDAO();
	}
	
	public GbVO addGb(String gbtitle, String gbcontent, java.sql.Timestamp gbtime, Integer lostno, Integer memno){
		
		GbVO gbVO = new GbVO();
		
		gbVO.setGbtitle(gbtitle);
		gbVO.setGbcontent(gbcontent);
		gbVO.setGbtime(gbtime);
		gbVO.setLostno(lostno);
		gbVO.setMemno(memno);
		dao.insert(gbVO);
		
		return gbVO;
	}
	
	public GbVO updateGb(Integer gbno,String gbtitle, String gbcontent, java.sql.Timestamp gbtime, Integer lostno, Integer memno){
		
		GbVO gbVO = new GbVO();
		
		gbVO.setGbno(gbno);
		gbVO.setGbtitle(gbtitle);
		gbVO.setGbcontent(gbcontent);
		gbVO.setGbtime(gbtime);
		gbVO.setLostno(lostno);
		gbVO.setMemno(memno);
		dao.update(gbVO);
		
		return gbVO;
	}
	
	public void deleteGb(Integer gbno){
		dao.delete(gbno);
	}
	
	public GbVO getOneGb(Integer gbno){
		return dao.findByPrimaryKey(gbno);
	}
	
	public List<GbVO> getAll(){
		return dao.getAll();
	}
	
	public List<GbVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	 
	public List<GbVO> listGb_Bylostno(Integer lostno){
		return dao.listGb_ByLostno(lostno);
	}
	
	public List<GbVO> listGb_Bymemno(Integer memno){
		return dao.listGb_ByMemno(memno);
	}
	
}
