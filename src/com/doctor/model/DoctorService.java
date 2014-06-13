package com.doctor.model;

import java.util.List;

public class DoctorService {
	private DoctorDAO_interface dao;

	public DoctorService() {
		dao = new DoctorDAO();
	}

	public DoctorVO addDr(String drName, String drExp, String drSex,
			byte[] drPic, java.sql.Date drBirth, String drAdd, String drTel) {
		DoctorVO doctorVO = new DoctorVO();

		doctorVO.setDrName(drName);
		doctorVO.setDrExp(drExp);
		doctorVO.setDrSex(drSex);
		doctorVO.setDrPic(drPic);
		doctorVO.setDrBirth(drBirth);
		doctorVO.setDrAdd(drAdd);
		doctorVO.setDrTel(drTel);
		dao.insert(doctorVO);

		return doctorVO;
	}

	public DoctorVO updateDr(Integer drNo, String drName, String drExp,String drSex, 
			byte[] drPic, java.sql.Date drBirth, String drAdd, String drTel) {
		DoctorVO doctorVO = new DoctorVO();

		doctorVO.setDrNo(drNo);
		doctorVO.setDrName(drName);
		doctorVO.setDrExp(drExp);
		doctorVO.setDrSex(drSex);
		doctorVO.setDrPic(drPic);
		doctorVO.setDrBirth(drBirth);
		doctorVO.setDrAdd(drAdd);
		doctorVO.setDrTel(drTel);
		dao.update(doctorVO);

		return doctorVO;
	}
    
	public void deleteDr(Integer drNo){
		dao.delete(drNo);
	}
	
	public DoctorVO getOneDr(Integer drNo){
		return dao.findByPrimaryKey(drNo);
	}
	
	public List<DoctorVO> getAll(){
		return dao.getAll();
	}
}

