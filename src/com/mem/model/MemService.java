package com.mem.model;

import java.util.List;
import java.util.Set;

public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService(){
		dao = new MemDAO();
	}
	
	public MemVO addMem(String memid, String mempassword, String memname, String memidno, String mememail, 
			java.sql.Date membirth, String memadd, Integer memsex, String memtel, Integer memstate) {

		MemVO memVO = new MemVO();

		memVO.setMemid(memid);
		memVO.setMempassword(mempassword);
		memVO.setMemname(memname);
		memVO.setMemidno(memidno);
		memVO.setMememail(mememail);
		memVO.setMembirth(membirth);
		memVO.setMemadd(memadd);
		memVO.setMemsex(memsex);
		memVO.setMemtel(memtel);
		memVO.setMemstate(memstate);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(Integer memno, String memid, String mempassword, String memname, String memidno, String mememail, 
			java.sql.Date membirth, String memadd, Integer memsex, String memtel, Integer memstate) {

		MemVO memVO = new MemVO();

		memVO.setMemno(memno);
		memVO.setMemid(memid);
		memVO.setMempassword(mempassword);
		memVO.setMemname(memname);
		memVO.setMemidno(memidno);
		memVO.setMememail(mememail);
		memVO.setMembirth(membirth);
		memVO.setMemadd(memadd);
		memVO.setMemsex(memsex);
		memVO.setMemtel(memtel);
		memVO.setMemstate(memstate);
		dao.update(memVO);

//		memVO = dao.findByPrimaryKey(memno);
//		memVO = getOneMem(memNo);
		return dao.findByPrimaryKey(memno);
	}

	public MemVO getOneMem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO getOneMem(String memid){
		return dao.findByMemid(memid);
	}
	
	public Set<MemVO> listMem_ByState(Integer memstate){
		return dao.listMem_ByState(memstate);
	}
}
