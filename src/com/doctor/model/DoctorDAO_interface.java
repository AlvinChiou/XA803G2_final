package com.doctor.model;

import java.util.List;

public interface DoctorDAO_interface {
	public void insert(DoctorVO doctorVO);

	public void update(DoctorVO doctorVO);

	public void delete(Integer drNo);

	public DoctorVO findByPrimaryKey(Integer drNO);

	public List<DoctorVO> getAll();
}
