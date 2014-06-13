package com.act.model;
import java.sql.Timestamp;
import java.util.*;

public interface IActDAO {
	
	public int insert(ActVO act);
    public int update(ActVO act);
    public int delete(Integer actNo);
    public ActVO findByPrimaryKey(Integer actNo);
    public List<ActVO> getAll();
    public List<ActVO> getAll(Map<String, String[]> map);
    public ActVO findActsByDate(Timestamp actStartTime);
    public List<ActVO> getAllActsByDate(String actStartDate);
    public List<ActVO> getAllActsByMemNo(Integer memNo);
    public void updateStatus(String actStatus);
}
