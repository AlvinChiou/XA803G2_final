package com.pet.model;
import java.util.*;

import com.act.model.ActVO;
import com.apt.model.AptVO;
import com.drRecord.model.DrRecordVO;

public interface IPetDAO {
	
	public int insert(PetVO pet);
    public int update(PetVO pet);
    public int delete(Integer petNo);
    public PetVO findByPrimaryKey(Integer petNo);
    
    public Set<DrRecordVO> getDrRecordsByPetNo(Integer petNo);
    
    public PetVO findByPrimaryKeyFromMember(Integer memNo);
    public List<PetVO> getAll();
    public List<PetVO> getAllPetsFromMember(Integer memNo);
    
    public Set<AptVO> getAptsByPetNo(String petNo);

    public List<PetVO> getAll(Map<String, String[]> map);
    
}
