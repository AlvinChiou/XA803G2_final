package com.productitem.model;
import java.util.*;

import com.order.model.OrderVO;

public class ProdItemService {
	private ProdItemDAO_interface dao;
	public ProdItemService(){
		dao = new ProdItemDAO();
	}
	public ProdItemVO addProdItem(Integer itemno, Integer itemqty, String itemmemo, String ordno, Integer prono, Integer price){
		ProdItemVO prodItemVO = new ProdItemVO();	
		prodItemVO.setItemmemo(itemmemo);
		prodItemVO.setItemqty(itemqty);
		prodItemVO.setItemno(itemno);
		prodItemVO.setOrdno(ordno);
		prodItemVO.setProno(prono);
		prodItemVO.setPrice(price);
		dao.insert(prodItemVO);
		return prodItemVO;
	}
	public ProdItemVO updateProdItemVO(Integer itemno, Integer itemqty, String itemmemo, String ordno, Integer prono, Integer price){
		ProdItemVO prodItemVO = new ProdItemVO();
		prodItemVO.setItemmemo(itemmemo);
		prodItemVO.setItemqty(itemqty);
		prodItemVO.setItemno(itemno);
		prodItemVO.setOrdno(ordno);
		prodItemVO.setProno(prono);
		prodItemVO.setPrice(price);
		dao.update(prodItemVO);
		return prodItemVO;
	}
	public List<ProdItemVO> getAll(){
		return dao.getAll();
	}
	public ProdItemVO getOnProdItemVO(String ordno){
		return dao.findByPrimaryKey(ordno);
	}
	public void deleteProdItem(String ordno){
		dao.delete(ordno);
	}

}
