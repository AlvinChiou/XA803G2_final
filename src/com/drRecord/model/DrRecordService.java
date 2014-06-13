package com.drRecord.model;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.List;

public class DrRecordService {
	
	private IdrRecordDAO dao;

	public DrRecordService(){
		dao = new DrRecordDAO();
	}
	public DrRecordVO addDrRecord(Date drRecTime ,String drRecPres,Integer drNo,Integer petNo) throws Exception {

//		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//		fis.read(buffer);
//    	fis.close();
		
		
		DrRecordVO vo2 = new DrRecordVO();
		
		vo2.setDrRecTime(drRecTime);
		vo2.setDrRecPres(drRecPres);
		vo2.setDrNo(drNo);
		vo2.setPetNo(petNo);
		dao.insert(vo2);
		return vo2;
		
	}

	public DrRecordVO updateDrRecord(Integer drRecNo ,Date drRecTime ,String drRecPres,Integer drNo,Integer petNo) {

		DrRecordVO vo3 = new DrRecordVO();
		
		vo3.setDrRecNo(drRecNo);
		vo3.setDrRecTime(drRecTime);
		vo3.setDrRecPres(drRecPres);
		vo3.setDrNo(drNo);
		vo3.setPetNo(petNo);
		
		dao.update(vo3);
		return vo3;
	}

	public void deleteDrRecord(Integer drRecNo) {
		dao.delete(drRecNo);
	}

	public DrRecordVO getOneDrRecord(Integer drRecNo) {
		return dao.findByPrimaryKey(drRecNo);
	}

	public List<DrRecordVO> getAll() {
		return dao.getAll();
	}
}
