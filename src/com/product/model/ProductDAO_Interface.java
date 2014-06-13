package com.product.model;
import java.util.*;
public interface ProductDAO_Interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(Integer prono);
	public ProductVO findByPrimaryKey(Integer prono);
	public List<ProductVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap�A�^��List)
	public List<ProductVO> getAll(Map<String, String[]> map);
	public void updateByPrimaryKey(ProductVO productVO);
	public void updateStatusByPrimaryKey(ProductVO productVO);
}