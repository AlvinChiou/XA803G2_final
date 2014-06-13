package com.emp.model;

import java.util.List;

public class EmployeeService {
	private EmployeeDAO_interface dao;
	private String empEmail;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeVO addEmp(String empName, java.sql.Date empBirth,
			String empTel, String empSex, String empPos, Double empSalary,
			java.sql.Date empArrDate, java.sql.Date empOff, String empID,
			String empAdd, byte[] empPic, String empPassword, String empEmail) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpName(empName);
		employeeVO.setEmpBirth(empBirth);
		employeeVO.setEmpTel(empTel);
		employeeVO.setEmpSex(empSex);
		employeeVO.setEmpPos(empPos);
		employeeVO.setEmpSalary(empSalary);
		employeeVO.setEmpArrDate(empArrDate);
		employeeVO.setEmpOff(empOff);
		employeeVO.setEmpID(empID);
		employeeVO.setEmpAdd(empAdd);
		employeeVO.setEmpPic(empPic);
		employeeVO.setEmpPassword(empPassword);
		employeeVO.setEmpEmail(empEmail);
		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmp(Integer empNo, String empName,
			java.sql.Date empBirth, String empTel, String empSex,
			String empPos, Double empSalary, java.sql.Date empArrDate,
			java.sql.Date empOff, String empID, String empAdd, byte[] empPic, String empPassword, String empEmail) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpNo(empNo);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpBirth(empBirth);
		employeeVO.setEmpTel(empTel);
		employeeVO.setEmpSex(empSex);
		employeeVO.setEmpPos(empPos);
		employeeVO.setEmpSalary(empSalary);
		employeeVO.setEmpArrDate(empArrDate);
		employeeVO.setEmpOff(empOff);
		employeeVO.setEmpID(empID);
		employeeVO.setEmpAdd(empAdd);
		employeeVO.setEmpPic(empPic);
		employeeVO.setEmpPassword(empPassword);
		employeeVO.setEmpEmail(empEmail);
		
		dao.update(employeeVO);

		return employeeVO;
	}

	public void deleteEmp(Integer empNo) {
		dao.delete(empNo);
	}

	public EmployeeVO getOneEmp(Integer empNo) {
		return dao.findByPrimaryKey(empNo);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}
