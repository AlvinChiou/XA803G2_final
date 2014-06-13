package com.shift.model;

import java.util.List;
import java.util.Set;
import com.shift.model.ShiftVO;

public class ShiftService {

	private ShiftDAO_interface dao;

	public ShiftService() {
		dao = new ShiftDAO();
	}

	public List<ShiftVO> getAll() {
		return dao.getAll();
	}

	public ShiftVO getOneShift(String shiftNo) {
		return dao.findByPrimaryKey(shiftNo);
	}

//	public Set<ShiftVO> getEmpsByDeptno(String shitNo) {
//		return dao.getEmpsByDeptno(shitNo);
//	}
	 
	public ShiftVO addShift(java.sql.Date shiftDate, Integer shiftMaximum, String shiftPeriod, 
			String drNo
			) {

		ShiftVO shiftVO = new ShiftVO();

		shiftVO.setShiftDate(shiftDate);;
		shiftVO.setShiftMaximum(shiftMaximum);
		shiftVO.setShiftPeriod(shiftPeriod);
		shiftVO.setDrNo(drNo);
	
		dao.insert(shiftVO);

		return shiftVO;
	}

	public void deleteShift(String shitNo) {
		dao.delete(shitNo);
	}
	
	public ShiftVO updateShift(String shiftNo,java.sql.Date shiftDate, Integer shiftMaximum, String shiftPeriod,
			String drNo ) {
		ShiftDAO shiftDAO = new ShiftDAO();
		
		ShiftVO shiftVO = shiftDAO.findByPrimaryKey(shiftNo);

		shiftVO.setShiftDate(shiftDate);
		shiftVO.setShiftMaximum(shiftMaximum);
		shiftVO.setShiftPeriod(shiftPeriod);
		shiftVO.setDrNo(drNo);
	
		dao.update(shiftVO);
		return shiftVO;
	}
}
