package com.actregister.model;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.List;

public class ActRegisterService {
	
	private IActRegisterDAO dao;

	public ActRegisterService(){
		dao = new ActRegisterDAO();
	}
	public ActRegisterVO addActRegister(String actRegName ,java.sql.Timestamp actRegDate, java.sql.Timestamp actRegTime, String actRegPayState,Integer memNo,Integer actNo) throws Exception {

//		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//		fis.read(buffer);
//    	fis.close();
		
		
		ActRegisterVO vo2 = new ActRegisterVO();
		
		vo2.setActRegName(actRegName);
		vo2.setActRegDate(actRegDate);
		vo2.setActRegTime(actRegTime);
		vo2.setActRegPayState(actRegPayState);
		vo2.setMemNo(memNo);
		vo2.setActNo(actNo);
			
		dao.insert(vo2);
		return vo2;
		
	}

	public ActRegisterVO updateActRegister(Integer actRegNo ,String actRegName, java.sql.Timestamp actRegDate, java.sql.Timestamp actRegTime, String actRegPayState,Integer memNo,Integer actNo) {

		ActRegisterVO vo3 = new ActRegisterVO();
		
		vo3.setActRegNo(actRegNo);
		vo3.setActRegName(actRegName);
		vo3.setActRegDate(actRegDate);
		vo3.setActRegTime(actRegTime);
		vo3.setActRegPayState(actRegPayState);
		vo3.setMemNo(memNo);
		vo3.setActNo(actNo);
		
		
		dao.update(vo3);
		return vo3;
	}

	public void deleteActRegister(Integer actRegNo) {
		dao.delete(actRegNo);
	}

	public ActRegisterVO getOneActRegister(Integer actRegNo) {
		return dao.findByPrimaryKey(actRegNo);
	}

	public List<ActRegisterVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActRegisterVO> getAllActRegistersByMemNo(Integer memNo) {
		return dao.getAllActRegistersByMemNo(memNo);
	}
	
	public List<ActRegisterVO> getAllActRegistersByActNo(Integer actNo) {
		return dao.getAllActRegistersByActNo(actNo);
	}
	
	public void deleteByMemNoAndActNo(Integer memNo , Integer actNo) {
		dao.deleteByMemNoAndActNo(memNo , actNo);
	}
}
