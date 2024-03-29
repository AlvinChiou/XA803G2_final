package com.product.model;
import java.sql.Connection;
import java.util.*;
public class ProductService {
	private ProductDAO_Interface dao;
	public ProductService(){
		dao = new ProductDAO();
	}
	public ProductVO addProduct(String productname, String category, Integer price, byte[] image1, byte[] image2, byte[] image3, Integer quantity, Integer minimumquantity, Integer status, String keyword, String relatedProducts, Integer priority, Double discount, Integer score, String description){
		ProductVO productVO = new ProductVO();		
		productVO.setProductname(productname);
		productVO.setCategory(category);
		productVO.setPrice(price);
		productVO.setImage1(image1);
		productVO.setImage2(image2);
		productVO.setImage3(image3);
		productVO.setQuantity(quantity);
		productVO.setMinimumquantity(minimumquantity);
		productVO.setStatus(status);
		productVO.setKeyword(keyword);		
		productVO.setRelatedProducts(relatedProducts);
		productVO.setPriority(priority);
		productVO.setDiscount(discount);
		productVO.setScore(score);
		productVO.setDescription(description);
		dao.insert(productVO);
		return productVO;
	}
	public ProductVO updateProduct(Integer prono, String productname, String category, Integer price, byte[] image1, byte[] image2, byte[] image3, Integer quantity, Integer minimumquantity, Integer status, String keyword, String relatedProducts, Integer priority, Double discount, Integer score, String description){
		ProductVO productVO = new ProductVO();
		productVO.setProno(prono);
		productVO.setProductname(productname);
		productVO.setCategory(category);
		productVO.setPrice(price);
		productVO.setImage1(image1);
		productVO.setImage2(image2);
		productVO.setImage3(image3);
		productVO.setQuantity(quantity);
		productVO.setMinimumquantity(minimumquantity);
		productVO.setStatus(status);
		productVO.setKeyword(keyword);
		productVO.setRelatedProducts(relatedProducts);
		productVO.setPriority(priority);
		productVO.setDiscount(discount);
		productVO.setScore(score);
		productVO.setDescription(description);
		dao.update(productVO);
		return productVO;
	}
	
	public ProductVO updateByPrimaryKey(Integer prono, Integer quantity){
		ProductVO productVO = new ProductVO();

		productVO.setProno(prono);
		productVO.setQuantity(quantity);
		dao.updateByPrimaryKey(productVO);
		return productVO;
	}
	
	public ProductVO updateStatusByPrimaryKey(Integer prono, Integer status){
		ProductVO productVO = new ProductVO();

		productVO.setProno(prono);
		productVO.setStatus(status);
		dao.updateStatusByPrimaryKey(productVO);
		return productVO;
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
	public List<ProductVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public ProductVO getOneProduct(Integer prono){
		return dao.findByPrimaryKey(prono);
	}
	
	public void deleteProduct(Integer prono){
		dao.delete(prono);
	}
}
