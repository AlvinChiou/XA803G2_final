package com.pet.model;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.apt.model.AptVO;
import com.drRecord.model.DrRecordVO;

public class PetService {
	
	private IPetDAO dao;

	public PetService(){
		dao = new PetDAO();
	}
	public PetVO addPet(String petName ,String petSex,String petType, byte[] petPic, Double petAge,Integer memNo) throws Exception {

//		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//		fis.read(buffer);
//    	fis.close();
//		
		
		PetVO vo2 = new PetVO();
		
		vo2.setPetName(petName);
		vo2.setPetSex(petSex);
		vo2.setPetType(petType);
		vo2.setPetPic(petPic);
		vo2.setPetAge(petAge);
		vo2.setMemNo(memNo);
			
		dao.insert(vo2);
		return vo2;
		
	}

	public PetVO updatePet(Integer petNo ,String petName ,String petSex,String petType, byte[] petPic, Double petAge,Integer memNo) {

		PetVO vo3 = new PetVO();
		
		vo3.setPetNo(petNo);
		vo3.setPetName(petName);
		vo3.setPetSex(petSex);
		vo3.setPetType(petType);
		vo3.setPetPic(petPic);
		vo3.setPetAge(petAge);
		vo3.setMemNo(memNo);
		
		dao.update(vo3);
		return vo3;
	}

	public void deletePet(Integer petNo) {
		dao.delete(petNo);
	}

	public PetVO getOnePet(Integer petNo) {
		return dao.findByPrimaryKey(petNo);
	}
	
	public PetVO getOnePetFromMember(Integer memNo) {
		return dao.findByPrimaryKeyFromMember(memNo);
	}

	public List<PetVO> getAll() {
		return dao.getAll();
	}
	
	public List<PetVO> getAllPetsFromMember(Integer memNo) {
		return dao.getAllPetsFromMember(memNo);
	}
	
	public Set<DrRecordVO> getDrRecordsByPetNo(Integer petNo) {
		return dao.getDrRecordsByPetNo(petNo);
	}
	
public Set<AptVO> getAptsByPetNo(String petNo) {
		// TODO Auto-generated method stub
		return dao.getAptsByPetNo(petNo);
	}

public List<PetVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
}
}
