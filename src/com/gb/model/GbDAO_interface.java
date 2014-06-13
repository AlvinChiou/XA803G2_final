package com.gb.model;

import java.util.List;
import java.util.Map;

import com.gb.model.GbVO;
import com.news.model.NewsVO;

public interface GbDAO_interface {
	public int insert(GbVO gbVO);
	public int update(GbVO gbVO);
	public int delete(Integer gbno);
	public GbVO findByPrimaryKey(Integer gbno);
	public List<GbVO> getAll();
	
	public List<GbVO> listGb_ByLostno(Integer lostno);
	public List<GbVO> listGb_ByMemno(Integer memno);
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	public List<GbVO> getAll(Map<String, String[]> map);
}
