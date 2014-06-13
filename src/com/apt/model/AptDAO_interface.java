package com.apt.model;


import java.util.List;
import java.util.Map;

public interface AptDAO_interface {
        public int insert(AptVO aptVO);
        public int update(AptVO aptVO);
        public int delete(String apt_No);
        public AptVO findByPrimaryKey(String apt_No);
        public List<AptVO> getAll();
        public List<AptVO> getFuture20All();
        public int getLastAptNo();
		public List<AptVO> getAll(Map<String, String[]> map); 
}

