package com.drRecord.model;
import java.util.*;


public interface IdrRecordDAO {
	public int insert(DrRecordVO drRec);
    public int update(DrRecordVO drRec);
    public int delete(Integer drRecNo);
    public DrRecordVO findByPrimaryKey(Integer drRecNo);
    public List<DrRecordVO> getAll();
}
