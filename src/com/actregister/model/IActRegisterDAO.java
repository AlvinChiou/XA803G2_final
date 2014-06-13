package com.actregister.model;
import java.util.*;


public interface IActRegisterDAO {
	public int insert(ActRegisterVO actReg);
    public int update(ActRegisterVO actReg);
    public int delete(Integer actRegNo);
    public int deleteByMemNoAndActNo(Integer memNo , Integer actNo);
    public ActRegisterVO findByPrimaryKey(Integer actRegNo);
    public List<ActRegisterVO> getAll();
    public List<ActRegisterVO> getAllActRegistersByMemNo(Integer memNo);
    public List<ActRegisterVO> getAllActRegistersByActNo(Integer actNo);
}
