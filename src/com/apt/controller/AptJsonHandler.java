package com.apt.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import com.apt.model.AptVO;

/**
 * connect to DAO and convert the result data to JSON
 * 
 * @author ron
 * 
 */
public class AptJsonHandler {

	public static JSONArray toJSONArray(List<AptVO> apts) {
		JSONArray jsonArray = new JSONArray();
		for (AptVO aptVO : apts) {
			JSONObject jsonObject = toJSONObject(aptVO);
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	public static JSONObject toJSONObject(AptVO aptVO) {
		/*HashMap<String, Object> map = new HashMap<String, Object>();
		int id = spot.getId();
		String name = spot.getName();
		String phoneNo = spot.getPhoneNo();
		String address = spot.getAddress();
		byte[] image = spot.getImage();
		String imageBase64 = Base64.encode(image);
		map.put("id", id);
		map.put("name", name);
		map.put("phoneNo", phoneNo);
		map.put("address", address);
		map.put("imageBase64", imageBase64);
		JSONObject jsonObject = new JSONObject(map);
		return jsonObject;*/
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String aptNo = aptVO.getAptNo();
		//將sql.Date轉成較通用的long類型
		Date apt_Date = aptVO.getAptDate();
		long aptDate = apt_Date.getTime();
		
		String aptPeriod = aptVO.getAptPeriod();
		Integer aptNoSlip = aptVO.getAptNoSlip();
		//將sql.Timestamp轉成較通用的long類型
		Timestamp apt_RegTime = aptVO.getAptRegTime();
		long aptRegTime = apt_RegTime.getTime();
		
		String petNo = aptVO.getPetNo();
		
		map.put("aptNo", aptNo);
		map.put("aptDate", aptDate);
		map.put("aptPeriod", aptPeriod);
		map.put("aptNoSlip", aptNoSlip);
		map.put("aptRegTime", aptRegTime);
		map.put("petNo", petNo);
		JSONObject jsonObject = new JSONObject(map);
		return jsonObject;
	}

}
