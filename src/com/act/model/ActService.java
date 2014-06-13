package com.act.model;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ActService {
	
	private IActDAO dao;

	public ActService(){
		dao = new ActDAO();
	}
	public ActVO addAct(String actName,String actContent, java.sql.Timestamp actStartTime,java.sql.Timestamp actEndTime ,
			byte[] actPic, String actEquipment, double actDeposit, double actHostFee , double actRegFee ,String actStatus,Integer memNo,Integer empNo) throws Exception {

		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
		int len = fis.available();
		byte[] buffer = new byte[len];
		fis.read(buffer);
    	fis.close();
		
		
		ActVO vo2 = new ActVO();
		
		vo2.setActName(actName);
		vo2.setActContent(actContent);
		vo2.setActStartTime(actStartTime);
		vo2.setActEndTime(actEndTime);
		vo2.setActPic(actPic);
		vo2.setActEquipment(actEquipment);
		vo2.setActDeposit(actDeposit);
		vo2.setActHostFee(actHostFee);
		vo2.setActRegFee(actRegFee);
		vo2.setActStatus(actStatus);
		vo2.setMemNo(memNo);
		vo2.setEmpNo(empNo);
		dao.insert(vo2);
		return vo2;
		
	}

	public ActVO updateAct(Integer actNo,String actName,String actContent, java.sql.Timestamp actStartTime,java.sql.Timestamp actEndTime ,
			byte[] actPic, String actEquipment, double actDeposit, double actHostFee , double actRegFee ,String actStatus,Integer memNo,Integer empNo) {

		ActVO vo3 = new ActVO();
		
		vo3.setActNo(actNo);
		vo3.setActName(actName);
		vo3.setActContent(actContent);
		vo3.setActStartTime(actStartTime);
		vo3.setActEndTime(actEndTime);
		vo3.setActPic(actPic);
		vo3.setActEquipment(actEquipment);
		vo3.setActDeposit(actDeposit);
		vo3.setActHostFee(actHostFee);
		vo3.setActRegFee(actRegFee);
		vo3.setActStatus(actStatus);
		vo3.setMemNo(memNo);
		vo3.setEmpNo(empNo);
		
		dao.update(vo3);
		return vo3;
	}

	public void deleteAct(Integer actNo) {
		dao.delete(actNo);
	}

	public ActVO getOneAct(Integer actNo) {
		return dao.findByPrimaryKey(actNo);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public ActVO getOneActByDate(Timestamp actStartTime) {
		return dao.findActsByDate(actStartTime);
	}

	public List<ActVO> getAllActsByDate(String actStartDate) {
		return dao.getAllActsByDate(actStartDate);
	}
	
	public List<ActVO> getAllActsByMemNo(Integer memNo) {
		return dao.getAllActsByMemNo(memNo);
	}

}
