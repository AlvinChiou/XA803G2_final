package com.apt.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class AptService {

	private AptDAO_interface dao;

	public AptService() {
		dao = new AptDAO();
	}

	public AptVO addApt(Date aptDate, String aptPeriod, Integer aptNoSlip,
			Timestamp aptRegTime, String petNo ) {

		AptVO aptVO = new AptVO();

		aptVO.setAptDate(aptDate);
		aptVO.setAptPeriod(aptPeriod);
		aptVO.setAptNoSlip(aptNoSlip);
		aptVO.setAptRegTime(aptRegTime);
		aptVO.setPetNo(petNo);
		dao.insert(aptVO);

		return aptVO;
	}

	public AptVO updateApt( String aptNo, Date aptDate, String aptPeriod, Integer aptNoSlip,
			Timestamp aptRegTime, String petNo ) {

		AptVO aptVO = dao.findByPrimaryKey(aptNo);

		aptVO.setAptDate(aptDate);
		aptVO.setAptPeriod(aptPeriod);
		aptVO.setAptNoSlip(aptNoSlip);
		aptVO.setAptRegTime(aptRegTime);
		aptVO.setPetNo(petNo);
		dao.update(aptVO);

		return aptVO;
	}

	public void deleteApt(String aptNo) {
		dao.delete(aptNo);
	}

	public AptVO getOneEmp(String aptNo) {
		return dao.findByPrimaryKey(aptNo);
	}

	public List<AptVO> getAll() {
		return dao.getAll();
	}

	public List<AptVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);

	}
}
