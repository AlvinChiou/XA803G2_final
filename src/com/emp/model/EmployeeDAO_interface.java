package com.emp.model;

import java.util.List;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);

	public void update(EmployeeVO employeeVO);

	public void delete(Integer employeeVO);

	public EmployeeVO findByPrimaryKey(Integer empNo);

	public List<EmployeeVO> getAll();
	
	
	public int getLastEmpNo();
}
